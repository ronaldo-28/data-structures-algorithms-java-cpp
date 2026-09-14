// class Solution {
// public:
//     // we need a sorted structure so we use map, not unordered_map
//     static map<int, pair<char, int>> rle;
//     // since lens of characters can be duplicate, and we also linear function
//     // lngst_rpt_chr(), gives use Q*N, which is N^2 time complexity but now,
//     // this will give us Q*log(N) which is similar to segment tree approach
//     static multiset<int> lngst_lens;
//     void build_rle(map<int, pair<char, int>>& rle, string str) {
//         int strt = 0, len = str.length();
//         while (strt < len) {
//             int j = strt + 1;
//             while (j < len && str[j] == str[strt])
//                 j++;
//             int chr_len = j - strt;
//             rle[strt] = make_pair(str[strt], chr_len);
//             lngst_lens.insert(chr_len);
//             strt = j;
//         }
//     }

//     void split(int pos) {
//         auto itrtr = rle.upper_bound(pos);
//         itrtr--;
//         char old_chr = itrtr->second.first;
//         int start_pos = itrtr->first;
//         int len = itrtr->second.second;
//         int end_pos = start_pos + len - 1;
//         if (start_pos == pos) return;
//         // FIX: remove old segment length
//         lngst_lens.erase(lngst_lens.find(len));
//         int left_len = pos - start_pos;
//         int right_len = end_pos - pos + 1;
//         rle[start_pos] = make_pair(old_chr, left_len);
//         rle[pos] = make_pair(old_chr, right_len);
//         // FIX: insert new segment lengths
//         lngst_lens.insert(left_len);
//         lngst_lens.insert(right_len);
//     }

//     void merge(int pos) {
//         auto itrtr = rle.find(pos);
//         if (itrtr != rle.begin()) {
//             auto prev_itrtr = itrtr;
//             prev_itrtr--;
//             if (prev_itrtr->second.first == itrtr->second.first) {
//                 // FIX: remove both old lengths
//                 lngst_lens.erase(lngst_lens.find(prev_itrtr->second.second));
//                 lngst_lens.erase(lngst_lens.find(itrtr->second.second));
//                 prev_itrtr->second.second += itrtr->second.second;
//                 // FIX: insert merged length
//                 lngst_lens.insert(prev_itrtr->second.second);
//                 rle.erase(itrtr);
//                 itrtr = prev_itrtr;
//             }
//         }

//         auto nxt_itrtr = next(itrtr);
//         if (nxt_itrtr != rle.end()) {
//             if (nxt_itrtr->second.first == itrtr->second.first) {
//                 // FIX: remove both old lengths
//                 lngst_lens.erase(lngst_lens.find(itrtr->second.second));
//                 lngst_lens.erase(lngst_lens.find(nxt_itrtr->second.second));
//                 itrtr->second.second += nxt_itrtr->second.second;
//                 // FIX: insert merged length
//                 lngst_lens.insert(itrtr->second.second);
//                 rle.erase(nxt_itrtr);
//             }
//         }
//     }

//     void lngst(string str, string queryCharacters, vector<int>& queryIndices,
//                vector<int>& rslt) {
//         build_rle(rle, str);
//         int len = queryIndices.size();
//         auto lngst_rpt_chr = [&]() {
//             // int lrgst_len = 0;
//             // for (const auto& sgnt : rle)
//             //     if (sgnt.second.second > lrgst_len)
//             //         lrgst_len = sgnt.second.second;
//             // rslt.push_back(lrgst_len);
//             rslt.push_back(*lngst_lens.rbegin());
//         };

//         for (int indx = 0; indx < len; indx++) {
//             char new_chr = queryCharacters[indx];
//             int pos = queryIndices[indx];
//             auto itrtr = rle.upper_bound(pos);
//             itrtr--;
//             char old_chr = itrtr->second.first;
//             if (old_chr == new_chr) {
//                 lngst_rpt_chr();
//                 continue;
//             } else {
//                 split(pos);
//                 // Before: [5 → (a,5)]  → covers [5,6,7,8,9]
//                 // After split(7):
//                 // 5 → (a,2)   - [5,6]
//                 // 7 → (a,3)   - [7,8,9]
//                 // ❌ position 7 is still inside a segment of length 3
//                 // You need:
//                 // 7 → (a,1), 8 → (a,2), hence add split(pos + 1);
//                 split(pos + 1);
//                 auto new_itrtr = rle.find(pos);
//                 new_itrtr->second.first = new_chr;
//                 new_itrtr->second.second = 1;
//                 merge(pos);
//                 lngst_rpt_chr();
//             }
//         }
//     }

//     vector<int> longestRepeating(string s, string queryCharacters,
//                                  vector<int>& queryIndices) {
//         rle.clear();
//         lngst_lens.clear();
//         vector<int> rslt;
//         lngst(s, queryCharacters, queryIndices, rslt);
//         return rslt;
//     }
// };
// map<int, pair<char, int>> Solution::rle;
// multiset<int> Solution::lngst_lens;

#define FOR(i, a, b) for (long i = (a); i < (b); i++)
#define REP(i, n) for (long i = 0; i < (n); i++)
#define ROF(i, a, b) for (long i = (b); --i >= (a); )

class Solution {
  int nn;
  string s;
  struct Seg {int lc, mc, rc; };
  vector<Seg> seg;

  void mconcat(int i, int k) {
    int p = i>>1;
    i &= ~1;
    seg[p].lc = seg[i].lc;
    seg[p].rc = seg[i^1].rc;
    seg[p].mc = max(seg[i].mc, seg[i^1].mc);
    int mid = (i^1)*k-nn;
    if (s[mid-1] == s[mid]) {
      if (seg[p].lc == k) seg[p].lc += seg[i^1].lc;
      if (seg[p].rc == k) seg[p].rc += seg[i].rc;
      seg[p].mc = max(seg[p].mc, seg[i].rc+seg[i^1].lc);
    }
  }
public:
  vector<int> longestRepeating(string s_, string modc, vector<int>& modi) {
    int n = s_.size(), ln = n == 1 ? 0 : 32-__builtin_clz(n-1);
    nn = 1 << ln;
    vector<int> ret(modc.size());
    seg.resize(2*nn);
    s = move(s_);
    s.resize(nn);
    FOR(i, nn, nn+n)
      seg[i].lc = seg[i].mc = seg[i].rc = 1;
    if (nn > 1) {
      for (int i = nn; i < nn+n; i += 2) mconcat(i, 1);
      ROF(i, 2, nn) mconcat(i, 1 << __builtin_clz(i)-__builtin_clz(nn));
    }
    REP(i, modc.size()) {
      s[modi[i]] = modc[i];
      int x = nn+modi[i];
      for (int k = 1; x > 1; x >>= 1, k <<= 1)
        mconcat(x, k);
      ret[i] = seg[1].mc;
    }
    return ret;
  }
};