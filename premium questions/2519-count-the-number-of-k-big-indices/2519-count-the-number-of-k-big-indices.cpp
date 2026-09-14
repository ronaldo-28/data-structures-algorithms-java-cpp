class Fenwick{
private:
    vector<int> bits;
    int n;

    int lowbit(int x){
        return x & -x;
    }

public:
    

    Fenwick (int n){
        this->n = n;
        bits.resize(n, 0);
    }

    void add(int i, int val){
        for(; i< n; i+=lowbit(i)){
            bits[i] += val;
        }

    }

    int query(int r){
        int sum = 0;
        for(; r> 0; r-=lowbit(r)){
            sum += bits[r];
        }

        return sum;
    }
};

class Solution {
public:
    int kBigIndices(vector<int>& nums, int k) {
        
        int n = nums.size();
        Fenwick* fwLeft = new Fenwick(n+1);
        Fenwick* fwRight = new Fenwick(n+1);

        for(int i=0; i<n; i++){
            int val  = nums[i];
            if(i < k){
                fwLeft->add(val, 1);
            }else{
                fwRight->add(val, 1);
            }
        }

        int cnt = 0;


        for(int i=k; i<n-k; i++)
        {
            int val = nums[i];
            fwRight->add(val, -1);

            if(fwLeft->query(val-1) >= k && fwRight->query(val-1) >= k){
                cnt++;
            }
            fwLeft->add(val, 1);
        }


        return cnt;
    }
};