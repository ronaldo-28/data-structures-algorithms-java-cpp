// class LockingTree {
//     static class Node{
//         int val;
//         Node parent;
//         List<Node> children;
//         boolean isLocked;
//         int Lockedby;
//         int countdescendant;
//         Node(int val){
//             this.val=val;
//             this.children=new ArrayList<>();
//             this.isLocked=false;
//             this.Lockedby=-1;
//             this.countdescendant=0;

//         }
//     }
//     private Map<Integer,Node> map;

//     public LockingTree(int[] parent) {
//         int n= parent.length;
//         map=new HashMap<>();
//         for(int i=0;i<n;i++){
//             map.put(i,new Node(i));
//         }
//         for(int i=0;i<n;i++){
//             if(parent[i]!=-1){
//                 Node child=map.get(i);
//                 Node par=map.get(parent[i]);
//                 child.parent=par;
//                 par.children.add(child);
//             }
//         }
     



        
//     }
//       private boolean lockedAncestors(Node node){
//             Node curr=node.parent;
//             while(curr!=null){
//                if( curr.isLocked) return true;
//                curr=curr.parent;
//             }
//             return false;
//         }
//         private void updateAncestors(Node node, int delta){
//             Node curr=node.parent;
//             while(curr!=null){
//                 curr.countdescendant+=delta;
//                 curr=curr.parent;
//             }

//         }
    
//     public boolean lock(int num, int user) {
//         Node node=map.get(num);
//         if(node.isLocked) return false;
//         if(lockedAncestors(node)) return false;
//         if(node.countdescendant>0) return false;
//         node.isLocked=true;
//         node.Lockedby=user;
//         updateAncestors(node,1);
//         return true;
        
//     }
    
//     public boolean unlock(int num, int user) {
//         Node node=map.get(num);
//         if(!node.isLocked) return false;
//         if(node.Lockedby!=user) return false;
//         node.Lockedby=-1;
//         node.isLocked=false;
//         updateAncestors(node,-1);
//         return true;
        
//     }
    
//     public boolean upgrade(int num, int user) {
//         Node node=map.get(num);
//         if(node.isLocked) return false;
//         if(lockedAncestors(node)) return false;
//         if(node.countdescendant==0) return false;
//        boolean foundlocked= unlockDescendant(node);
//        if(!foundlocked) return false;
//         node.isLocked=true;
//         node.Lockedby=user;
//         updateAncestors(node,1);
//         return true;
        
//     }
//     private boolean unlockDescendant(Node node){
//         boolean found=false;
//         Queue<Node> q=new LinkedList<>();
//         q.offer(node);
//         while(!q.isEmpty()){
//             Node curr=q.poll();
//              if(curr.isLocked){
//             curr.isLocked=false;
//             curr.Lockedby=-1;
//             updateAncestors(curr,-1);
//             found=true;
//         }
//             for(Node child : curr.children){
//                 q.offer(child);
//                 // if(child.isLocked==true){
//                 //     child.isLocked=false;
//                 //     child.Lockedby=-1;
//                 //     updateAncestors(child,-1);
//                 //     found=true;
//                 }

//             }
        
//         return found;
//     }
// }

// /**
//  * Your LockingTree object will be instantiated and called as such:
//  * LockingTree obj = new LockingTree(parent);
//  * boolean param_1 = obj.lock(num,user);
//  * boolean param_2 = obj.unlock(num,user);
//  * boolean param_3 = obj.upgrade(num,user);
//  */
class LockingTree {

    private int[] parent;
    private List<Integer>[] graph;
    private int[] owner;           // -1 means unlocked
    private int[] activeLocks;     // number of locked nodes in subtree

    public LockingTree(int[] parent) {
        int n = parent.length;
        this.parent = parent;

        graph = new ArrayList[n];
        owner = new int[n];
        activeLocks = new int[n];

        Arrays.fill(owner, -1);

        for(int i = 0; i < n; i++)
            graph[i] = new ArrayList<>();

        for(int i = 1; i < n; i++)
            graph[parent[i]].add(i);
    }

    public boolean lock(int node, int user) {
        if(owner[node] != -1) return false;

        owner[node] = user;
        propagate(node, 1);
        return true;
    }

    public boolean unlock(int node, int user) {
        if(owner[node] != user) return false;

        owner[node] = -1;
        propagate(node, -1);
        return true;
    }

    public boolean upgrade(int node, int user) {

        if(owner[node] != -1 || activeLocks[node] == 0)
            return false;

        for(int p = parent[node]; p != -1; p = parent[p])
            if(owner[p] != -1) return false;

        int count = activeLocks[node];
        clear(node);

        propagate(node, -count);
        owner[node] = user;
        propagate(node, 1);

        return true;
    }

    private void propagate(int node, int delta) {
        for(int p = parent[node]; p != -1; p = parent[p])
            activeLocks[p] += delta;
    }

    private void clear(int node) {
        owner[node] = -1;
        activeLocks[node] = 0;

        for(int child : graph[node])
            if(owner[child] != -1 || activeLocks[child] > 0)
                clear(child);
    }
}