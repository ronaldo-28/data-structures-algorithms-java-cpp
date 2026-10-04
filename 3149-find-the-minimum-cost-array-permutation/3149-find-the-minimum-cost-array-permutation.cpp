class Solution {
public:
    vector<int> findPermutation(vector<int>& nums) {
        int n=nums.size();
        int MINMIN=INT_MAX;
        vector<bool> visited(n,false); visited[0]=true;
        vector<int> num2index(n);
        for(int i=0;i<n;i++) num2index[nums[i]]=i;
        [&](this auto&&self, int last, int preSum){
            if(preSum>MINMIN) return;
            bool flag=false;
            for(int bward=last-1;bward>=0;bward--) if(!visited[num2index[bward]]) {
                visited[num2index[bward]]=true;
                self(num2index[bward],preSum+last-bward);
                visited[num2index[bward]]=false;
                flag=true; break;
            }
            if(!visited[num2index[last]]) {
                visited[num2index[last]]=true;
                self(num2index[last],preSum);
                visited[num2index[last]]=false;
                flag=true;
            }            
            for(int fward=last+1;fward<n;fward++) if(!visited[num2index[fward]]) {
                visited[num2index[fward]]=true;
                self(num2index[fward],preSum+fward-last);
                visited[num2index[fward]]=false;
                flag=true; break;
            }
            if(!flag&&preSum+abs(last-nums[0])<MINMIN) MINMIN=preSum+abs(last-nums[0]);
        }(0,0);
        vector<int> curr(1,0); 
        [&](this auto&&self,int bias){
            if(bias<0) return false;
            if(curr.size()==n) if(abs(curr.back()-nums[0])==bias) return true; else return false; else;
            int last=curr.back(); bool flag=false;
            for(int i=1;i<n;i++)
                if(visited[i]) continue; 
                else {
                    curr.push_back(i);
                    visited[i]=true;
                    if(self(bias-abs(last-nums[i]))) { flag=true; break; }
                    curr.pop_back();
                    visited[i]=false;
                }
            return flag;
        }(MINMIN);
        return curr;
    }

};