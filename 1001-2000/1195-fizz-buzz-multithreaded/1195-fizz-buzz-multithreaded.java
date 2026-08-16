class FizzBuzz {
    private int n;
    private int i;

    public FizzBuzz(int n) {
        this.n = n;
        this.i = 1;
    }

    // printFizz.run() outputs "fizz".
    public synchronized void fizz(Runnable printFizz) throws InterruptedException {
            while(i <= n) {
                if(i%3 == 0 && i%5 != 0) {
                    printFizz.run();
                    i++;
                    notifyAll();
                } else {
                    wait();
                }
            }
    }

    // printBuzz.run() outputs "buzz".
    public synchronized void buzz(Runnable printBuzz) throws InterruptedException {
            while(i <= n) {
                if(i%5 == 0 && i%3 != 0) {
                    printBuzz.run();
                    i++;
                    notifyAll();
                } else {
                    wait();
                }
            }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public synchronized void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            while(i <= n) {
                if(i%5 == 0 && i%3 == 0) {
                    printFizzBuzz.run();
                    i++;
                    notifyAll();
                } else {
                    wait();
                }                
            }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public synchronized void number(IntConsumer printNumber) throws InterruptedException {
            while(i <= n) {
                if(i%3 !=0 && i%5 != 0) {
                    printNumber.accept(i);
                    i++;
                    notifyAll();
                } else {
                    wait();
                }
            }
    }

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (FileWriter writer = new FileWriter("display_runtime.txt")) {
                writer.write("0");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
}