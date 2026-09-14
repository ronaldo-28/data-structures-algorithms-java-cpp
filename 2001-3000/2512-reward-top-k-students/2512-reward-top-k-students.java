class Solution {
    static class Pair{
        int f;
        int id;
        public Pair(int id,int f)
        {
            this.id=id;
            this.f=f;
        }
    }
    public List<Integer> topStudents(String[] p, String[] ne, String[] r, int[] stu, int k) {
        PriorityQueue<Pair>pq=new PriorityQueue<>((a,b)->(a.f==b.f)?a.id-b.id:b.f-a.f);
        HashSet<String>pos=new HashSet<>();
        HashSet<String>neg=new HashSet<>();
        for(String s:p)
        {
            pos.add(s);
        }
        for(String s:ne)
        {
            neg.add(s);
        }
        int n=stu.length;
        for(int i=0;i<n;i++)
        {
            int f=0;
            String s[]=r[i].split(" ");
            for(String st:s)
            {
                if(pos.contains(st)) f+=3;
                else if(neg.contains(st)) f--;
            }
            pq.add(new Pair(stu[i],f));
        }
        // int ans[]=new int[k];
        List<Integer>ans=new ArrayList<>();
        int i=0;
        while(i<k)
        {
            ans.add(pq.poll().id);
            i++;
        }
        return ans;
    }
}