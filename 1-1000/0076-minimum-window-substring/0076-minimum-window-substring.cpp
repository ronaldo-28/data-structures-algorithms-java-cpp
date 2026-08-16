class Solution {
public:
    string minWindow(string s, string t) {
        if(t.size()>s.size())
            return "";
        vector<int> freq(256,0);
        for(char c:t)
            freq[c]++;
        
        int req=t.size(),start=0,minlen=INT_MAX,left=0;
        for(int end=0;end<s.size();end++)
        {
            if(freq[s[end]]>0)
                req--;
            freq[s[end]]--;

            while(req==0)
            {
                if(end-start+1<minlen)
                {
                    minlen=end-start+1;
                    left=start;
                }
                freq[s[start]]++;
                if(freq[s[start]]>0)    
                    req++;
                start++;
            }
        }
        return minlen==INT_MAX?"":s.substr(left,minlen);
        
    }
};