class Solution {
public:

/*
abcdefghi j k l

aple

p->2
a->1
l->1
e->1


first 9
second 9
third = 8


freq[26]
pq max heap {freq, char}

start mapping to positions
first 9 in the heap will be at pos 1
second 9 in second position
third 8 in third positin


loop over characters
sum  (freq*position)

time
static size of letters 26
assume n is the count of different letters max 26
m is the size of the string

m + nlogn

space 
o(n)

apple

a->1
p->2
l->1
2>1




2
1
1
1




*/
    int minimumKeypresses(string s) {
        int n = s.size();
        int freq[26] = {};
        priority_queue<int> pq; //freq

        for(auto i: s){
            freq[i - 'a']++;
        }

        for(int i = 0; i < 26; i++){
            if(freq[i] > 0){
                pq.push(freq[i]);
            }
        }

        int ans = 0;
        int first = 9, second = 9, third = 8;
        while(pq.size()){
            int top = pq.top();
            pq.pop();

            int cur;
            if(first > 0){
                cur = 1;
                first--;
            }else if(second > 0){
                cur = 2;
                second--;
            }else{
                cur = 3;
                third--;
            }

            ans += (cur * top);
        }
        return ans;


        
    }
};