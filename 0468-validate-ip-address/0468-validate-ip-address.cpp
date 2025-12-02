class Solution {
private:
    // --- IPv4 Validation Helper ---
    bool is_valid_ipv4(const string& IP) {
        // Use stringstream to split the IP by the '.' delimiter
        stringstream ss(IP);
        string segment;
        int count = 0;

        // The input string must not start or end with a delimiter (handled by the split process if not careful)
        if (IP.find('.') == string::npos) return false;

        // Check if there are exactly 4 segments separated by '.'
        while (getline(ss, segment, '.')) {
            count++;

            // Rule 1: xi must not be empty
            if (segment.empty() || segment.length() > 3) {
                return false;
            }

            // Rule 2: xi cannot have leading zeros (unless xi is "0")
            if (segment.length() > 1 && segment[0] == '0') {
                return false;
            }

            // Rule 3: xi must contain only digits and be <= 255
            for (char c : segment) {
                if (!isdigit(c)) {
                    return false;
                }
            }

            try {
                int val = stoi(segment);
                if (val < 0 || val > 255) {
                    return false;
                }
            } catch (const exception& e) {
                // Should not happen if Rule 3 is enforced, but for robustness
                return false;
            }
        }

        // Rule 4: Must have exactly 4 segments
        // The split process can sometimes miss trailing empty segments if not carefully implemented.
        // Check for boundary condition: "1.1.1.1." would result in 4 segments, but is invalid.
        if (count != 4 || IP.back() == '.') {
            return false;
        }

        // Final check on the number of delimiters vs segments
        int delimiter_count = 0;
        for (char c : IP) {
            if (c == '.') delimiter_count++;
        }
        if (delimiter_count != 3) return false;

        return true;
    }

    // --- IPv6 Validation Helper ---
    bool is_valid_ipv6(const string& IP) {
        // Use stringstream to split the IP by the ':' delimiter
        stringstream ss(IP);
        string segment;
        int count = 0;

        // The input string must not start or end with a delimiter
        if (IP.find(':') == string::npos || IP.back() == ':') return false;

        while (getline(ss, segment, ':')) {
            count++;

            // Rule 1: 1 <= xi.length <= 4
            if (segment.empty() || segment.length() > 4) {
                return false;
            }

            // Rule 2: xi is a hexadecimal string
            for (char c : segment) {
                if (!isxdigit(c)) {
                    return false;
                }
            }
        }

        // Rule 3: Must have exactly 8 segments
        // Similar check for number of delimiters (7) vs segments (8)
        int delimiter_count = 0;
        for (char c : IP) {
            if (c == ':') delimiter_count++;
        }
        if (delimiter_count != 7 || count != 8) return false;

        return true;
    }

public:
    string validIPAddress(string queryIP) {
        if (queryIP.find('.') != string::npos) {
            // Potential IPv4
            if (is_valid_ipv4(queryIP)) {
                return "IPv4";
            }
        } else if (queryIP.find(':') != string::npos) {
            // Potential IPv6
            if (is_valid_ipv6(queryIP)) {
                return "IPv6";
            }
        }

        return "Neither";
    }
};