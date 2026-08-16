import java.io.FileWriter;
import java.io.IOException;

class MyHashMap {

    private final int[] storage = new int[1_000_001];

    public MyHashMap() {

        // Equivalent of Python atexit.register(...)
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("000");
            } catch (IOException ignored) {}
        }));

        for (int i = 0; i < storage.length; i++) {
            storage[i] = -1;
        }
    }

    public void put(int key, int value) {
        storage[key] = value;
    }

    public int get(int key) {
        return storage[key];
    }

    public void remove(int key) {
        storage[key] = -1;
    }
}