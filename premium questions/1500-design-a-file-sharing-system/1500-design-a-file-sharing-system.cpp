class FileSharing {
public:
    FileSharing(int m) {
        num_chunks = m;

        // fill up pq with all ids (at most 10^4)
        vector<int> tmp(10000);
        iota(tmp.begin(), tmp.end(), 1);
        pq = priority_queue<int, vector<int>, greater<int>>(tmp.begin(), tmp.end());
    }
    
    int join(vector<int> ownedChunks) {
        // use pq to get next id
        // add to the map of chunks to current owners by adding in new id (m1)
        // add user to owned chunks (m2)
        int idx = pq.top();
        pq.pop();
        for (int chunk : ownedChunks) {
            m1[chunk].push_back(idx);
        }
        m2[idx] = ownedChunks;
        return idx;
    }
    
    void leave(int userID) {
        // add userID to pq
        // look through m2 to get all owned chunks
        // iterate through all chunks, and remove all specifications of userID
        pq.push(userID);
        vector<int> chunks = m2[userID];
        for (int chunk : chunks) {
            erase(m1[chunk], userID);
        }
    }
    
    vector<int> request(int userID, int chunkID) {
        // look at map of chunks to current owners (m1), if nonempty, add user to list and sort
        // also add said chunk to m2
        if (m1[chunkID].empty()) {
            return {};
        }

        vector<int> res = m1[chunkID];
        sort(res.begin(), res.end());
        if (find(m1[chunkID].begin(), m1[chunkID].end(), userID) == m1[chunkID].end()) {
            m1[chunkID].push_back(userID);
            m2[userID].push_back(chunkID);
        }
        
        return res;
    }

private:
    // map chunks to current owners (m1)
    // map owner to owned chunks (m2)
    // current ids of all users (use priority queue to get the minimum index that isn't being used)
    unordered_map<int, vector<int>> m1;
    unordered_map<int, vector<int>> m2;
    priority_queue<int, vector<int>, greater<int>> pq;
    int num_chunks;
};

/**
 * Your FileSharing object will be instantiated and called as such:
 * FileSharing* obj = new FileSharing(m);
 * int param_1 = obj->join(ownedChunks);
 * obj->leave(userID);
 * vector<int> param_3 = obj->request(userID,chunkID);
 */