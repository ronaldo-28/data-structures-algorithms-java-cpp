class Solution {
    struct Room {
        int id;
        int size;
    };
public:
    vector<int> closestRoom(vector<vector<int>>& rooms, const vector<vector<int>>& queries) {
        const auto compare = [](const auto& r1, const auto& r2) noexcept { return r1[0] < r2[0]; };
        const auto idx_compare = [&queries](int i1, int i2) noexcept { return queries[i1][0] < queries[i2][0]; };
        const auto stack_compare = [](int needed_size, int room_size) noexcept { return needed_size >= room_size; };
        ranges::sort(rooms, compare);
        vector<int> idcs;
        idcs.resize(size(queries));
        iota(begin(idcs), end(idcs), 0);
        ranges::sort(idcs, idx_compare);
        vector<Room> stack;
        int i = 0;
        vector<int> ret;
        ret.resize(size(queries), -1);
        for (int idx: idcs) {
            const auto& query = queries[idx];
            for (; i != size(rooms); ++i) {
                const auto& room = rooms[i];
                if (room[0] > query[0]) {
                    break;
                }
                while (!stack.empty()) {
                    if (stack.back().size > room[1])
                        break;
                    stack.pop_back();
                }
                stack.push_back(Room{ room[0], room[1] });
            }
            auto iter = ranges::lower_bound(stack, query[1], stack_compare, &Room::size);
            if (iter == begin(stack))
                ret[idx] = -1;
            else
                ret[idx] = (--iter)->id;
        }
        i = size(rooms) - 1;
        stack.clear();
        for (int idx: ranges::reverse_view(idcs)) {
            const auto& query = queries[idx];
            for (; i >= 0; --i) {
                const auto& room = rooms[i];
                if (room[0] < query[0]) {
                    break;
                }
                while (!stack.empty()) {
                    if (stack.back().size > room[1])
                        break;
                    stack.pop_back();
                }
                stack.push_back(Room{ room[0], room[1] });
            }
            auto iter = ranges::lower_bound(stack, query[1], stack_compare, &Room::size);
            if (iter == begin(stack))
                continue;
            --iter;
            if (ret[idx] == -1 || iter->id - query[0] < query[0] - ret[idx])
                ret[idx] = iter->id; 
        }
        return ret;
    }
};