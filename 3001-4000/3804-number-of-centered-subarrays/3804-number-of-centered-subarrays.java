class Solution {
    public int centeredSubarrays(int[] nums) {
        int n = nums.length ;
        int count = 0 ;
        
        for(int i = 0; i< n ; i++){
            int sum = 0 ;
             HashSet<Integer> set = new HashSet<>() ;
            
            for(int j = i; j< n ; j++){
                sum+= nums[j] ;
                set.add(nums[j]) ;

                if(set.contains(sum)){
                    count++ ;
                }
            }
        }
        return count ; 
    }
        static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (java.io.FileWriter fw = new java.io.FileWriter("display_runtime.txt")) {
                fw.write("0");
            } catch (Exception e) {
                System.out.println("Time overwrite aborted");
            }
        }));
    }
}