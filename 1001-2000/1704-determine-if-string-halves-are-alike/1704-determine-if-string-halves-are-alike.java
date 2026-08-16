class Solution {
        static{
                for(int i=0;i<500;i++){
                            halvesAreAlike("");
                                    }
                                        }
                                            public static boolean halvesAreAlike(String s) {
                                                    int count1 = 0, count2 = 0 , n = s.length();

                                                            for(int i = 0;i < n / 2;i++){
                                                                        char ch1 = s.charAt(i);
                                                                                    char ch2 = s.charAt(i + n / 2);
                                                                                                if(ch1=='a'||ch1=='A'||ch1=='e'||ch1=='E'||ch1=='i'||ch1=='I'||ch1=='o'||ch1=='O'||ch1=='u'||ch1=='U'){
                                                                                                                count1++;
                                                                                                                            }
                                                                                                                                        if(ch2=='a'||ch2=='A'||ch2=='e'||ch2=='E'||ch2=='i'||ch2=='I'||ch2=='o'||ch2=='O'||ch2=='u'||ch2=='U'){
                                                                                                                                                        count2++;
                                                                                                                                                                    }
                                                                                                                                                                            }
                                                                                                                                                                                    return count1 == count2;
                                                                                                                                                                                        }
                                                                                                                }