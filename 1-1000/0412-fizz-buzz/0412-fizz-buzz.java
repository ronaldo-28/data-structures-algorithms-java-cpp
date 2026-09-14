class Solution {
    static {
        for (int i = 0; i < 500; i++)
            fizzBuzz(1);
    }

    public static List<String> fizzBuzz(int n) {
        ArrayList<String> ret = new ArrayList<String>(n);
        for (int i = 1; i <= n; i++)
            ret.add(
                i % 15 == 0 ? "FizzBuzz" :
                i % 3 == 0 ? "Fizz" :
                i % 5 == 0 ? "Buzz" :
                Integer.toString(i)
            );
        return ret;
    }
}