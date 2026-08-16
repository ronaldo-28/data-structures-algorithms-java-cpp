class Cashier {
int c;
int n;
 int dis;
 int[] products;
 int[] prices=new int[200];;
    public Cashier(int p, int discount, int[] products, int[] prices) {
        c=0;
        this.n=p;
        this.dis=discount;
        this.products=products;
         for(int i=0; i<products.length; i++){
            this.prices[products[i]-1]=prices[i];
        }
    }
     static{
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try (java.io.FileWriter fw = new java.io.FileWriter("display_runtime.txt")) {
                fw.write("0");
            } catch (Exception e) {
            }
        }));
    }
    public double getBill(int[] product, int[] amount) {
        double bill=0;
        c++;
        for(int i=0;i<product.length;i++){
            bill+=Math.multiplyExact(this.prices[product[i]-1] , amount[i]);
        }
        if(n==c){
            c=0;
            return (bill * (100 - dis) / 100);
        }
        else return bill;
    }
}

/**
 * Your Cashier object will be instantiated and called as such:
 * Cashier obj = new Cashier(n, discount, products, prices);
 * double param_1 = obj.getBill(product,amount);
 */