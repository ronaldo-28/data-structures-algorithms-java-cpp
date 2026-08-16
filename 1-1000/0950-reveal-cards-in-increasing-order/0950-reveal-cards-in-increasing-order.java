//Try Solving without Queue if Efficent 

class Solution {
    public int[] deckRevealedIncreasing(int[] deck) {
        int n = deck.length;
        int[] store = new int[2 * n];
        int i, j;
        Arrays.sort(deck);
        for (i = 0; i < 2 * n; i += 2) {
            store[i] = deck[i / 2];
        }
        // Jumping 1 after 1 after in circle impl=store from back and place from back
        for (j = i - 2, i = j - 1; i >= 0; i -= 2, j--) {
            store[i] = store[j];
        }
        return Arrays.copyOf(store, n);
    }
    // By Using Queue's Not Effcient by Own Coding
    // public int[] deckRevealedIncreasing(int[] deck) {
    // if(deck.length==0){
    // return deck;
    // }
    // int[] result=new int[deck.length];
    // Arrays.sort(deck);
    // Queue<Integer> sq=new LinkedList<>();
    // Queue<Integer> iq=new LinkedList<>();
    // for(int i=0;i<deck.length;i++){
    // sq.offer(deck[i]);
    // iq.offer(i);
    // }
    // while(!iq.isEmpty()){
    // int index=iq.poll();
    // result[index]=sq.poll();
    // if(!iq.isEmpty())
    // iq.offer(iq.poll());
    // }
    // return result;
    // }
}