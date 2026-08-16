class Solution {
    public String reorderSpaces(String text) {
        char[] arr = text.toCharArray();
        int space = 0;
        int words = 0;
        for(int i = 0;i<arr.length;)
        {
            while (i<arr.length && arr[i]==' ')
            {
                space++;
                i++;
            }
            boolean add = false;
            while (i<arr.length && arr[i]!=' ')
            {
                add = true;
                i++;
            }
            if(add)
                words++;
        }
        char[] ans = new char[arr.length];
        int required = 1;
        if(words>1)
        {
            required = space/(words-1);
        }
        int i = 0, k =0;
        while (i<arr.length && arr[i]==' ')
        {
            i++;
        }
        while (i<arr.length && k<arr.length)
        {
            while (i<arr.length && arr[i]!=' ')
            {
                ans[k++] = arr[i++];
            }
            while (i<arr.length && arr[i]==' ')
            {
                i++;
            }
            int K = required;
            while (k<arr.length && K-->0)
            {
                ans[k++] = ' ';
            }
        }
        while (k<arr.length)
        {
            ans[k++] = ' ';
        }
        return new String(ans);
    }
}