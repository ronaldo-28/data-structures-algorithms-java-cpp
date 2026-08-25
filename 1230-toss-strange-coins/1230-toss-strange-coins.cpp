// Author: Alexander Picon
// GitHub: https://github.com/alexpicon
// LinkedIn: https://www.linkedin.com/in/alexpicon/
// Web: https://chaski.ai/


class Solution {
   public:
    // NOLINTNEXTLINE(readability-identifier-naming)
    static auto probabilityOfHeads(std::vector<double>& prob, int target)
        -> double {
        auto const num_coins = std::ssize(prob);
        std::vector<double> heads_prob(target + 1, 0.0);
        heads_prob[0] = 1.0;

        for (int coin_idx = 0; coin_idx < num_coins; coin_idx++) {
            double const coin_prob = prob[coin_idx];
            for (int heads_count = std::min(coin_idx + 1, target);
                 heads_count >= 1; heads_count--) {
                heads_prob[heads_count] =
                    (heads_prob[heads_count] * (1 - coin_prob)) +
                    (heads_prob[heads_count - 1] * coin_prob);
            }
            heads_prob[0] *= (1 - coin_prob);
        }

        return heads_prob[target];
    }
};