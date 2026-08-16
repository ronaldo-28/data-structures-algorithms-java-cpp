class Solution {
    public int[] rearrangeBarcodes(int[] bar) {
        if(bar.length<=2){
            return bar;
        }
        int i=0;
        int j=1;
        while(j<bar.length){
            if(j==bar.length-1 && bar[i] == bar[j]){
                for(int k=0;k<i;k++){
                    if(bar[k]!=bar[j]){
                        int temp = bar[j];
                        bar[j] = bar[k];
                        bar[k] = temp;
                        break;
                    }
                }
                i=-1;
                j=0;

            }
            else if(bar[i] == bar[j]){
                for(int k=j+1;k<bar.length;k++){
                    if(bar[k]!=bar[j]){
                        int temp = bar[j];
                        bar[j] = bar[k];
                        bar[k] = temp;
                        break;
                    }
                }
            }
            i++;
            j++;
        }
        return bar;
    }
}