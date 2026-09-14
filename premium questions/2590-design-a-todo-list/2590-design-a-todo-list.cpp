class TodoList {
  public:
    TodoList() {}

    int addTask(int userId, std::string taskDescription, int dueDate, std::vector<std::string> tags) {
        const int id = static_cast<int>(tasks_.size()) + 1;      // 🔴 全域遞增，不是每人各自從 1
        tasks_.push_back({userId, dueDate, std::move(taskDescription), std::move(tags), false});
        userTasks_[userId].push_back(id);                        // 🔴 索引：這個人有哪些 taskId
        return id;
    }

    std::vector<std::string> getAllTasks(int userId) { return query(userId, nullptr); }

    std::vector<std::string> getTasksForTag(int userId, std::string tag) { return query(userId, &tag); }

    void completeTask(int userId, int taskId) {
        if (taskId < 1 || taskId > static_cast<int>(tasks_.size())) return;
        Task& t = tasks_[static_cast<std::size_t>(taskId - 1)];
        if (t.owner != userId) return;                           // 🔴 閘門：不是自己的不能動
        t.done = true;                                           // 已完成再設一次無害
    }

  private:
    struct Task {
        int owner;
        int due;
        std::string desc;
        std::vector<std::string> tags;
        bool done;
    };
    std::vector<Task> tasks_;
    std::unordered_map<int, std::vector<int>> userTasks_;

    std::vector<std::string> query(int userId, const std::string* tag) {
        std::vector<std::pair<int, int>> picked;                 // (到期日, taskId)
        auto it = userTasks_.find(userId);
        if (it != userTasks_.end())
            for (int id : it->second) {
                const Task& t = tasks_[static_cast<std::size_t>(id - 1)];
                if (t.done) continue;                            // 🔴 閘門：只回未完成
                if (tag && std::find(t.tags.begin(), t.tags.end(), *tag) == t.tags.end())
                    continue;                                    // 🔴 閘門：帶標籤時才要過濾
                picked.push_back({t.due, id});
            }
        std::sort(picked.begin(), picked.end());                 // 🔴 (到期日, taskId) ⇒ 同日時依插入順序
        std::vector<std::string> out;
        out.reserve(picked.size());
        for (const auto& p : picked) out.push_back(tasks_[static_cast<std::size_t>(p.second - 1)].desc);
        return out;
    }
};