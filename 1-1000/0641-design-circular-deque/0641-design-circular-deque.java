class MyCircularDeque {
    private int[] queue;
    private int front;
    private int size;
    private int capacity;

    /**
     * Constructor to initialize the circular deque.
     * @param k The capacity of the deque.
     */
    public MyCircularDeque(int k) {
        queue = new int[k];
        capacity = k;
        front = 0;
        size = 0;
    }

    /**
     * Inserts an item at the front of the deque.
     * @param value The value to insert.
     * @return true if the operation is successful, false otherwise.
     */
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        front = (front - 1 + capacity) % capacity;
        queue[front] = value;
        size++;
        return true;
    }

    /**
     * Inserts an item at the rear of the deque.
     * @param value The value to insert.
     * @return true if the operation is successful, false otherwise.
     */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        int rear = (front + size) % capacity;
        queue[rear] = value;
        size++;
        return true;
    }

    /**
     * Deletes an item from the front of the deque.
     * @return true if the operation is successful, false otherwise.
     */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1) % capacity;
        size--;
        return true;
    }

    /**
     * Deletes an item from the rear of the deque.
     * @return true if the operation is successful, false otherwise.
     */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        size--;
        return true;
    }

    /**
     * Gets the item from the front of the deque.
     * @return The front item, or -1 if the deque is empty.
     */
    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return queue[front];
    }

    /**
     * Gets the item from the rear of the deque.
     * @return The rear item, or -1 if the deque is empty.
     */
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        int rear = (front + size - 1) % capacity;
        return queue[rear];
    }

    /**
     * Checks whether the circular deque is empty.
     * @return true if the deque is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Checks whether the circular deque is full.
     * @return true if the deque is full, false otherwise.
     */
    public boolean isFull() {
        return size == capacity;
    }
}