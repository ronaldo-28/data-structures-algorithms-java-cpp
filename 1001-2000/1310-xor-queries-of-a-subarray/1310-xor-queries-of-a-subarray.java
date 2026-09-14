class  Solution
{


public int[] xorQueries(int[] arr, int[][] queries) {

    // Step 1: Convert arr into prefix XOR
    for (int i = 1; i < arr.length; i++) {
        arr[i] ^= arr[i - 1];
    }

    int[] result = new int[queries.length];

    // Step 2: Process queries
    for (int i = 0; i < queries.length; i++) {
        int left = queries[i][0];
        int right = queries[i][1];

        if (left > 0) {
            result[i] = arr[right] ^ arr[left - 1];
        } else {
            result[i] = arr[right];
        }
    }

    return result;
}
}