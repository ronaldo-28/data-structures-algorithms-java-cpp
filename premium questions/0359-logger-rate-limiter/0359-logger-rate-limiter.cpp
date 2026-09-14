class Logger {
public:
    Logger() = default;

    bool shouldPrintMessage(int timestamp, std::string message) {
        auto it = last_.find(message);
        // 邊界：>= 10 才放行（t=1 印過之後 t=11 可以再印）
        if (it != last_.end() && timestamp - it->second < 10) return false;
        last_[message] = timestamp;      // 只有成功才更新
        return true;
    }

private:
    std::unordered_map<std::string, int> last_;
};

/**
 * Your Logger object will be instantiated and called as such:
 * Logger* obj = new Logger();
 * bool param_1 = obj->shouldPrintMessage(timestamp,message);
 */