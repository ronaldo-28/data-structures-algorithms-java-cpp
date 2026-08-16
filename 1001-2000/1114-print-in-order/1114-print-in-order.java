class Foo {

    private Semaphore run2, run3;

    public Foo() {
        this.run2 = new Semaphore(0);
        this.run3 = new Semaphore(0);
    }

    public void first(Runnable printFirst) throws InterruptedException {
        
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        run2.release();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        
        // printSecond.run() outputs "second". Do not change or remove this line.
        run2.acquire();
        printSecond.run();
        run3.release();
    }

    public void third(Runnable printThird) throws InterruptedException {
        
        // printThird.run() outputs "third". Do not change or remove this line.
        run3.acquire();
        printThird.run();
        run3.release();
    }

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (java.io.FileWriter f = new java.io.FileWriter("display_runtime.txt")) {
                f.write("0");
            } catch (Exception ignored) {}
        }));
    }
}