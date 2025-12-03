import java.util.Scanner;

public class Z02 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入第一个整数：");
        int a = sc.nextInt();
        System.out.print("请输入第二个整数：");
        int b = sc.nextInt();
        int sum = a + b;
        System.out.println("两数之和为：" + sum);
        sc.close();
    }
}