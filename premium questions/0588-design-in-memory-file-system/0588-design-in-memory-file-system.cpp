struct File {
    // string path;
    string content;
    bool is_dir;
    map<string, File> files;

    File() = default;
    File(bool isDir) : is_dir(isDir) {

    }
};

class FileSystem {
private:
    File root = File{true};

    static vector<string> split(const string& path) {
    vector<string> parts;
    size_t i = 0, n = path.size();
    while (i < n) {
        while (i < n && path[i] == '/') ++i;
        size_t j = i;
        while (j < n && path[j] != '/') ++j;
        if (j > i) parts.emplace_back(path.substr(i, j - i));
        i = j;
    }
    return parts;
}

public:
    FileSystem() {
    }
    
    vector<string> ls(string path) {
        vector<string> parts = split(path);

        File* curFile = &root;
        for (const string& path : parts) {
            auto it = curFile->files.find(path);
            if (it != curFile->files.end())
                curFile = &curFile->files[path];
        }

        vector<string> res;
        if (curFile->is_dir) {
            for (const auto& f : curFile->files) {
                res.push_back(f.first);
            }
        } else {
            res.push_back(parts.back());
        }

        return res;
    }
    
    void mkdir(string path) {
        vector<string> parts = split(path);
        
        File* curFile = &root; 
        for (const auto& path : parts) {
            auto it = curFile->files.find(path);
            if (it == curFile->files.end()) {
                curFile->files[path] = File(true);
            }
            curFile = &curFile->files[path];
        }
    }
    
    void addContentToFile(string filePath, string content) {
        vector<string> parts = split(filePath);

        File* curFile = &root; 
        for (const auto& path : parts) {
            curFile = &curFile->files[path];
        }

        curFile->content += content;
    }
    
    string readContentFromFile(string filePath) {
        vector<string> parts = split(filePath);

        File* curFile = &root; 
        for (const auto& path : parts) {
            curFile = &curFile->files[path];
        }

        return curFile->content;
    }
};

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem* obj = new FileSystem();
 * vector<string> param_1 = obj->ls(path);
 * obj->mkdir(path);
 * obj->addContentToFile(filePath,content);
 * string param_4 = obj->readContentFromFile(filePath);
 */