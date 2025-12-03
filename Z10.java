import java.lang.reflect.Method;
public class Z10 {
    public static void main(String[] args) {
        try {
            Class<?> bankAccountClass = Class.forName("BankAccount");
            BankAccount account = (BankAccount) bankAccountClass.getConstructor().newInstance();
            Method depositMethod = bankAccountClass.getMethod("deposit", double.class);
            double depositAmount = 3000.0;
            depositMethod.invoke(account, depositAmount);
            account.checkBalance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}