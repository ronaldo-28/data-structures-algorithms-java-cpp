class Solution {
    public String validIPAddress(String queryIP) {
        for (Character ch : queryIP.toCharArray()) {
            if (ch == ':') return validIpv6Address(queryIP);
            if (ch == '.') return validIpv4Address(queryIP);
        }

        return "Neither";
    }

    private static String validIpv6Address(String queryIP) {
        int ipPartCount = 0;
        int ipPartLen = 0;

        char[] ipChars = queryIP.toCharArray();
        char ch;
        for (int i = 0; i < ipChars.length; i++) {
            ch = ipChars[i];

            if (ch == ':') {
                ipPartCount++;
                if (i == ipChars.length - 1
                        || ipPartLen == 0
                        || ipPartCount > 7
                        || ipPartLen > 4) {
                    return "Neither";
                }

                ipPartLen = 0;
            }
            // valid ch check
            else if ((ch >= '0' && ch <= '9')
                    || (ch >= 'a' && ch <= 'f')
                    || (ch >= 'A' && ch <= 'F')) {
                ipPartLen++;
            } else {
                return "Neither";
            }
        }

        if (ipPartLen > 4 || ipPartCount < 7) {
            return "Neither";
        }

        return "IPv6";
    }

    private static String validIpv4Address(String queryIP) {

        int ipPartCount = 0;
        int ipPart = 0;
        int ipPartLen = 0;

        char[] ipChars = queryIP.toCharArray();
        char ch;
        for (int i = 0; i < ipChars.length; i++) {
            ch = ipChars[i];

            int chNum = ch - '0';
            if (ch == '.') {
                ipPartCount++;
                if (i == ipChars.length - 1
                        || ipPartLen == 0
                        || ipPartCount > 3
                        || ipPart < 0 || ipPart > 255) {
                    return "Neither";
                }

                if ((ipPartLen == 2 && ipPart < 10) || (ipPartLen == 3 && ipPart < 100)) {
                    return "Neither";
                }

                ipPart = 0;
                ipPartLen = 0;
            } else if (chNum >= 0 && chNum <= 9) {
                ipPart = ipPart * 10 + chNum;
                ipPartLen++;
            } else {
                return "Neither";
            }
        }

        if ((ipPartLen == 2 && ipPart < 10) || (ipPartLen == 3 && ipPart < 100)) {
            return "Neither";
        }
        if (ipPart < 0 || ipPart > 255 || ipPartCount < 3) {
            return "Neither";
        }

        return "IPv4";
    }
}