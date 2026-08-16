class Solution
{
public:
    int largestValsFromLabels(vector<int>& values, vector<int>& labels, int numWanted, int useLimit)
    {
        vector<pair<int,int>> list(values.size());
        for(int i=0;i<values.size();i++)
        {
            list[i]={values[i],labels[i]};
        }
        sort(list.begin(),list.end(),greater<pair<int,int>>());
        int count[20001]={0};
        int sum=0;
        for(auto& m:list)
        {
            if(count[m.second]<useLimit)
            {
                count[m.second]++;
                numWanted--;
                sum+=m.first;
            }
            if(numWanted==0) break;
        }
        return sum;
    }
};