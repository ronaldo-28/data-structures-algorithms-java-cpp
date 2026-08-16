class Solution {
    void merge_sort (int A[], int n,int D[])
{
 int i, j, k, m;
 int B[], C[]; int E[], F[];
 if (n > 1) {
 k = n/2; m = n - k;
 B = new int[k];
 C = new int[m];
 E = new int[k];
 F = new int[m];
 for (i=0; i<k; i++) {B[i] = A[i];
                      E[i]=D[i];  }
 for (j=k; j<n; j++) {C[j-k] = A[j];
                        F[j-k]=D[j];}

 merge_sort (B, k, E);
 merge_sort (C, m, F);
 merge (B, C, A,E,F,D, k, m); // destination array is A
 }
 }
 void merge (int a[], int b[], int c[], int d[],int e[],int f[],int m, int n)
{
 int i=0, j=0, k=0, p=0;
 
 while ((i<m) && (j<n)) {
 if (a[i] < b[j])
 { c[k] = a[i];
    f[k]=d[i];
     i++; }
 else if(a[i]>b[j])
 { c[k] = b[j];
    f[k]=e[j]; j++; }
else
{
    if(d[i]<e[j])
    {
        c[k]=a[i];
        f[k]=d[i];
        i++;
    }
    else
    {
        c[k]=b[j];
        f[k]=e[j];
        j++;
    }
}
 k++;
 }

 if (i == m) { 
 for (p=j; p<n; p++)
 { c[k] = b[p];
    f[k]=e[p]; k++; }
 } else { 
 for (p=i; p<m; p++)
 { c[k] = a[p];
    f[k]=d[p]; k++; }
 }
}
    public List<Integer> filterRestaurants(int[][] r, int vf, int mp, int md) {
        int rate[]=new int[r.length];
        int id[]=new int[r.length];
        int idx=0;
        for(int i=0;i<r.length;i++)
        {
            if(r[i][3]<=mp&&r[i][4]<=md)
            {
                if((vf==1&&r[i][2]==1)||vf==0)
                {
                    rate[idx]=r[i][1];
                    id[idx]=r[i][0];
                    idx++;
                }
            }
        }
        rate=Arrays.copyOfRange(rate,0,idx);
        id=Arrays.copyOfRange(id,0,idx);
        merge_sort(rate,rate.length,id);
        
        //System.out.println(Arrays.toString(id));
        //System.out.println(Arrays.toString(rate));
        List<Integer> ret=new ArrayList<>();
        for(int i=id.length-1;i>=0&&id[i]!=0;i--)
        {
            ret.add(id[i]);
        }
        return ret;

    }
}