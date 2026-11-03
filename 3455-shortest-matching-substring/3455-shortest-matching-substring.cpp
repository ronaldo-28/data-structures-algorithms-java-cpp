namespace {
struct all_strstr {
    std::string_view str;
    std::string_view pattern;
    std::size_t* T;

    constexpr void calculate_table() noexcept {
        T[0] = 0;
        for (std::size_t i = 1, len = 0; i != pattern.size();) {
            if (pattern[i] == pattern[len])
                T[i++] = ++len;
            else if (len)
                len = T[len - 1u];
            else
                T[i++] = 0;
        }
    }

    using sentinel = std::default_sentinel_t;

    struct iterator {
        std::string_view str;
        std::string_view pattern;
        std::size_t* T;

        std::size_t i;
        std::size_t j;

        using difference_type = std::ptrdiff_t;
        using value_type = std::string_view;

        constexpr value_type operator*() const noexcept {
            return str.substr(i - pattern.size(), pattern.size());
        }

    private:
        struct arrow_holder {
            value_type v;
            constexpr const value_type* operator->() const&& noexcept {
                return std::addressof(v);
            }
            void operator->() const& = delete;
        };
    public:
        constexpr const arrow_holder operator->() const noexcept {
            return arrow_holder{ operator*() };
        }

        constexpr iterator& operator++() noexcept {
            while (str.size() - i >= pattern.size() - j) {
                if (pattern[j] == str[i]) {
                    ++i;
                    ++j;
                }

                if (j == pattern.size()) {
                    j = T[j-1u];
                    return *this;
                }
                
                if (pattern[j] != str[i]) {
                    if (j)
                        j = T[j-1u];
                    else
                        ++i;
                }
            }
            *this = sentinel{};
            return *this;
        }

        constexpr iterator operator++(int) {
            auto copy = *this;
            ++*this;
            return copy;
        }

        friend constexpr bool operator==(const iterator& l, const iterator& r) noexcept {
            return l.i == r.i;
        }

        friend constexpr bool operator==(const iterator& l, sentinel) noexcept {
            return l.i == -1zu;
        }

        constexpr iterator& operator=(sentinel) noexcept {
            i = -1zu;
            return *this;
        }
    };

    constexpr iterator begin() const noexcept {
        iterator it{
            .str = str,
            .pattern = pattern,
            .T = T,
            .i = 0,
            .j = 0
        };
        ++it;
        return it;
    }

    static constexpr sentinel end() noexcept {
        return {};
    }
};

struct Solution {
    static unsigned shortestMatchingSubstring(std::string_view s, std::string_view p) noexcept {
        std::string_view p1 = p.substr(0, p.find('*'));
        p.remove_prefix(p1.size() + 1u);
        std::string_view p2 = p.substr(0, p.find('*'));
        std::string_view p3 = p.substr(p2.size() + 1u);

        static constexpr std::size_t max_length = 100000;
        static std::size_t T[max_length];

        switch (p1.empty() + p2.empty() + p3.empty()) {
        case 3:
            return 0; 
        case 2: {
            p = p1.empty() ? p2.empty() ? p3 : p2 : p1;
            all_strstr m{ s, p, T };
            m.calculate_table();
            if (m.begin() == m.end())
                return -1u;
            return p.size();
        }
        case 1: {
            if (p1.empty()) {
                p1 = p2;
                p2 = p3;
            } else if (p2.empty())
                p2 = p3;
            all_strstr m1{ s, p1, T };
            m1.calculate_table();
            auto it1 = m1.begin();
            if (it1 == m1.end())
                return -1u;
            all_strstr m2{ std::string_view(std::to_address(it1->end()), std::to_address(s.end())), p2, T + p1.size() };
            m2.calculate_table();
            auto it2 = m2.begin();
            if (it2 == m2.end())
                return -1u;
            unsigned smallest = -1u;
            for (; it1 != m1.end(); ++it1) {
                if (std::to_address(it1->end()) > std::to_address(it2->begin())) {
                    m2.str = std::string_view(std::to_address(it1->end()), std::to_address(s.end()));
                    it2 = m2.begin();
                    if (it2 == m2.end())
                        return smallest;
                }
                smallest = std::min<unsigned>(smallest, std::distance(std::to_address(it1->begin()), std::to_address(it2->end())));
                if (smallest == p1.size() + p2.size())
                    break;
            }
            return smallest;
        }
        default:
            // All non-empty
            break;
        }
        all_strstr m1{ s, p1, T };
        m1.calculate_table();
        auto it1 = m1.begin();
        if (it1 == m1.end())
            return -1u;
        all_strstr m2{ std::string_view(std::to_address(it1->end()), std::to_address(s.end())), p2, T + p1.size() };
        m2.calculate_table();
        auto it2 = m2.begin();
        if (it2 == m2.end())
            return -1u;
        all_strstr m3{ std::string_view(std::to_address(it2->end()), std::to_address(s.end())), p3, T + p1.size() + p2.size() };
        m3.calculate_table();
        auto it3 = m3.begin();
        if (it3 == m3.end())
            return -1u;

        unsigned smallest = -1u;
        for (; it1 != m1.end(); ++it1) {
            if (std::to_address(it1->end()) > std::to_address(it2->begin())) {
                m2.str = std::string_view(std::to_address(it1->end()), std::to_address(s.end()));
                it2 = m2.begin();
                if (it2 == m2.end())
                    return smallest;
            }
            if (std::to_address(it2->end()) > std::to_address(it3->begin())) {
                m3.str = std::string_view(std::to_address(it2->end()), std::to_address(s.end()));
                it3 = m3.begin();
                if (it3 == m3.end())
                    return smallest;
            }
            smallest = std::min<unsigned>(smallest, std::distance(std::to_address(it1->begin()), std::to_address(it3->end())));
            if (smallest == p1.size() + p2.size() + p3.size())
                break;
        }
        return smallest;
    }
};
}