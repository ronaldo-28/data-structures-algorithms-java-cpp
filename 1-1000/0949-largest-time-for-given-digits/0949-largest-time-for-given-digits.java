class Solution {
    public String largestTimeFromDigits(int[] arr) {
        int maxtime=-1;
        for (int i = 0; i <4 ; i++) {
            for (int j = 0; j <4 ; j++) {

                if(i==j){
                    continue;
                }

                for (int k = 0; k <4 ; k++) {
                    if(k==j || k==i){
                        continue;
                    }
                    int l=6-i-j-k;// 4 position with index 0,1,2,3

                    int hour=arr[i] *10+arr[j];

                    int min=arr[k]*10+arr[l];

                    if (hour<24 && min<60){
                        int totalmin=hour*60+min;
                        maxtime=Math.max(maxtime,totalmin);
                    }

                }
            }
        }

        if (maxtime==-1){
            return "";
        }

        //convert maxtime into hours and min
        int hour=maxtime/60;
        int min=maxtime%60;

        StringBuilder sb=new StringBuilder();

        if (hour<10){
            sb.append('0');
        }
        sb.append(hour);
        sb.append(':');

        if (min<10){
            sb.append('0');
        }
        sb.append(min);

        return sb.toString();
    }
}