class Solution {
    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> mainList = new ArrayList<>();
        int maxSize = 0;
        
        for(int number: groupSizes) {
            if (number > maxSize) {
                maxSize = number;
            }
        }
        

        for(int size = 1; size <= maxSize; size++) {
            List<Integer> innerList = new ArrayList<>(); 

            for(int person = 0; person < groupSizes.length; person++) {
                
                if(groupSizes[person] == size) {
                    innerList.add(person); 
                    
                    
                    if(innerList.size() == size) {
                        mainList.add(innerList);
                        innerList = new ArrayList<>(); 
                    }
                }
            }
        }
        
        return mainList;
    }
}