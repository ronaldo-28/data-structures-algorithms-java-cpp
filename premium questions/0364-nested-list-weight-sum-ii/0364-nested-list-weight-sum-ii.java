/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {

    /**
    Sum of (integer * (maxDepth - (the depth of the integer) + 1))
     = Sum of (integer * ((maxDepth + 1) - (the depth of the integer)))
      = (
        a[i] * (maxDepth+1 - depth[a[i]]) 
        + a[i-1] * (maxDepth+1 - depth[a[i-1]])
        .............
        + a[1] * (maxDepth+1 - depth[a[1]])
        )
    = (
        ((a[i] * (maxDepth+1)) - (a[i] * depth[a[i]]))
        + ((a[i-1] * (maxDepth+1)) - (a[i-1] * depth[a[i-1]]))
        .....
        + ((a[1] * (maxDepth+1)) - (a[1] * depth[a[1]]))

        = ((maxDepth+1)*(a[i] + a[i-1] + ... + a[1]))
        -
        ((a[i] * depth[a[i]]) 
        + (a[i-1] * depth[a[i-1]]) 
        ...........
        + (a[1] * depth[a[1]])) 
    )
     */

/**
((maxDepth+1)*(a[i] + a[i-1] + ... + a[1]))
        -
        ((a[i] * depth[a[i]]) 
        + (a[i-1] * depth[a[i-1]]) 
        ...........
        + (a[1] * depth[a[1]]))
 */
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int depth = 1;
        int res = 0;

        Queue<NestedInteger> q = new LinkedList<>();
        q.addAll(nestedList);
        int sumInt = 0;
        int sumProduct = 0;

        while(!q.isEmpty()){
            int l = q.size();

            for(int i=0; i<l; i++){
                NestedInteger curr = q.poll();
                if(curr.isInteger()){
                    sumInt += curr.getInteger();
                    sumProduct += (depth * curr.getInteger());
                }
                else{
                    List<NestedInteger> list = curr.getList();
                    q.addAll(list);
                }  
            }
            depth++;
        }

        res = (depth * sumInt) - sumProduct;
        return res;

    }
}