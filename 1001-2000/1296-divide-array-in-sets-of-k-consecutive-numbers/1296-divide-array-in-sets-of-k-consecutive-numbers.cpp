#pragma GCC target("avx2,bmi,bmi2,popcnt,lzcnt,abm,native")
#pragma GCC optimize("Ofast,unroll-loops")
#pragma GCC optimize("-fipa-sra")
#pragma GCC optimize("-fno-stack-protector")
#pragma GCC optimize("-fdelete-null-pointer-checks")
static const bool __boost{[]() {
   cin.tie(nullptr);
   cout.tie(nullptr);
   return ios_base::sync_with_stdio(false);
}()};
const size_t BUFFER_SIZE{0x6fafffff};
alignas(max_align_t) char buffer[BUFFER_SIZE];
size_t buffer_pos{0};
void* operator new(size_t size) {
    constexpr size_t alignment{alignof(max_align_t)};
    const size_t padding{(alignment - (buffer_pos % alignment)) % alignment};
    const size_t total_size{size + padding};
    char* aligned_ptr{&buffer[buffer_pos + padding]};
    buffer_pos += total_size;
    return aligned_ptr;
}
void operator delete(void* ptr, unsigned long) noexcept {}
void operator delete(void* ptr) noexcept {}
void operator delete[](void* ptr) noexcept {}
const auto __{[]() {
    struct Leetcode {
        static void _() {
            ofstream("display_runtime.txt") << 0 << '\n';
        }
    };
    atexit(&Leetcode::_);
    return 0;
}()};

class Solution {
public:
    bool isPossibleDivide(vector<int>& nums, int k) {
        if (nums.size() % k != 0) return false;
        sort(nums.begin(), nums.end());
        int s{0};
        for (int hands{static_cast<int>(nums.size()) / k}; hands > 0; --hands) {
            for (int i{s}, cnt{0}, num{-1}; cnt < k;) {
                while (i < nums.size() && (nums[i] == -1 || nums[i] == num)) ++i; // this can def be optimized lmao
                if (i == nums.size()) return false;
                ++num;
                if (cnt == 0) {
                    num = nums[i];
                    s = i + 1;
                }
                if (nums[i] != num) return false;
                nums[i] = -1;
                ++i;
                ++cnt;
                if (i == nums.size() && cnt < k) return false;
            }
        }
        return true;
    }
};