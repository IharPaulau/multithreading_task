public class Ex_4_Yield {
    public static void main(String[] args) throws InterruptedException {
        var t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + i);
                // Thread.yield(); // <===================== Uncomment this
            }
        });
        var t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + i);
//                 Thread.yield(); // <===================== Uncomment this
            }
        });

        t1.start();
        t2.start();
    }
}