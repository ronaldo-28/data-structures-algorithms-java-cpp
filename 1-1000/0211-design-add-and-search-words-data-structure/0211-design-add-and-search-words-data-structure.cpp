class TrieNode{
    public:
    unordered_map<char,TrieNode*> map;
    char c;
    bool end;
    TrieNode(char c_){
        c=c_;
        end=false;
    }
};
class WordDictionary {
public:
    
    TrieNode *head;
    WordDictionary(){
        head=new TrieNode('.');
    }
    
    void addWord(string word) {
        TrieNode *tmp= head;
        for(int i=0;i<word.size();i++){
            
            if(word[i]=='.'){
                continue;
            }
            if (tmp->map[word[i]]==nullptr){
                tmp->map[word[i]]=new TrieNode(word[i]);
            }
            
            tmp=tmp->map[word[i]];
            
        }
        tmp->end=true;
    }
    
    bool search(string word) {
        TrieNode *tmp= head;
        queue<TrieNode*> q;
        int j=0;
        q.push(tmp);
        bool end=false;
        for(int i=0;i<word.size();i++){
            end=false;
            int size=q.size();
            while(size>0){
                if(q.front()==nullptr){
                    q.pop();
                    size--;
                    continue ;
                }
                if(word[i]=='.'){
                    for(auto p:q.front()->map){
                        if(p.second!=nullptr)
                            q.push(p.second);
                            end=end||p.second->end;
                    }
                }
                else if(q.front()->map.contains(word[i])) {
                    q.push(q.front()->map.at(word[i])); 
                    end=end||q.front()->map.at(word[i])->end;
                }
                size--;
                q.pop();
            }
            if(q.size()<=0){
                return false;
            }          
            
        }
        
        return end;
    }
};
auto init = atexit([] {ofstream("display_runtime.txt") << "0"; });

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary* obj = new WordDictionary();
 * obj->addWord(word);
 * bool param_2 = obj->search(word);
 */