class Solution {
public:
    vector<int> findMaximums(vector<int>& n) {
        // monotonic increasing stack 
        vector<int> res(n.size()), s;
        n.push_back(0);
        // int aa, bb = 10;
        // aa = 3;
        // bb -= aa + 2;
        // cout << bb << endl;
        for (int i = 0; i < n.size(); ++i)
        {
            while (!s.empty() && n[s.back()] >= n[i]) 
            {
                int j = s.back();
                // k is the current rightmost element > n[j] (n[s.back()])
                int k = i - 1;
                s.pop_back();
                // after pop_back(), s.back()+1 is the curr leftmost element > n[j]
                if (!s.empty())
                    // nums between k = i-1 and s.back()
                    // since this is zero index so we minus an extra 1 here
                    k = k - (s.back() + 1) + 1 - 1;
                res[k] = max(res[k], n[j]);
            }
            s.push_back(i);
        }
        // update non-touched windows using result for larger windows
        // Hence we take max between res[i] and res[i+1]: if there is a bigge value in a bigger subarray, that's a better value for a shorter subarray too
        // another explanation:
        // Since not all window size are touched, we need to make sure those non-touched windows have their maximum-minimum value by moving and updating from large window sizes to smaller windows sizes. This is because, max-min value for larger windows will definitely less than or equals to max-min value for smaller window sizes.
        // that is to say, max-min value for larger windows will definitely less than or equals to max-min value for smaller window sizes
        for (int i = res.size() - 1; i > 0; --i)
            res[i - 1] = max(res[i - 1], res[i]);
        return res;
    }
};



// Explanation
// Monotonic stack is very powerful and it has several important features, here let's take mono-increase stack as an example (mono-decrease stack will be the opposite)
// Given arr = [5,3,1,7,6]
// index = 0, mono_increase_stack = [5]
// index = 1, mono_increase_stack = [3]
// index = 2, mono_increase_stack = [1]
// index = 3, mono_increase_stack = [1,7]
// index = 4, mono_increase_stack = [1,6]
// Features of mono-increase stack
// The bottom of stack is the smallest number met so far
// 5 is the smallest up until index 0
// 3 is the smallest up until index 1
// 1 is the samllest up until index 2, 3, 4
// Values in stack is always mono-increase (same thing holds if you chose to store indices in mono-stack)
// The number being pop out from stack is defintely greater than or equals to the current value
// When index = 1, we need to pop out 5 from the stack and push in 3 to comply with the mononotic characteristics, here 5 >= 3
// The second value from the top of the stack is the first value less than the value at the top of stack, reading from right to left
// When index = 3, you will find 1 is the first number less than 7 reading from right to left
// We can also say 1 is the smallest between index 2 and index 3
// Same logic holds when index = 4
// Let's define a couple things here to better express this feature:
// When index = i, the current value is arr[i]
// current_value = arr[i], current_idx = i
// stack_top_value = stack[-1] if len(stack) >= 1
// stack_top_idx = index of stack[-1] in arr
// second_stack_top_value = stack[-2] if len(stack) >= 2
// second_stack_top_idx = index of stack[-2] in arr
// Given above definition, if we adding previous three points 2, 3 & 4 together, we will get:
// The stack_top_value is the smallest value between second_stack_top_idx + 1 and current_idx - 1

// From bullet point 2 & 4, we know that any number in between second_stack_top_idx + 1 and stack_top_idx is greater than stack_top_value, because second_stack_top_value is the first value less than stack_top_value reading from right to left. (This is the left part: [second_stack_top_idx+1, stack_top_idx])
// From bullet point 2 & 3, we know that stack_top_value > current_value, but due to monotonic characteristic, stack_top_value will be less than any value between stack_top_idx and current_idx - 1. (This is the right part: [stack_top_idx, current_idx - 1])
// Thus, again we confirmed that stack_top_value is the smallest number between second_stack_top_idx+1 and current_idx - 1, inclusive.
// From all 5 features mentioned above, now you know a little bit more about mono-increase stack. To solve this particular problem, feature 5 is the most important one, because it gives us any linear way to find the minimum number of multiple window sizes.
// It's excellent that we have this feature using mono stack, but the question now becomes:
// Does this method guarantee the maximum value for a certain window size?
// Yes.
// In the previous process, we are always getting the minimum for different windows
// At the mean time, remember in bullet point 1 & 3, the values pushed into stack are always the smallest in certain range, thus as long as we follow the procedure, we are not gonna miss any small values
// If we can keep track of all the smallest values in certain range, then we will easilly get the maximum among them all.
// Does it guarantee to cover all the window size?
// No, not all window size will be touched, but with a tiny tweak, it will definitely touch the largest window size, n = len(nums)
// We append a 0 or a very small number to the end of nums, so it will definitely touch window size n, because now the current_idx is n and when we exhaust the stack by popping it, the second_stack_top_idx will be 0
// Since not all window size are touched, we need to make sure those non-touched windows have their maximum-minimum value by moving and updating from large window sizes to smaller windows sizes. This is because, max-min value for larger windows will definitely less than or equals to max-min value for smaller window sizes.
// Time Complexity: O(N)
// Unfortunately, I am not able to get this solution by myself, this problem take advantage of mono-stack in a very subtle way. I feel more like the solution came first and then the problem was made up on top of it. Not a good day for me.