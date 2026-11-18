class Solution {
public:
    static constexpr std::string_view hex_map = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static char get_char(int value) {
        if (value >= 10 && value <= 35) {
            return hex_map[value - 10];
        }   
        return value + '0';
    }

    string concatHex36(int n) {
        int square = n * n;
        int cube = square * n;
        std::string res{};
        
        while(square != 0){
            auto [q, r] = std::div(square, 16);
            res += get_char(r);
            square = q;
        }
        std::ranges::reverse(res);

        std::string cube_str{};
        while(cube != 0){
            auto [q, r] = std::div(cube, 36);
            cube_str += get_char(r);
            cube = q;
        }

        std::ranges::reverse(cube_str);
        res += std::move(cube_str);
        
        return res;
    }
};