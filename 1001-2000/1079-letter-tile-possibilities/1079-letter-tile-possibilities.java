// class Solution {
//      //static List<String> l=new ArrayList<>();
//     public static void count(String tiles,StringBuilder p,Set<String> s,boolean[] b)
//     {
//         if(p.length()>0)
//         {
//             s.add(p.toString());
//         }
//         for(int j=0;j<tiles.length();j++)
//         {
//             if(b[j]) continue;

//             p.append(tiles.charAt(j));
//             b[j]=true;

//             count(tiles,p,s,b);

//             p.deleteCharAt(p.length()-1);
//             b[j]=false;
//         }

//     }
//     public int numTilePossibilities(String tiles) {
//         Set<String> s=new HashSet<>();
//         boolean[] b=new boolean[tiles.length()];
//         count(tiles,new StringBuilder(),s,b);
//         return s.size();
//     }
// }
class Solution {
    public int numTilePossibilities(String tiles) {
        char[] arr = tiles.toCharArray();
        return permute(0, arr);
    }
    public int permute(int start, char[] arr) {
        if(start == arr.length) return 0;
        int ans = 0;
        for(int i=start; i<arr.length; i++) {
            if(!isPermutedBefore(start, i-1, arr[i], arr)) {
                swap(start, i, arr);
                ans += 1+permute(start+1, arr);
                swap(start, i, arr);
            }
        }
        return ans;
    }
    public boolean isPermutedBefore(int i, int j, char ch, char[] arr) {
        while(i<=j)
            if(arr[i++] == ch) return true;
        return false;
    }
    public void swap(int i, int j, char[] arr) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}