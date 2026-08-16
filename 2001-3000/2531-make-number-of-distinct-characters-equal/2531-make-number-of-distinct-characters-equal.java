class Solution {
    public boolean isItPossible(String w1, String w2) {
        int arr1[]=new int[26];
        int arr2[]=new int[26];
        for(int i=0;i<w1.length();i++)
        {
            arr1[w1.charAt(i)-'a']+=1;
        }
        for(int i=0;i<w2.length();i++)
        {
            arr2[w2.charAt(i)-'a']+=1;
        }
        for(int i=0;i<26;i++)
        {
            for(int j=0;j<26;j++)
            {
                if(arr1[i]!=0 && arr2[j]!=0)
                {
                    arr1[i]--;
                    arr2[j]--;
                    arr2[i]++;
                    arr1[j]++;
                    int cn1=0,cn2=0;
                    for(int k=0;k<26;k++)
                    {
                        if(arr1[k]>0)cn1++;
                        if(arr2[k]>0)cn2++;
                    }
                    if(cn1==cn2)return true;
                    arr1[i]++;
                    arr2[j]++;
                    arr2[i]--;
                    arr1[j]--;
                }
            }
        }
        return false;
    }
}