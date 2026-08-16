class Solution {
    /*
    We will find an optimal point, where half of ones will be to the left (leftLen),
    and the rest to the right (rightLen).
    In case k is not even, right part will take the extra element.
    Now we will be moving the point from left to right and counting how many
    shifts we need to make to get leftLen of ones to be to the left of the point
    and rightLen to the right.
    The idea is that when we move the point, we can reuse the data we already know
    to make sure we don't overcalculate the same stuff on and on.
    For example: X00101 - to move boths points (rightLen = 2) to X, we need 2+3 moves.
    This is easy to calculate by finding ones and substructing indexes. Now let's move
    X to the right: X00101. We could use indexes again - or just take see that every one
    became one step close -> if I take previous steps and remove rightLen, then I have the answer.
    Similar logic applies to the left part - every move every of leftLen moves away.
    The only catch is if we move past one. If one migrates from right part to left part, then:
    - we need to remove the leftest one used (as we have to use only leftLen of ones)
    - we need to add another one to the right, as we just gave it away
    - as soon as we don't have enough ones to the right, we are done
    */
    public int minMoves(int[] nums, int k) {
        if (k == 1) return 0;
        final int leftLen = k/2;
        final int rightLen = k-leftLen;// this will cover both odd and even k
        int divider = 0; // this is the magic point, it represent the first element of right subset
        // it is pretty clear, that it has to be set right after leftLen of ones is read
        int onesSoFar = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                onesSoFar++;
            }
            if (onesSoFar == leftLen) {
                divider = i+1;
                break;
            }
        }
        /*
        No for both left and right we need to get inital sums of steps
        Also we track the most left index of one, and same for most right used so far.
        Later, when the divider point moves over one, we will move left index to the next one,
        and same for right.
        */
        int leftSum = 0;
        int rightSum = 0;
        int positionToFill = divider - 1;
        /*
        Clearly we need to track which position we want one to move to.
        If divider is at index 10. The 3 ones (if k is 6 or 7) need to move to 7,8,9
        Which we fill starting from divider and to the left.
        Mirrir logic applies to the right.
        */
        int leftestOnePointer = -1;
        int rightestOnePointer = 0;
        for (int i = divider - 1; i >= 0; i--) {
            if (nums[i] == 1) {
                leftestOnePointer = i;
                leftSum += positionToFill - i;
                positionToFill--;
            }
        }
        positionToFill = divider;
        int consumedOnes = 0;
        for (int i = divider; (i < nums.length)&&(consumedOnes < rightLen); i++) {
            if (nums[i] == 1) {
                rightestOnePointer = i;
                rightSum += i - positionToFill;
                positionToFill++;
                consumedOnes++;
            }
        }
        /*
        This is the moment when we have intial data.
        We know that to move leftLen of ones to divider - it takes leftSum of steps.
        And same for right part.
        */
        int result = leftSum + rightSum;
        while (divider < nums.length - rightLen) {
            // move divider right
            divider++;
            if (nums[divider-1] == 0) {
                /*
                Simplest case, divider moved past zero:
                1 0 0 0 1
                    ^
                All ones to the left became one step away: 1*left
                All ones to the right became one step close: 1*rightLen
                */
                leftSum += leftLen;
                rightSum -= rightLen;
                result = Math.min(result, leftSum + rightSum);
            } else {
                /*
                The "main" case. One migrates from right to left.
                */
                leftSum += leftLen;
                /*
                we need to remove extra steps, as we don;t use the leftest one any more
                The new one is already at a good position, it contributes nothing
                */
                leftSum -= (divider - leftestOnePointer - 1);
                leftestOnePointer++;
                // and move left to the next one we are using
                while (nums[leftestOnePointer] != 1) leftestOnePointer++;
                /*
                Now we need to find next available one to replace the one we just gave away
                */
                rightestOnePointer += 1;
                while (rightestOnePointer < nums.length && nums[rightestOnePointer] != 1) {
                    rightestOnePointer++;
                }
                if (rightestOnePointer == nums.length) {
                    // happens if no more ones
                    break;
                }
                rightSum += rightestOnePointer - divider - rightLen + 1;
                result = Math.min(result, leftSum + rightSum);
            }
        }
        return result;
    }
}