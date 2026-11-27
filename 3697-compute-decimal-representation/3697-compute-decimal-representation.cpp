class Solution 
{
public:
    vector<int> decimalRepresentation(int n) 
    {
        int divisor = 1000000000;

        vector<int> ret;

        while (n > 0)
        {
            int divideResult = n / divisor;

            if (divideResult != 0)
            {
                ret.push_back(divideResult * divisor);
            }

            n = n % divisor;
            divisor /= 10;
        }

        return ret;
    }
};