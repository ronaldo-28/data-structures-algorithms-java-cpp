class Solution {
    public String discountPrices(String sentence, int discount) {
        char[] chars = sentence.toCharArray();
        StringBuilder builder = new StringBuilder();
        int position = 0;
        while(position < chars.length) {
            position = parseWord(builder, position, chars, discount);
            position = parseSpace(builder, position, chars);
        }
        return builder.toString();
    }

    int parseWord(StringBuilder builder, int position, char[] chars,
    int discount) {
        if(chars[position] == '$') {
            builder.append(chars[position]);
            return parseNumber(builder, position + 1, chars, discount);
        } else {
            return skip(builder, position, chars);
        }
    }

    int parseNumber(StringBuilder builder, int position, char[] chars, int discount) {
        long number = 0;
        int start = position;
        while(position < chars.length 
                && chars[position] != ' ') {
            if(chars[position] >= '0' 
                    && chars[position] <= '9') {
                    number = number * 10 + (chars[position] - '0');
                    builder.append(chars[position]);
                    position++;
            } else {
                return skip(builder, position, chars);
            } 
        }
        if(start != position) {
            long price = number;
            price = price * 100 - price * discount;
            builder.setLength(builder.length() - (position - start));
            builder.append(price / 100);
            builder.append(".");
            builder.append((price / 10) % 10);
            builder.append(price % 10);
        }
        return position;
    }

    int skip(StringBuilder builder, int position, char[] chars) {
        while(position < chars.length) {
            if(chars[position] == ' ') {
                return position;
            }
            builder.append(chars[position]);
            position++;
        }
        return position;
    }

     int parseSpace(StringBuilder builder, int position, char[] chars) {
        if(position < chars.length && chars[position] == ' ') {
            builder.append(chars[position]);
            position++;
        }
        return position;
     }
}