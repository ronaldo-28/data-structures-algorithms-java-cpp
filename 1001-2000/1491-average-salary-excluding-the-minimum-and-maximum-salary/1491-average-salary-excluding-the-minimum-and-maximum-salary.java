class Solution {
    public double average(int[] salary) {
        double ans=0;
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        double sum=0;
        for(int i=0; i<salary.length; i++){
            sum+=salary[i];
            min=Math.min(salary[i], min);
            max=Math.max(salary[i], max);

        }
        sum=sum-min-max;
        return sum/(salary.length-2);
    }
}