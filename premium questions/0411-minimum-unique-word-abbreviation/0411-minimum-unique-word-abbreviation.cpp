/*
The Bitmask & DFS Approach
For an abbreviation to be valid, it must differ from every dictionary word of the same length by at least one kept character.

Conflict Masks: We compare the target against each relevant dictionary word. We create a "conflict mask" for each dictionary word where a bit is set to 1 if the character in the target differs from the character in the dictionary word.

Validity Check: For any proposed abbreviation bitmask, it is only valid if it shares at least one 1 bit with every dictionary conflict mask. Mathematically: (abbr_mask & conflict_mask) > 0.

IMP: DFS with Pruning: Instead of generating and checking all $2^N$ possible masks, we use Depth-First Search (DFS) to build the mask bit by bit. We aggressively track the string length of the abbreviation as we build it. If the current length exceeds or equals the shortest valid length we've found so far (min_len), we immediately prune that branch. Exploring the "abbreviate" (0 bit) branches first finds short abbreviations quickly, making the pruning extremely powerful.
*/
class Solution {
private:
    int min_len;
    int best_mask;
    int n;
    vector<int> diff_masks;

    // Helper to simulate to_string(count).length() instantly
    inline int getNumLen(int count) {
        if (count == 0) return 0;
        if (count < 10) return 1;
        return 2; // Max n is 21, so count will never exceed 99
    }

    void dfs(int pos, int mask, int length, int zero_count) {
        // Calculate exact length based on digits
        int current_total_len = length + getNumLen(zero_count);
        
        // Pruning: Stop exploring if this path is already longer than our best found
        if (current_total_len >= min_len) return; 

        if (pos == n) {
            // Reached the end; check against all dictionary conflicts
            for (int diff : diff_masks) {
                if ((mask & diff) == 0) return; // Invalid: no differing characters kept
            }
            
            // If valid and strictly shorter, update our best record
            min_len = current_total_len;
            best_mask = mask;
            return;
        }

        // Option 1: Abbreviate target[pos] (Keep bit 0, increment zero_count)
        dfs(pos + 1, mask, length, zero_count + 1);

        // Option 2: Keep target[pos] (Set bit 1)
        // Finalize the length of the zeros we just skipped
        int added_len_from_zeros = getNumLen(zero_count);
        dfs(pos + 1, mask | (1 << pos), length + 1 + added_len_from_zeros, 0);
    }

public:
    string minAbbreviation(string target, vector<string>& dictionary) {
        n = target.length();
        
        for (const string& w : dictionary) {
            if (w.length() == n) {
                int diff = 0;
                for (int i = 0; i < n; ++i) {
                    if (target[i] != w[i]) {
                        diff |= (1 << i);
                    }
                }
                diff_masks.push_back(diff);
            }
        }

        if (diff_masks.empty()) return to_string(n);

        min_len = n + 1; 
        best_mask = (1 << n) - 1;

        dfs(0, 0, 0, 0);

        // Reconstruct the resulting abbreviation string
        string res = "";
        int zeros = 0;
        for (int i = 0; i < n; ++i) {
            if (best_mask & (1 << i)) {
                if (zeros > 0) {
                    res += to_string(zeros);
                    zeros = 0;
                }
                res += target[i];
            } else {
                zeros++;
            }
        }
        if (zeros > 0) {
            res += to_string(zeros);
        }

        return res;
    }
};
/*
Complexity Analysis
Time Complexity: O(2^N × M), where N is the length of target and M is the number of words in dictionary of length N. Without pruning, we would check all 2^N masks against M dictionary words. In practice, the DFS branch-and-bound pruning combined with checking the abbreviation path first drops the execution time drastically to effectively near O(N×M).

Space Complexity: O(M+N) where O(M) is used to store the diff_masks for relevant dictionary words, and O(N) is the maximum recursive depth of the call stack used by the DFS.
*/

/*
In second DFS call, we claim to set target[pos] but when we do 1 << pos, we set pos from right not left, isn't this incorrect?

this is not incorrect, and the code works perfectly.

Here is why: bitmasks do not care about visual direction (left vs. right). They only care about consistency in mapping. As long as string index i always corresponds to bit index i, the algorithm holds together.

Let's look at how this code consistently maps index 0 (the leftmost character) to bit 0 (the rightmost bit) across all three phases of the algorithm:

if (target[i] != w[i]) {
    diff |= (1 << i);
}
If the first character (i = 0) differs, it sets bit 0 (1 << 0). The left-most character is now officially mapped to the right-most bit.

dfs(pos + 1, mask | (1 << pos), ...);
If it decides to keep the first character (pos = 0), it sets bit 0 (1 << 0). This aligns perfectly with the dictionary masks. When it checks (mask & diff) == 0 at the base case, the bits for character i line up exactly.

for (int i = 0; i < n; ++i) {
    if (best_mask & (1 << i)) {
        res += target[i];
    }
}
It asks, "Is bit 0 set? If so, append character 0." It reads the string left-to-right while reading the integer from LSB to MSB.

Why do it this way?
It is simply much easier to write.

If you wanted to align them visually (mapping the leftmost character to the leftmost bit), you would have to calculate the bit shift in reverse. Every time you wanted to set or check a bit, you would have to write 1 << (n - 1 - i) or 1 << (n - 1 - pos).
*/

#if 0 // old length calculation
class Solution {
private:
    int min_len;
    int best_mask;
    int n;
    vector<int> diff_masks;

    void dfs(int pos, int mask, int length, int zero_count) {
        // A non-zero count of abbreviated characters always adds exactly 1 to the length
        int current_total_len = length + (zero_count > 0 ? 1 : 0);
        
        // Pruning: Stop exploring if this path is already longer than our best found
        if (current_total_len >= min_len) return; 

        if (pos == n) {
            // Reached the end; check against all dictionary conflicts
            for (int diff : diff_masks) {
                if ((mask & diff) == 0) return; // Invalid: no differing characters kept
            }
            
            // If valid and strictly shorter, update our best record
            min_len = current_total_len;
            best_mask = mask;
            return;
        }

        // Option 1: Abbreviate target[pos] (Keep bit 0, increment zero_count)
        dfs(pos + 1, mask, length, zero_count + 1);

        // Option 2: Keep target[pos] (Set bit 1)
        // If we were abbreviating, finalize that substring's length (which is exactly 1)
        int added_len_from_zeros = (zero_count > 0 ? 1 : 0);
        dfs(pos + 1, mask | (1 << pos), length + 1 + added_len_from_zeros, 0);
    }

public:
    string minAbbreviation(string target, vector<string>& dictionary) {
        n = target.length();
        
        for (const string& w : dictionary) {
            if (w.length() == n) {
                int diff = 0;
                for (int i = 0; i < n; ++i) {
                    if (target[i] != w[i]) {
                        diff |= (1 << i);
                    }
                }
                diff_masks.push_back(diff);
            }
        }

        if (diff_masks.empty()) return to_string(n);

        min_len = n + 1; 
        best_mask = (1 << n) - 1;

        dfs(0, 0, 0, 0);

        // Reconstruct the resulting abbreviation string
        string res = "";
        int zeros = 0;
        for (int i = 0; i < n; ++i) {
            if (best_mask & (1 << i)) {
                if (zeros > 0) {
                    res += to_string(zeros);
                    zeros = 0;
                }
                res += target[i];
            } else {
                zeros++;
            }
        }
        if (zeros > 0) {
            res += to_string(zeros);
        }

        return res;
    }
};

#endif