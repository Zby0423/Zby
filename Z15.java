import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Z15 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<>();
        System.out.println("输入数字（输入非数字结束）：");
        while (scanner.hasNextInt()) {
            list.add(scanner.nextInt());
        }
        ArrayList<Integer> uniqueList = new ArrayList<>(new HashSet<>(list));
        System.out.println("去重后：" + uniqueList);
    }
}