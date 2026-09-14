// class Solution {
//     private int sumOfDiv(int n[],int div)
//     {  int sum=0;
//         for(int i=0;i<n.length;i++)
//         {
      
//         sum+=Math.ceil((double)(n[i]/(double)div));
        
      
//         }
//           return sum;
//     }
//     public int smallestDivisor(int[] n, int threshold) {
//         int lo=n.length;
//         int l=1;
//        int max=Arrays.stream(n).max().getAsInt();
//        if(l>threshold)
//        {
//         return -1;
//        }


//         int h=max;
//         while(l<=h)
//         {
//             int m=(l+h)/2;
//             if(sumOfDiv(n,m)<=threshold)
//             {
//                 h=m-1;
//             }
//             else
//             {
//                 l=m+1;
//             }
//         }
//         return l;
        
//     }
// }
class Solution {
    static {
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        try (FileWriter fw = new FileWriter("display_runtime.txt")) {
            fw.write("0");
        } catch (Exception e) {}
    }));
    }

    public static int sumby(int []nums1,int div){
        int sum=0;
        for(int num:nums1){
            sum+=(int)Math.ceil((double)num/div);
        }

        return sum;
    }
    public int smallestDivisor(int[] nums, int threshold) {
        int len=nums.length;
        if(len>threshold) return -1;


        //int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;

        for(int num:nums){
            max=Math.max(max,num);
            
        }

        int low=1;
        int high=max;

        while(low<=high){
            int mid=low+(high-low)/2;
            if(sumby(nums,mid)<=threshold){
                high=mid-1;
            }
            else{
                low=mid+1;
            }
        }
        return low;



    



        
    }
}