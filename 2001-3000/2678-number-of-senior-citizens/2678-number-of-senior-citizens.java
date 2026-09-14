class Solution {
    public int countSeniors(String[] details) {
        int seniorCount = 0;

        for (String person : details) {
            int age = getAge(person);

            if (age > 60) {
                seniorCount++;
            }
        }

        return seniorCount;
    }

    private int getAge(String detail) {
        int tens = detail.charAt(11) - '0';
        int ones = detail.charAt(12) - '0';

        return tens * 10 + ones;
    }
}