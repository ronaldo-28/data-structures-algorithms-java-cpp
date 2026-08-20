class Excel {
    vector<vector<int>> values;
    vector<vector<vector<string>>> expressions;
    constexpr static auto hasher = [] (const pair<int, char>& p) {
        return hash<int>{}(p.first) ^ hash<char>{}(p.second);
    };
    unordered_map<pair<int, char>, int, decltype(hasher)> cache;
    void clear(const int row, const char column)
    {
        cache.erase({row, column});
        const int m = values.size();
        const int n = values.front().size();
        for (int i = 0; i < m; ++ i)
        {
            for (int j = 0; j < n; ++ j)
            {
                for (const string& expression: expressions[i][j])
                {
                    if (const size_t pos = expression.find(':');
                        pos == string::npos)
                    {
                        const int r = stoi(expression.substr(1));
                        const char c = expression.front();
                        if (r == row && c == column)
                            clear(i + 1, j + 'A');
                    }
                    else
                    {
                        const string first = expression.substr(0, pos);
                        const string second = expression.substr(pos + 1);
                        const int rb = stoi(first.substr(1));
                        const int re = stoi(second.substr(1));
                        const char cb = first.front();
                        const char ce = second.front();
                        for (int r = rb; r <= re; ++ r)
                        {
                            for (char c = cb; c <= ce; ++ c)
                            {
                               if (c == column && r == row)
                                clear(i + 1, j + 'A');
                            }
                        }
                    }
                }
            }
        }
    }
public:
    Excel(int height, char width): values(height, vector<int>(width - 'A' + 1)), expressions(height, vector<vector<string>>(width - 'A' + 1)) {
        
    }
    
    void set(int row, char column, int val) {
        values[row - 1][column - 'A'] = val;
        expressions[row - 1][column - 'A'].clear();
        clear(row, column);
    }
    
    int get(int row, char column) {
        if (expressions[row - 1][column - 'A'].empty())
            return values[row - 1][column - 'A'];
        if (cache.count({row, column}))
            return cache[{row, column}];
        int res = 0;
        for (const string& expression: expressions[row - 1][column - 'A'])
        {
            if (const size_t pos = expression.find(':');
                pos == string::npos)
            {
                const int r = stoi(expression.substr(1));
                const char c = expression.front();
                res += get(r, c);
            }
            else
            {
                const string first = expression.substr(0, pos);
                const string second = expression.substr(pos + 1);
                const int rb = stoi(first.substr(1));
                const int re = stoi(second.substr(1));
                const char cb = first.front();
                const char ce = second.front();
                for (int r = rb; r <= re; ++ r)
                {
                    for (char c = cb; c <= ce; ++ c)
                    {
                        res += get(r, c);
                    }
                }
            }
        }
        return cache[{row, column}] = res;
    }
    
    int sum(int row, char column, vector<string> numbers) {
        expressions[row - 1][column - 'A'] = numbers;
        clear(row, column);
        return get(row, column);
    }
};

/**
 * Your Excel object will be instantiated and called as such:
 * Excel* obj = new Excel(height, width);
 * obj->set(row,column,val);
 * int param_2 = obj->get(row,column);
 * int param_3 = obj->sum(row,column,numbers);
 */