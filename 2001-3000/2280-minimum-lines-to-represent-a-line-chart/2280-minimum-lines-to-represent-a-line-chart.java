class Solution {
    public int minimumLines(int[][] stockPrices) {
        if(stockPrices.length < 3)
            return stockPrices.length-1;

        int res = 1;
        int a, b, c;
        Arrays.sort(stockPrices, (ax,bx) -> Integer.compare(ax[0], bx[0]));

        a = 0;
        b = 1;
        c = 2;

        while(c < stockPrices.length){
            if(isLine(stockPrices, a, b, c)){
                c++;
            }else {
                res++;
                b= c;
                a = c-1;

                c++;
            }
        }

        return res;
    }

    public boolean isLine(int[][] stockPrices, int a, int b, int c){
        return (stockPrices[b][1] - stockPrices[a][1]) 
                * (stockPrices[c][0] - stockPrices[b][0]) 
                    == (stockPrices[c][1]- stockPrices[b][1])
                         * (stockPrices[b][0] - stockPrices[a][0]);
    }
}