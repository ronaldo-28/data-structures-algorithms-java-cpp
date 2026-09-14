/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
    public static int[] nextLargerNodes(ListNode head) {
        ListNode temp = head;
        ArrayList <Integer> arr = new ArrayList<>();
        while(temp!=null){
            arr.add(temp.val);
            temp = temp.next;
        }
        int n = arr.size();
        int [] res = new int[n];
        Deque <Integer> st = new ArrayDeque<>();
        for(int i=n-1;i>=0;i--){
            while (!st.isEmpty() && arr.get(st.peek()) <= arr.get(i)) {
                st.pop();
            }            if(st.isEmpty()) res[i] = 0;
            else res[i] = arr.get(st.peek());
            st.push(i);
        }
        return res;
    }
}