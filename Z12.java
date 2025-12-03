public class Z12 {
    public static void main(String[] args) {
        Thread numThread = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread charThread = new Thread(() -> {
            for (char c = 'A'; c <= 'J'; c++) {
                System.out.println(c);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        numThread.start();
        charThread.start();
    }
}