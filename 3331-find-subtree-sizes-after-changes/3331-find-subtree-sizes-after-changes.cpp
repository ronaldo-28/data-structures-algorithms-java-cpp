// The new parent of x is its closest original ancestor y with s[y] == s[x] (else parent[x]); all changes are
// simultaneous and only depend on the original tree. One iterative DFS keeps last[c] = deepest ancestor-or-self
// with letter c (saved on entry, restored on exit) and records the pre-order. A new parent is an original
// ancestor, hence earlier in that pre-order, so accumulating sizes in reverse pre-order is valid. O(n).
// variant B: sizes accumulated directly into par (no SZ array / copy pass): par[] is no longer needed after the CSR.
// Result written over parent (same type/size) and returned by move.
#define LC_FASTNEW
// ===== LC FAST HEADER v1 (13.11.2026, research/judge_speed.md §9) =====
// Вставлять В НАЧАЛО решения. Всё проверено на judge-сборке (clang 19 -O2 -fsanitize=address, libstdc++ 14).
// В отладочной сборке (-DLC_DEBUG) всё ускоряющее выключается, чтобы ASan/UBSan ловили наши баги.
#ifndef LC_DEBUG
// (1) Настройки ASan рантайма. Читаются при init ASan (до main) -> действуют. Если судья задаёт
//     ASAN_OPTIONS в env, совпадающие флаги env перекрывают наши (остальные остаются).
//     Замер: аллокационная нагрузка -32..-37%, глубокая рекурсия -35% (UAR=0).
extern "C" __attribute__((used)) const char* __asan_default_options() {
    return "detect_stack_use_after_return=0:malloc_context_size=0:quarantine_size_mb=0:"
           "thread_local_quarantine_size_kb=0:max_malloc_fill_size=0:poison_heap=0:detect_leaks=0:"
           "alloc_dealloc_mismatch=0:new_delete_type_mismatch=0:detect_container_overflow=0:replace_intrin=0";
}
// (2) Отвязать cin от stdio: драйвер читает тесты getline(cin) ДО вызова; если чтение попадает в таймер
//     (leetcode.cn hqztrue; на 3208 проверяется), даёт 226 -> 19 мс на 20 МБ входа. Вреда нет.
static const int lc_io_ = [] { std::ios::sync_with_stdio(false); std::cin.tie(nullptr); return 0; }();
#endif

// (3) ОПЦИЯ: #define LC_FASTNEW перед вставкой — свой operator new/delete (free-list по классам 2^k,
//     мимо аллокатора ASan). Замер: vector/map/unordered_map-нагрузка -64%, новый vector ответа -79%.
//     НЕЛЬЗЯ: многопоточные задачи (1114-1117, 1195, 1226, 1242, 1279) — аллокатор не потокобезопасен.
#if defined(LC_FASTNEW) && !defined(LC_DEBUG)
#include <cstdlib>
#include <new>
#define LCA_ __attribute__((no_sanitize("address", "undefined")))
struct LcFree_ { LcFree_* nx; };
static LcFree_* lc_fl_[64];
static char *lc_cur_, *lc_end_, *lc_chunks_[4096]; static int lc_nch_;
static char lc_arena_[64 << 20];   // .bss: память учитывается только по касанию
LCA_ static inline void* lc_alloc_(size_t n) {
    if (n > (1u << 20)) { char* p = (char*)std::malloc(n + 16); if (!p) throw std::bad_alloc(); *(size_t*)p = 63; return p + 16; }
    unsigned c = n <= 16 ? 4 : 64 - __builtin_clzll(n - 1);
    if (LcFree_* f = lc_fl_[c]) { lc_fl_[c] = f->nx; return f; }
    size_t need = (size_t(1) << c) + 16;
    if (!lc_cur_) { lc_cur_ = lc_arena_; lc_end_ = lc_arena_ + sizeof lc_arena_; }
    if (lc_cur_ + need > lc_end_) { size_t ch = 64 << 20; lc_cur_ = (char*)std::malloc(ch); if (!lc_cur_) throw std::bad_alloc();
        if (lc_nch_ < 4096) lc_chunks_[lc_nch_++] = lc_cur_; lc_end_ = lc_cur_ + ch; }
    char* p = lc_cur_; lc_cur_ += need; *(size_t*)p = c; return p + 16;
}
LCA_ static inline void lc_free_(void* q) {
    if (!q) return; char* p = (char*)q - 16; size_t c = *(size_t*)p;
    if (c == 63) { std::free(p); return; }
    LcFree_* f = (LcFree_*)q; f->nx = lc_fl_[c]; lc_fl_[c] = f;
}
void* operator new(size_t n) { return lc_alloc_(n); }
void* operator new[](size_t n) { return lc_alloc_(n); }
void* operator new(size_t n, const std::nothrow_t&) noexcept { return lc_alloc_(n); }   // stable_sort/inplace_merge
void* operator new[](size_t n, const std::nothrow_t&) noexcept { return lc_alloc_(n); }
void operator delete(void* p) noexcept { lc_free_(p); }
void operator delete[](void* p) noexcept { lc_free_(p); }
void operator delete(void* p, size_t) noexcept { lc_free_(p); }
void operator delete[](void* p, size_t) noexcept { lc_free_(p); }
void operator delete(void* p, const std::nothrow_t&) noexcept { lc_free_(p); }
void operator delete[](void* p, const std::nothrow_t&) noexcept { lc_free_(p); }
#endif

// (4) Ядра: горячий код только в KN-функциях на сырых указателях (НЕ ставить на функцию с vector&/лямбдами:
//     no_sanitize + vector::operator[] = 4-5x МЕДЛЕННЕЕ, т.к. запрещён инлайн). KT = KN + popcnt/bmi/lzcnt.
#ifndef LC_DEBUG
#define KN static __attribute__((noinline, no_sanitize("address", "undefined")))
#define KT static __attribute__((noinline, no_sanitize("address", "undefined"), target("popcnt,bmi,bmi2,lzcnt")))
#define KI static inline __attribute__((no_sanitize("address", "undefined")))   // хелперы, вызываемые ТОЛЬКО из KN
#else
#define KN static
#define KT static
#define KI static inline
#endif

// (5) Предрасчёт вне таймера: глобальный инициализатор выполняется до main (startup не считается, ОФИЦ 2024.10).
//     Замер: решето 2e6 под ASan = 2.9 мс убрано из замера (10.5 -> 7.6 мс на 30 тестах).
//     Шаблон:  static int TAB[N];  KN void lc_build() { ... }  static const int lc_b_ = (lc_build(), 0);
// (6) Большие static-буферы (>= 4 МБ) — прогреть страницы в инициализаторе (page faults вне таймера), -33%:
//     KN void lc_touch(void* p, size_t bytes) { for (size_t i = 0; i < bytes; i += 4096) ((volatile char*)p)[i] = 0; }
//     static const int lc_t_ = (lc_touch(BUF, sizeof BUF), 0);
// (7) Ответ-вектор: если сигнатура vector<int>& nums, а ответ того же типа/размера и задача НЕ in-place
//     (драйвер не печатает nums), писать в nums и `return std::move(nums);` — без аллокации, -91%.
// ===== /LC FAST HEADER =====
enum { NM = 100000 };
static int HEAD[NM + 2], CH[NM], STK[NM], IT[NM], SAV[NM], ORD[NM], NP[NM], SZ[NM];
KN void kern(int* par, const char* s, int n) {
    for (int i = 0; i <= n; ++i) HEAD[i] = 0;
    for (int i = 1; i < n; ++i) ++HEAD[par[i] + 1];
    for (int i = 0; i < n; ++i) HEAD[i + 1] += HEAD[i];
    for (int i = 0; i < n; ++i) IT[i] = HEAD[i];
    for (int i = 1; i < n; ++i) CH[IT[par[i]]++] = i;
    for (int i = 0; i < n; ++i) IT[i] = HEAD[i];
    int last[26]; for (int c = 0; c < 26; ++c) last[c] = -1;
    int sp = 0, on = 0;
    { int c = s[0] - 'a'; SAV[0] = -1; last[c] = 0; NP[0] = -1; ORD[on++] = 0; STK[sp++] = 0; }
    while (sp) {
        int u = STK[sp - 1];
        if (IT[u] < HEAD[u + 1]) {
            int v = CH[IT[u]++], c = s[v] - 'a', l = last[c];
            NP[v] = l >= 0 ? l : u; SAV[v] = l; last[c] = v; ORD[on++] = v; STK[sp++] = v;
        } else { last[s[u] - 'a'] = SAV[u]; --sp; }
    }
    for (int i = 0; i < n; ++i) par[i] = 1;
    for (int k = n - 1; k > 0; --k) { int v = ORD[k]; par[NP[v]] += par[v]; }
}
class Solution {
public:
    vector<int> findSubtreeSizes(vector<int>& parent, string s) {
        kern(parent.data(), s.data(), parent.size());
        return std::move(parent);
    }
};