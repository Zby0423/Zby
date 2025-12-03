class Account {
    private double balance;

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("存款成功！存入金额：%.2f，当前余额：%.2f%n", amount, balance);
        } else {
            System.out.println("错误：存款金额必须大于0");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("错误：取款金额必须大于0");
        } else if (amount > balance) {
            System.out.println("错误：余额不足");
        } else {
            balance -= amount;
            System.out.printf("取款成功！取出金额：%.2f，当前余额：%.2f%n", amount, balance);
        }
    }

    public void checkBalance() {
        System.out.printf("当前账户余额：%.2f%n", balance);
    }
}

public class Z07 {
    public static void main(String[] args) {
        Account myAccount = new Account();
        myAccount.checkBalance();
        myAccount.deposit(5000);
        myAccount.withdraw(1500);
        myAccount.withdraw(4000);
        myAccount.deposit(-200);
        myAccount.checkBalance(); //
    }
}