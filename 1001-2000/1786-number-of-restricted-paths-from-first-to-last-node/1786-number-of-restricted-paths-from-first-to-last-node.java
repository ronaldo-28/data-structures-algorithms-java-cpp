import java.util.Arrays;

class Solution {
    private static final int MODULO = 1000000007;

    public int countRestrictedPaths(int n, int[][] edges) {
        int edgeCount = edges.length;
        
        // Static Adjacency List (Forward Star)
        int[] headEdge = new int[n + 1];
        int[] nextEdge = new int[edgeCount * 2];
        int[] destinationNode = new int[edgeCount * 2];
        int[] edgeWeight = new int[edgeCount * 2];
        Arrays.fill(headEdge, -1);

        for (int i = 0; i < edgeCount; i++) {
            int source = edges[i][0];
            int destination = edges[i][1];
            int weight = edges[i][2];

            // Add directed edge: source -> destination
            destinationNode[i * 2] = destination;
            edgeWeight[i * 2] = weight;
            nextEdge[i * 2] = headEdge[source];
            headEdge[source] = i * 2;

            // Add directed edge: destination -> source
            destinationNode[i * 2 + 1] = source;
            edgeWeight[i * 2 + 1] = weight;
            nextEdge[i * 2 + 1] = headEdge[destination];
            headEdge[destination] = i * 2 + 1;
        }

        // Dijkstra's Algorithm
        int[] shortestDistances = new int[n + 1];
        Arrays.fill(shortestDistances, Integer.MAX_VALUE);
        shortestDistances[n] = 0;

        // Manual Min-Heap using long to pack (distance << 18 | node_id)
        long[] minHeap = new long[edgeCount * 2 + 1];
        int heapSize = 0;
        addToHeap(minHeap, ++heapSize, n, 0);

        while (heapSize > 0) {
            long currentPackedValue = removeFromHeap(minHeap, heapSize--);
            int currentNode = (int) (currentPackedValue & 0x3FFFF);
            int currentDist = (int) (currentPackedValue >>> 18);

            if (currentDist > shortestDistances[currentNode]) continue;

            for (int i = headEdge[currentNode]; i != -1; i = nextEdge[i]) {
                int neighbor = destinationNode[i];
                int newDistance = shortestDistances[currentNode] + edgeWeight[i];
                if (newDistance < shortestDistances[neighbor]) {
                    shortestDistances[neighbor] = newDistance;
                    addToHeap(minHeap, ++heapSize, neighbor, newDistance);
                }
            }
        }

        // Memoization Table for counting restricted paths
        int[] memoPaths = new int[n + 1];
        Arrays.fill(memoPaths, -1);
        
        return countWaysRecursively(1, n, headEdge, nextEdge, destinationNode, shortestDistances, memoPaths);
    }

    private int countWaysRecursively(int currentNode, int targetNode, int[] headEdge, int[] nextEdge, 
                                     int[] destinationNode, int[] shortestDistances, int[] memoPaths) {
        if (currentNode == targetNode) return 1;
        if (memoPaths[currentNode] != -1) return memoPaths[currentNode];

        long totalWays = 0;

        for (int i = headEdge[currentNode]; i != -1; i = nextEdge[i]) {
            int neighborNode = destinationNode[i];
            
            // Restricted path condition: current node must be further from the end than the neighbor
            if (shortestDistances[currentNode] > shortestDistances[neighborNode]) {
                totalWays = (totalWays + countWaysRecursively(neighborNode, targetNode, headEdge, 
                             nextEdge, destinationNode, shortestDistances, memoPaths)) % MODULO;
            }
        }

        return memoPaths[currentNode] = (int) totalWays;
    }

    private void addToHeap(long[] heap, int size, int nodeId, int distance) {
        long value = ((long) distance << 18) | nodeId;
        heap[size] = value;
        int index = size;
        while (index > 1 && heap[index] < heap[index / 2]) {
            long temp = heap[index];
            heap[index] = heap[index / 2];
            heap[index / 2] = temp;
            index /= 2;
        }
    }

    private long removeFromHeap(long[] heap, int size) {
        long rootValue = heap[1];
        heap[1] = heap[size];
        int index = 1;
        while (index * 2 < size) {
            int smallerChild = index * 2;
            if (smallerChild + 1 < size && heap[smallerChild + 1] < heap[smallerChild]) {
                smallerChild++;
            }
            if (heap[index] <= heap[smallerChild]) break;
            long temp = heap[index];
            heap[index] = heap[smallerChild];
            heap[smallerChild] = temp;
            index = smallerChild;
        }
        return rootValue;
    }
}