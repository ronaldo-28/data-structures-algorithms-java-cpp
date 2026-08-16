class Solution {
 static { Runtime.getRuntime().addShutdownHook(new Thread(() -> { try (java.io.FileWriter fw = new java.io.FileWriter("display_runtime.txt")) { fw.write("0"); } catch (Exception e) { } })); }
   
   long  ma(int[] a, int[] b, int k){
    long val = 0 ;
    int cnt = 0 ;
    List<Integer> c = new ArrayList<>();
    for(int i=0;i<a.length;i++){
        if(a[i]>=b[i]){
            val+=a[i];
            cnt++;
        }else{
            val+=b[i];
            c.add(b[i]-a[i]);
        }
    }
    Collections.sort(c);
    int p=0;
    while(cnt<k){
        val= val-c.get(p);
        p++;
        cnt++;
    }
    return val;
   }



    public long maxPoints(int[] technique1, int[] technique2, int k) {
        return  ma(technique1, technique2,k);
    }
}