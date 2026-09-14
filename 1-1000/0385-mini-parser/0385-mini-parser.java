class Solution {
    int idx, n;
    public NestedInteger deserialize(String s) {
        n = s.length();
        return des(s.toCharArray());
    }

    public NestedInteger des(char[] arr){
        if(arr[idx] == '['){
            idx++;
            NestedInteger curr = new NestedInteger();
            while(idx < n && arr[idx] != ']'){
                if(arr[idx] == ','){
                    idx++;
                }
                NestedInteger next = des(arr);
                curr.add(next);
            }
            idx++;
            return curr;
        }else{
            boolean neg = false;
            if(arr[idx] == '-'){
                neg = true;
                idx++;
            }
            int num = 0;
            while(idx < n && Character.isDigit(arr[idx])){
                num = (num * 10) + (arr[idx] - '0');
                idx++;
            }
            num = neg ? -num : num;
            return new NestedInteger(num);
        }
    }
}