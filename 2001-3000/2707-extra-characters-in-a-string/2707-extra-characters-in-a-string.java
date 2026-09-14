class Solution {
    class Trie{
        class Node{
            Node[] children;
            boolean eow;
            Node(){
                this.children=new Node[26];
                this.eow=false;
            }
        }
        
        Node root=new Node();

        public void add(String word){
            Node curr=root;
            for(int i=0;i<word.length();i++){
                char ch=word.charAt(i);
                int idx=ch-'a';
                if(curr.children[idx]==null){
                    curr.children[idx]=new Node();
                }
                curr=curr.children[idx];
            }
            curr.eow=true;
        }

        public boolean search(String word){
            Node curr=root;
            for(int i=0;i<word.length();i++){
                char ch=word.charAt(i);
                int idx=ch-'a';
                if(curr.children[idx]==null){
                    return false;
                }
                curr=curr.children[idx];
            }
            return curr.eow;
        }
    }


    public int minExtraChar(String s, String[] dictionary) {
        int n=dictionary.length;
        Trie trie=new Trie();
        for(int i=0;i<n;i++){
            trie.add(dictionary[i]);
        }

        n=s.length();
        int[] dp=new int[n+1];

        for(int i=n-1;i>=0;i--){
            //leave index i
            dp[i]=dp[i+1]+1;
            //take index i
            Trie.Node curr=trie.root;
            for(int j=i;j<n;j++){
                int idx=s.charAt(j)-'a';
                if(curr.children[idx]==null) break;
                curr=curr.children[idx];
                if(!curr.eow) continue;
                dp[i]=Math.min(dp[i],dp[j+1]);
            }
        }

        return dp[0];
    }
}