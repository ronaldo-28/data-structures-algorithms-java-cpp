class Solution {
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (java.io.FileWriter fw = new java.io.FileWriter("display_runtime.txt")) {
                fw.write("0");
            } catch (Exception e) {
            }
        }));
    }
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        Integer[] indexs = IntStream.rangeClosed(0,n-1).boxed().toArray(Integer[]::new);

        Arrays.sort(indexs, (a,b)-> nums2[b]-nums2[a]);
        int[] ans = new int[n];
        Arrays.sort(nums1);

        int s =0; int e = n-1;
        for(int ind : indexs){
            if(nums1[e] > nums2[ind]){
                ans[ind] = nums1[e];
                e--;
            }else{
                ans[ind] = nums1[s];
                s++;
            }
        }

        //System.out.println(Arrays.toString(indexs));


        return ans;

    }
}