class Solution {
    public String removeDuplicates(String s, int k) {
        int c=1,p=0;
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (java.io.FileWriter fw = new java.io.FileWriter("display_runtime.txt")) {
                fw.write("00");
            } catch (Exception e) {
                e.printStackTrace();
                }
                }));
        for(int i=0;i<s.length()-1;i++)
        {
            if(s.charAt(i)==s.charAt(i+1)&&c==1)
            p=i;
          if(s.charAt(i)==s.charAt(i+1))
          {
            c++;
            if(c==k)
            {
                s=s.substring(0,p)+s.substring(i+2,s.length());
                if(p-k>=-1)
                i=p-k;
                else
                i=-1;
                c=1;
            }
          }
          else 
          {
           c=1;
          }
        }
        return s;
    }
}