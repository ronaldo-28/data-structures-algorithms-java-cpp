// class Solution {
//     public int[] getOrder(int[][] tasks) {
        
//     }
// }
import java.util.Arrays;

class Solution {
    public int[] getOrder(int[][] tasks) {
        int taskCount = tasks.length;
        
        // Pack enqueueTime, processingTime, and index into a long array for fast sorting
        // We use two longs or a custom object, but for the initial sort, 
        // we'll use a wrapper to keep the logic manageable while staying fast.
        Task[] taskMetadata = new Task[taskCount];
        for (int i = 0; i < taskCount; i++) {
            taskMetadata[i] = new Task(tasks[i][0], tasks[i][1], i);
        }

        // Sort by enqueueTime
        Arrays.sort(taskMetadata, (a, b) -> Integer.compare(a.enqueueTime, b.enqueueTime));

        // Manual Min-Heap using long to store {processingTime, originalIndex}
        // Bit-packing: (long)processingTime << 32 | originalIndex
        long[] minHeap = new long[taskCount];
        int heapSize = 0;

        int[] resultOrder = new int[taskCount];
        int resultIndex = 0;
        int taskPointer = 0;
        long currentTime = 0;

        while (resultIndex < taskCount) {
            if (heapSize == 0 && currentTime < taskMetadata[taskPointer].enqueueTime) {
                currentTime = taskMetadata[taskPointer].enqueueTime;
            }

            while (taskPointer < taskCount && taskMetadata[taskPointer].enqueueTime <= currentTime) {
                // Push to manual heap: Priority = processingTime, then index
                long value = ((long) taskMetadata[taskPointer].processingTime << 32) | taskMetadata[taskPointer].index;
                push(minHeap, heapSize++, value);
                taskPointer++;
            }

            // Pop from manual heap
            long top = pop(minHeap, heapSize--);
            int processedIndex = (int) (top & 0xFFFFFFFFL);
            int processingDuration = (int) (top >> 32);

            resultOrder[resultIndex++] = processedIndex;
            currentTime += processingDuration;
        }

        return resultOrder;
    }

    private void push(long[] heap, int size, long value) {
        int i = size;
        while (i > 0) {
            int parent = (i - 1) / 2;
            if (heap[parent] <= value) break;
            heap[i] = heap[parent];
            i = parent;
        }
        heap[i] = value;
    }

    private long pop(long[] heap, int size) {
        long top = heap[0];
        long last = heap[size - 1];
        int i = 0;
        while (i * 2 + 1 < size - 1) {
            int child = i * 2 + 1;
            if (child + 1 < size - 1 && heap[child + 1] < heap[child]) {
                child++;
            }
            if (last <= heap[child]) break;
            heap[i] = heap[child];
            i = child;
        }
        heap[i] = last;
        return top;
    }

    private static class Task {
        int enqueueTime;
        int processingTime;
        int index;

        Task(int enqueueTime, int processingTime, int index) {
            this.enqueueTime = enqueueTime;
            this.processingTime = processingTime;
            this.index = index;
        }
    }
}