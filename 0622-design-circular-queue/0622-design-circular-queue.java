class MyCircularQueue {
    private int[] queue;     // Array representation of the circular queue
    private int front;       // Index of the front element
    private int size;        // Current size of the queue
    private int capacity;    // Maximum capacity of the queue

    // Constructor to initialize the queue with a given capacity.
    public MyCircularQueue(int k) {
        queue = new int[k];
        capacity = k;
        front = 0;
        size = 0;
    }

    // Inserts an element into the circular queue. Returns true if the operation is successful.
    public boolean enQueue(int value) {
        if (isFull()) { // Check if the queue is full
            return false;
        }
        // Calculate the index where the value will be inserted
        int index = (front + size) % capacity;
        queue[index] = value;
        size++; // Increment the size of the queue
        return true;
    }

    // Deletes an element from the circular queue. Returns true if the operation is successful.
    public boolean deQueue() {
        if (isEmpty()) { // Check if the queue is empty
            return false;
        }
        front = (front + 1) % capacity; // Move the front pointer to the next element
        size--; // Decrement the size of the queue
        return true;
    }

    // Gets the front item from the queue. If the queue is empty, return -1.
    public int Front() {
        if (isEmpty()) { // Check if the queue is empty
            return -1;
        }
        return queue[front]; // Return the front element
    }

    // Gets the last item from the queue. If the queue is empty, return -1.
    public int Rear() {
        if (isEmpty()) { // Check if the queue is empty
            return -1;
        }
        // Calculate the index of the rear element
        int index = (front + size - 1) % capacity;
        return queue[index]; // Return the rear element
    }

    // Checks if the circular queue is empty.
    public boolean isEmpty() {
        return size == 0; // Return true if the size is 0
    }

    // Checks if the circular queue is full.
    public boolean isFull() {
        return size == capacity; // Return true if the size is equal to the capacity
    }
}

/**
 * Example of usage:
 * MyCircularQueue circularQueue = new MyCircularQueue(3); // set the size to be 3
 * boolean enQueueResult = circularQueue.enQueue(1);  // return true
 * boolean deQueueResult = circularQueue.deQueue();  // return true
 * int front = circularQueue.Front();  // return 1, or -1 if the queue is empty
 * int rear = circularQueue.Rear();  // return the last element, or -1 if the queue is empty
 * boolean isEmpty = circularQueue.isEmpty();  // return false
 * boolean isFull = circularQueue.isFull();  // return true if the queue is full
 */