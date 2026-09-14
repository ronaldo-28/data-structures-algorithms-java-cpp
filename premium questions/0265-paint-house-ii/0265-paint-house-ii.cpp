class Solution {
public:
    int minCostII(vector<vector<int>>& costs) {
        int n = costs.size(), k = costs[0].size();
        int minVal1 = INT_MAX, minVal2 = INT_MAX, minVal1Color =-1, minVal2Color =-1;

        // base cases
        for(int color =0; color<k; color++)
        {
            if(minVal1 > costs[n-1][color])
            {
                minVal2 = minVal1;
                minVal2Color = minVal1Color;

                minVal1 = costs[n-1][color];
                minVal1Color = color;
            }
            else if(minVal2 > costs[n-1][color])
            {
                minVal2 = costs[n-1][color];
                minVal2Color = color;
            }
        }

        for(int i=n-2;i>=0;i--)
        {   
            int newminVal1 = INT_MAX, newminVal2 = INT_MAX, newminVal1Color =-1, newminVal2Color =-1;

            for(int color = 0; color<k; color++)
            {
                if(color != minVal1Color)
                {
                    costs[i][color] += minVal1;
                }
                else
                {
                    costs[i][color] += minVal2;
                }

                if(newminVal1 > costs[i][color])
                {
                    newminVal2 = newminVal1;
                    newminVal2Color = newminVal1Color;

                    newminVal1 = costs[i][color];
                    newminVal1Color = color;
                }
                else if(newminVal2 > costs[i][color])
                {
                    newminVal2 = costs[i][color];
                    newminVal2Color = color;
                }
            }

            minVal1 = newminVal1;
            minVal1Color = newminVal1Color;
            minVal2 = newminVal2;
            minVal2Color = newminVal2Color;
        }

        return minVal1;
    }
};