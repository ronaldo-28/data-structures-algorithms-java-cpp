// Author: Alexander Picon
// GitHub: https://github.com/alexpicon
// LinkedIn: https://www.linkedin.com/in/alexpicon/
// Web: https://chaski.ai/


class Solution {
   public:
    // NOLINTNEXTLINE(readability-identifier-naming,bugprone-easily-swappable-parameters)
    static auto canAliceWin(std::vector<std::string>& alice_words,
                            std::vector<std::string>& bob_words) -> bool {
        BestWords best_word(2, WordRow(ALPHABET_SIZE, nullptr));
        Memo memo(2, MemoRow(ALPHABET_SIZE, MEMO_UNKNOWN));

        for (const auto& word : alice_words) {
            best_word[0][letter_index(word)] = &word;
        }
        for (const auto& word : bob_words) {
            best_word[1][letter_index(word)] = &word;
        }

        const std::string& alice_first_word = alice_words[0];
        const std::size_t first_letter = letter_index(alice_first_word);
        const bool bob_can_win = can_current_player_win(
            best_word, memo, 1, first_letter, alice_first_word);
        return !bob_can_win;
    }

   private:
    static constexpr std::size_t ALPHABET_SIZE = 26;
    static constexpr int MEMO_UNKNOWN = -1;

    using WordRow = std::vector<const std::string*>;
    using BestWords = std::vector<WordRow>;
    using MemoRow = std::vector<int>;
    using Memo = std::vector<MemoRow>;

    static auto letter_index(const std::string& word) -> std::size_t {
        return static_cast<std::size_t>(word[0] - 'a');
    }

    static auto can_current_player_win(const BestWords& best_word, Memo& memo,
                                       std::size_t player, std::size_t letter,
                                       const std::string& word) -> bool {
        if (letter >= ALPHABET_SIZE) {
            return false;
        }

        const std::string* same_letter_word = best_word[player][letter];
        const std::string* next_letter_word =
            (letter + 1 < ALPHABET_SIZE) ? best_word[player][letter + 1]
                                         : nullptr;

        const bool has_same_letter_move =
            same_letter_word != nullptr && *same_letter_word > word;
        const bool has_next_letter_move = next_letter_word != nullptr;

        if (!has_same_letter_move && !has_next_letter_move) {
            return false;
        }

        if (memo[player][letter] != MEMO_UNKNOWN) {
            return memo[player][letter] == 1;
        }

        const std::size_t opponent = 1 - player;
        bool win_with_same_letter = false;
        bool win_with_next_letter = false;

        if (has_same_letter_move) {
            win_with_same_letter = !can_current_player_win(
                best_word, memo, opponent, letter, *same_letter_word);
        }
        if (has_next_letter_move) {
            win_with_next_letter = !can_current_player_win(
                best_word, memo, opponent, letter + 1, *next_letter_word);
        }

        const bool result = win_with_same_letter || win_with_next_letter;
        memo[player][letter] = result ? 1 : 0;
        return result;
    }
};