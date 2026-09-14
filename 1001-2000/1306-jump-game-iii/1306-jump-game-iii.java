class Solution {
    public boolean canReach(int[] arr, int start) {

        if(arr[arr.length-1]==0) return true;

        Boolean[] visted = new Boolean[arr.length];

        return backTrack(arr,start,visted,new ArrayList<>());

    }
    public boolean backTrack(int[] arr,int index,Boolean[] visted,List<Integer> ls){
        if(index<0 || index>=arr.length || ls.contains(index)) return false;

        if(visted[index]!=null) return false;

        if(arr[index]==0) return visted[index]=true;

        ls.add(index);

        boolean pick = backTrack(arr,index+arr[index],visted,ls);
        boolean npick = backTrack(arr,index-arr[index],visted,ls);

        ls.remove(ls.size()-1);
        return visted[index]=pick||npick;
    }
}