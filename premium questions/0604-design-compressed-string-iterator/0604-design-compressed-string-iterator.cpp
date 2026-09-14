class StringIterator {
public:
    string s;
    int i = 0;
    int r = 0;
    char c;
    StringIterator(string compressedString) {
        s = compressedString;    
    }
    
    char next() {
        if (i == s.size() && r == 0) return ' ';
        if (!r){
            c = s[i];
            ++i;
            int num = 0;
            while (i < s.size() && isdigit(s[i])) num = num * 10 + s[i] - '0', ++i;
            r = num;            
        }
        --r;
        return c;
    }
    
    bool hasNext() {
        return !(i == s.size() && r == 0);
    }
};

/**
 * Your StringIterator object will be instantiated and called as such:
 * StringIterator* obj = new StringIterator(compressedString);
 * char param_1 = obj->next();
 * bool param_2 = obj->hasNext();
 */