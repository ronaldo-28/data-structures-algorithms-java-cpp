/**
 * Definition for a category handler.
 * class CategoryHandler {
 *     public CategoryHandler(int[] categories);
 *     public boolean haveSameCategory(int a, int b);
 * };
 */
class Solution {
	public int numberOfCategories(int n, CategoryHandler categoryHandler) {
        int uniqueCategories = 0;
        
        for (int i = 0; i < n; i++) {
            boolean foundMatch = false;
            for (int j = 0; j < i; j++) {
                if (categoryHandler.haveSameCategory(i, j)) {
                    foundMatch = true;
                    break;
                }
            }
            if (!foundMatch) {
                uniqueCategories++;
            }
        }
        
        return uniqueCategories;
    	
	}
    // class Union{
    //     int par[];
    // }
}