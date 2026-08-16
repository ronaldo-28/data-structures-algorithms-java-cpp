class Solution {
public:
    int maximumWhiteTiles(vector<vector<int>>& tiles, int l) {
        
         vector< pair<int,int> > a;
         for(int i=0;i<tiles.size();i++ )
         {
            a.push_back({tiles[i][0],tiles[i][1]});
         }

         sort(a.begin(),a.end());
         vector<int> pref(a.size()+1,0);
         int ans=0;
         for(int i=0;i<a.size();i++ )
         {
            pref[i+1]=pref[i]+(a[i].second-a[i].first+1);
         }

         for(int i=0;i<a.size();i++ )
         {
             int s=i,e=a.size()-1;
             int target=a[i].first+l-1;
             int idx=i;
             while(s<=e)
             {
                 int mid=(s+e)/2;

                 if(a[mid].first<=target )
                 {
                    idx=mid;
                    s=mid+1;
                 }
                 else
                 {
                    e=mid-1;
                 }
             }

             //target 
             int rn=pref[idx]-pref[i];
            if(idx<a.size()) 
            {
                int val=max(0,min(target,a[idx].second)-a[idx].first+1);
                rn +=val;
            }
             ans=max(ans,rn);

         }

         return ans;
    }
};