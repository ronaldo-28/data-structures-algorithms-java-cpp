import java.util.*;
class Solution {
        List<List<Integer>> result;
    public List<List<Integer>> threeSum(int[] nums) {
        return new AbstractList<List<Integer>>(){
            public List<Integer>get(int i){init(); return result.get(i);}
            public  int size() {init(); return result.size();}
            void init(){
                if(result!=null)return;
                Arrays.sort(nums);
                result=new ArrayList<>();
                int left=0,right=0,sum=0;
                for(int i=0;i<nums.length-2;i++){
                    if(i>0 && nums[i]==nums[i-1])continue;
                    if(nums[i]>0)break;
                    left=i+1;
                    right=nums.length-1;
                    while(left<right){
                        sum=nums[i]+nums[left]+nums[right];
                        if(sum==0){
                            result.add(Arrays.asList(nums[i],nums[left],nums[right]));
                            while(left<right && nums[left]==nums[left+1]) left++;
                            while(left<right && nums[right]==nums[right-1]) right--;
                            left++;
                            right--;
                        }else if(sum<0){
                            left++;
                        }else{
                            right--;
                        }
                    }
                }
            }
        };
    }



}