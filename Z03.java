import java.util.Scanner;

public class Z03 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入圆的半径：");
        double r = sc.nextDouble();
        final double PI = 3.1415926;
        double area = PI * r * r;
        System.out.println("圆的面积为：" + area);
        sc.close();
    }
}
