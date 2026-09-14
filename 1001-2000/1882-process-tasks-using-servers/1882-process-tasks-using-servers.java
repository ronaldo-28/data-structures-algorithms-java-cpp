class Solution {

    class Server {
        int time;
        int index;
        int weight;
    }


    public int[] assignTasks(int[] servers, int[] tasks) {

        if(servers.length==1){
            return new int[tasks.length];
        }

        Comparator<Server> availableComparator = new Comparator<>() {
            public int compare(Server s1, Server s2) {
                if(s1.weight==s2.weight){
                    return s1.index - s2.index;
                }
                return s1.weight - s2.weight;
            }
        };

                Comparator<Server> unAvailableComparator = new Comparator<>() {
            public int compare(Server s1, Server s2) {
                return s1.time - s2.time;
            }
        };
        PriorityQueue<Server> available = new PriorityQueue<>(availableComparator);
        PriorityQueue<Server> busy = new PriorityQueue<>(unAvailableComparator);
        for(int i=0;i<servers.length;i++) {
            Server s = new Server();
            s.time = 0;
            s.weight = servers[i];
            s.index = i;
            available.add(s);
        }

        int[] ans = new int[tasks.length];
        int index = 0;
        int time = 0;

        while(index!=tasks.length) {
            while(busy.size()>0 && busy.peek().time<=time){
                available.add(busy.poll());
            }

            while(available.size()>0 && index<=time && index!=tasks.length) {
                Server s = available.poll();
                s.time = time+tasks[index];
                busy.add(s);
                ans[index]=s.index;
                index++;
            }

            if(available.size()>0){
                time++;
            } else {
                time = busy.peek().time;
            }
        }

        return ans;
    }
}