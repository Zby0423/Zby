import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Z25{
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 5; i++) {
            int taskId = i;
            executor.submit(() -> {
                System.out.println("任务" + taskId + "执行，线程：" + Thread.currentThread().getName());
                try { Thread.sleep(1000); } catch (InterruptedException e) {}
            });
        }
        executor.shutdown();
    }
}