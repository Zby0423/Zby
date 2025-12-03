import java.io.File;
import java.util.Scanner;

public class Z20 {
    public static void main(String[] args) throws Exception {
        File file = new File("test.txt");
        Scanner scanner = new Scanner(file);
        int count = 0;
        while (scanner.hasNext()) {
            scanner.next();
            count++;
        }
        System.out.println("单词数量：" + count);
        scanner.close();
    }
}