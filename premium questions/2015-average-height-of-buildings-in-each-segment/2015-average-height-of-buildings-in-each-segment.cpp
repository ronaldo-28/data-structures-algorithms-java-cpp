// Author: Alexander Picon
// GitHub: https://github.com/alexpicon
// LinkedIn: https://www.linkedin.com/in/alexpicon/
// Web: https://chaski.ai/


class Solution {
   public:
    // NOLINTNEXTLINE(readability-identifier-naming)
    static auto averageHeightOfBuildings(
        std::vector<std::vector<int>>& buildings)
        -> std::vector<std::vector<int>> {
        struct Event {
            int x_coord;
            std::int64_t height_delta;
            int count_delta;
        };

        std::vector<Event> events;
        events.reserve(buildings.size() * 2);
        for (const std::vector<int>& building : buildings) {
            const int start = building[0];
            const int end = building[1];
            const int height = building[2];
            events.push_back(
                {.x_coord = start, .height_delta = height, .count_delta = 1});
            events.push_back(
                {.x_coord = end, .height_delta = -height, .count_delta = -1});
        }

        std::ranges::sort(events, [](const Event& lhs, const Event& rhs) {
            return lhs.x_coord < rhs.x_coord;
        });

        std::vector<std::vector<int>> result;
        std::int64_t height_sum = 0;
        int count = 0;

        for (std::size_t idx = 0; idx < events.size();) {
            const int current_x = events[idx].x_coord;
            while (idx < events.size() && events[idx].x_coord == current_x) {
                height_sum += events[idx].height_delta;
                count += events[idx].count_delta;
                ++idx;
            }

            if (idx < events.size() && count > 0) {
                const int next_x = events[idx].x_coord;
                const int average = static_cast<int>(height_sum / count);
                if (!result.empty() && result.back()[2] == average &&
                    result.back()[1] == current_x) {
                    result.back()[1] = next_x;
                } else {
                    result.push_back({current_x, next_x, average});
                }
            }
        }

        return result;
    }
};