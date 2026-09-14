// Approach 1: Bit by Bit
class Solution1 {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int ret = 0, power = 31;
        while (n != 0) {
            // += == OR(|),  the modulo operation(%2) is &
            ret += (n & 1) << power;
            n = n >>> 1;
            power -= 1;
        }
        return ret;
    }
}

//Cleaner and idiomatic
class Solution {
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            res = (res << 1) | (n & 1);
            n >>>= 1;
        }
        return res;
    }
}

/*
# 🧠 Line-by-Line Explanation
### 1️⃣ `res = (res << 1) | (n & 1);`
* `n & 1` → extracts the **last (rightmost) bit** of `n`
* `res << 1` → makes space on the right
* `|` → inserts that extracted bit into `res`
So we:
* Take the last bit of `n`
* Append it to `res`
* Repeat 32 times
---

### 2️⃣ `n >>>= 1;`  ← Important
This shifts `n` right **logically** (fills left with 0).
That means:
* We move to the next bit
* We do NOT preserve the sign bit
---

# 🚨 Why `>>>` Instead of `>>`?
Because `n` might be **negative**.
In Java:
* `int` is **signed 32-bit**
* Negative numbers have leading `1`s
---
## ❌ What Happens If We Use `>>`?
If `n` is negative:
n = 11111111 11111111 11111111 11111010   (-6 example)
Using:
n >>= 1;
Result:
11111111 11111111 11111111 11111101

See the problem?
The left side keeps filling with `1`s forever.
So after many shifts:
* `n` never becomes 0
* The bits we extract become incorrect
* The reversed result is wrong

## ✅ What Happens With `>>>`?
Using:
n >>>= 1;
Result:
01111111 11111111 11111111 11111101
Now:
* Left side fills with `0`
* Bits shift cleanly
* After 32 shifts, `n` becomes 0
* Every bit is processed correctly

# 🎯 Why This Matters for Bit Reversal
When reversing bits:
* We care about the raw 32-bit pattern
* We do NOT care about the sign
* We must treat the number as **unsigned**

# 💡 Simple Mental Model
* `>>` = “keep the sign”
* `>>>` = “just move bits”

This algorithm needs to **just move bits**, not preserve sign.
 */