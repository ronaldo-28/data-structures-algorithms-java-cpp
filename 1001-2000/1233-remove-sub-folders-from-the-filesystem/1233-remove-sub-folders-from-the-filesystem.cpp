bool rankBySize(const string &s1, const string &s2)
{
    return s1.size() < s2.size();
}


class Solution {
public:
    vector<string> removeSubfolders(vector<string>& folder) {
        sort(folder.begin(), folder.end(), rankBySize);

        unordered_set<string> ans;
        ans.insert(folder[0]);

        for(int i = 1; i < folder.size(); i++)
        {
            string &s = folder[i];
            string subs = "/";

            bool find_root = false;
            for(int j = 1; j < s.size(); j++)
            {
                if(s[j] != '/')
                {
                    subs += s[j];
                }
                else
                {
                    if(ans.find(subs) != ans.end())
                    {
                        find_root = true;
                        break;
                    }
                    else
                    {
                        subs += '/';
                    }
                }
            }

            if(!find_root)
            {
                ans.insert(s);
            }
        }

        return vector<string>(ans.begin(), ans.end());
    }
};