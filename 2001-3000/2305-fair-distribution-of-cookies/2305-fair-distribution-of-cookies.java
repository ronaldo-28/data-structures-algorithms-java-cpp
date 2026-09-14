class Solution {
    int answer = Integer.MAX_VALUE;
    public int distributeCookies(int[] cookies, int k) {
        int children [] = new int[k];
        dfs(0, cookies, children);
        return answer;
    }

    public void dfs(int index, int [] cookies, int [] children){
        // base case: all bags are assigned
        if(index == cookies.length){

            int unfairness = 0;
            for(int load: children){
              unfairness = Math.max(load, unfairness);
            }

            // minimize this unfairness
            answer = Math.min(answer, unfairness);
            return;
        }

        int bag = cookies[index];

        // number of choices = k
        for(int child = 0; child<children.length; child++){

            // choose
            children[child] += bag;

            // explore
            // pruning 1:
            int currentMax = 0;
            for(int load: children){
                currentMax = Math.max(currentMax, load);
            }

            if(currentMax < answer){
                dfs(index+1, cookies, children);
            }

            //backtrack
            children[child] -= bag;

            // pruning 2:
            if(children[child]==0){
                break;
            }
        }
    }
}