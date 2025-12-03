import java.util.Scanner;

class NegativeNumberException extends Exception {
    // 无参构造方法：使用默认异常信息
    public NegativeNumberException() {
        super("错误：输入了负数，不符合要求");
    }
    public NegativeNumberException(String message) {
        super(message);
    }
}
public class Z09 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入一个正数：");
        int number = scanner.nextInt();

        try {
            checkPositive(number);
            System.out.println("输入正确！你输入的正数是：" + number);
        } catch (NegativeNumberException e) {
            System.out.println(e.getMessage());
        }
        scanner.close();
    }
    private static void checkPositive(int number) throws NegativeNumberException {
        if (number < 0) {
            throw new NegativeNumberException();
        }
    }
}