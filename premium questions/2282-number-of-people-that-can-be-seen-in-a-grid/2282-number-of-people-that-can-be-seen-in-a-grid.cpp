// Author: Alexander Picon
// GitHub: https://github.com/alexpicon
// LinkedIn: https://www.linkedin.com/in/alexpicon/
// Web: https://chaski.ai/


class Solution {
   public:
    // NOLINTNEXTLINE(readability-identifier-naming)
    static auto seePeople(std::vector<std::vector<int>>& heights)
        -> std::vector<std::vector<int>> {
        const std::size_t rows = heights.size();
        const std::size_t cols = rows == 0 ? 0 : heights[0].size();

        std::vector<std::vector<int>> result(rows, std::vector<int>(cols, 0));
        std::vector<int> stack;

        for (std::size_t row = 0; row < rows; ++row) {
            stack.clear();
            for (std::size_t col = cols; col-- > 0;) {
                result[row][col] = count_visible(heights[row][col], stack);
            }
        }
        for (std::size_t col = 0; col < cols; ++col) {
            stack.clear();
            for (std::size_t row = rows; row-- > 0;) {
                result[row][col] += count_visible(heights[row][col], stack);
            }
        }
        return result;
    }

   private:
    static auto count_visible(int current_height, std::vector<int>& stack)
        -> int {
        int visible = 0;
        while (!stack.empty() && stack.back() < current_height) {
            stack.pop_back();
            ++visible;
        }
        if (!stack.empty()) {
            ++visible;
        }
        if (!stack.empty() && stack.back() == current_height) {
            stack.pop_back();
        }
        stack.push_back(current_height);
        return visible;
    }
};