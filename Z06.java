import java.util.Scanner;


public class Z06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 接收用户输入
        System.out.print("请输入第一个数字：");
        double num1 = scanner.nextDouble();

        System.out.print("请输入运算符（+、-、*、/）：");
        char operator = scanner.next().charAt(0);

        System.out.print("请输入第二个数字：");
        double num2 = scanner.nextDouble();

        // 计算逻辑与结果输出
        double result;
        boolean isValid = true;
        switch (operator) {
            case '+':
                result = num1 + num2;
                System.out.printf("%.2f + %.2f = %.2f%n", num1, num2, result);
                break;
            case '-':
                result = num1 - num2;
                System.out.printf("%.2f - %.2f = %.2f%n", num1, num2, result);
                break;
            case '*':
                result = num1 * num2;
                System.out.printf("%.2f * %.2f = %.2f%n", num1, num2, result);
                break;
            case '/':
                // 校验除数不能为0
                if (num2 == 0) {
                    System.out.println("错误：除数不能为0");
                    isValid = false;
                } else {
                    result = num1 / num2;
                    System.out.printf("%.2f / %.2f = %.2f%n", num1, num2, result);
                }
                break;
            default:
                System.out.println("错误：无效的运算符");
                isValid = false;
        }

        scanner.close();
    }
}