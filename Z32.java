import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

class BankAccount implements Serializable {
    private String accountId;
    private String username;
    private double balance;
    public BankAccount(String accountId, String username, double balance) {
        this.accountId = accountId;
        this.username = username;
        this.balance = Math.max(balance, 0);
    }
    public synchronized void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("账户%s存款成功，金额：%.2f，当前余额：%.2f\n",
                    accountId, amount, balance);
        } else {
            System.out.println("存款失败：金额必须大于0");
        }
    }
    public synchronized boolean withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.printf("账户%s取款成功，金额：%.2f，当前余额：%.2f\n",
                    accountId, amount, balance);
            return true;
        } else {
            System.out.println("取款失败：金额非法或余额不足");
            return false;
        }
    }
    public synchronized double checkBalance() {
        System.out.printf("账户%s余额：%.2f\n", accountId, balance);
        return balance;
    }
    public String getAccountId() {
        return accountId;
    }
    @Override
    public String toString() {
        return String.format("账户ID：%s，用户名：%s，余额：%.2f",
                accountId, username, balance);
    }
}

class BankManager {
    private Map<String, BankAccount> accounts = new HashMap<>();
    private static final String FILE_PATH = "bank_accounts.dat";
    @SuppressWarnings("unchecked")
    public void loadAccountsFromFile() {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                accounts = (Map<String, BankAccount>) ois.readObject();
                System.out.println("成功加载账户信息，共" + accounts.size() + "个账户");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("加载账户失败：" + e.getMessage());
            }
        } else {
            System.out.println("未找到账户文件，将创建新文件");
        }
    }
    public void saveAccountsToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(accounts);
            System.out.println("账户信息已保存到文件");
        } catch (IOException e) {
            System.out.println("保存账户失败：" + e.getMessage());
        }
    }
    public void createAccount(String accountId, String username, double initialBalance) {
        if (accounts.containsKey(accountId)) {
            System.out.println("账户ID已存在，创建失败");
            return;
        }
        BankAccount account = new BankAccount(accountId, username, initialBalance);
        accounts.put(accountId, account);
        System.out.println("账户创建成功：" + account);
    }
    private Optional<BankAccount> getAccount(String accountId) {
        BankAccount account = accounts.get(accountId);
        if (account == null) {
            System.out.println("未找到账户：" + accountId);
            return Optional.empty();
        }
        return Optional.of(account);
    }
    public void deposit(String accountId, double amount) {
        getAccount(accountId).ifPresent(account -> account.deposit(amount));
    }
    public void withdraw(String accountId, double amount) {
        getAccount(accountId).ifPresent(account -> account.withdraw(amount));
    }
    public void checkBalance(String accountId) {
        getAccount(accountId).ifPresent(BankAccount::checkBalance);
    }
    public void showAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("暂无账户信息");
            return;
        }
        System.out.println("\n=== 所有账户信息 ===");
        accounts.values().forEach(System.out::println);
    }
}

public class Z32 {
    public static void main(String[] args) throws InterruptedException {
        BankManager manager = new BankManager();
        manager.loadAccountsFromFile();
        manager.createAccount("A001", "张三", 1000);
        Runnable depositTask = () -> manager.deposit("A001", 200);
        Runnable withdrawTask = () -> manager.withdraw("A001", 150);
        Thread t1 = new Thread(depositTask);
        Thread t2 = new Thread(withdrawTask);
        Thread t3 = new Thread(depositTask);
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t3.join();
        System.out.println("\n多线程操作后：");
        manager.checkBalance("A001");
        manager.showAllAccounts();
        manager.saveAccountsToFile();
    }
}