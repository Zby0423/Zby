class Resource {
    private int count = 0;
    private final int MAX = 5;
    public synchronized void produce() throws InterruptedException {
        while (count >= MAX) wait();
        count++;
        System.out.println("生产者生产，当前数量：" + count);
        notifyAll();
    }
    public synchronized void consume() throws InterruptedException {
        while (count <= 0) wait();
        count--;
        System.out.println("消费者消费，当前数量：" + count);
        notifyAll();
    }
}
public class Z17 {
    public static void main(String[] args) {
        Resource resource = new Resource();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try { resource.produce(); } catch (InterruptedException e) {}
            }
        }).start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try { resource.consume(); } catch (InterruptedException e) {}
            }
        }).start();
    }
}