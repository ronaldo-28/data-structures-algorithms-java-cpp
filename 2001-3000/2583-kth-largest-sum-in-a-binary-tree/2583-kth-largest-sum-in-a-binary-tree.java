class Solution {
    private static final Random rand = new Random();
    public long kthLargestLevelSum(TreeNode root, int k) {
        List<Long> res = new ArrayList<>();
        Deque<TreeNode> q = new ArrayDeque<>();
        if(root == null) return -1;
        q.offer(root);
        while(!q.isEmpty()) {
            int size = q.size();
            long sum = 0;
            for(int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                sum += cur.val;
                if(cur.left != null) {
                    q.offer(cur.left);
                }
                if(cur.right != null) {
                    q.offer(cur.right);
                }
            }
            res.add(sum);
        }
        if(res.size() < k) {
            return -1;
        }
        long[] arr = new long[res.size()];
        for(int i = 0; i < res.size(); i++) {
            arr[i] = res.get(i);
        }
        int l = 0, r = arr.length - 1;
        k = arr.length - k;
        while(true) {
            int index = partition(arr, l, r);
            if(index < k) {
                l = index + 1;
            } else if (index > k) {
                r = index - 1;
            } else {
                return arr[index];
            }
        }

    }

    private void swap(long[] arr, int i, int j) {
        long tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    private int partition(long[] arr, int l, int r) {
        int pivotIndex = l + rand.nextInt(r - l + 1);
        swap(arr, l, pivotIndex);
        int i = l + 1, j = r;
        while(i <= j) {
            while(i <= j && arr[i] < arr[l]) {
                i++;
            }
            while(i <= j && arr[j] > arr[l]) {
                j--;
            }
            if(i > j) {
                break;
            }
            swap(arr, i, j);
            i++;
            j--;
        }
        swap(arr, l, j);
        return j;
    }
}