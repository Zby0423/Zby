import java.util.TreeSet;
import java.util.Scanner;

public class Z27{
    public static void main(String[] args) {
        TreeSet<Integer> set = new TreeSet<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入整数（输入非整数结束）：");
        while (scanner.hasNextInt()) {
            set.add(scanner.nextInt());
        }
        System.out.println("升序排列结果：" + set);
        scanner.close();
    }
}