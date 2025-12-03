import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Product {
    // 商品属性：id（唯一标识）、name（名称）、price（价格）
    private int id;
    private String name;
    private double price;

    // 构造方法：初始化商品属性
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // getter方法：用于查询商品属性
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // 重写toString方法：方便打印商品信息
    @Override
    public String toString() {
        return "商品ID：" + id + " | 商品名称：" + name + " | 商品价格：" + price + "元";
    }
}

// 商品管理系统类
public class Z08 {
    // 存储商品的列表
    private static List<Product> productList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            // 显示菜单
            System.out.println("\n===== 商品管理系统 =====");
            System.out.println("1. 添加商品");
            System.out.println("2. 查询所有商品");
            System.out.println("3. 按ID查询商品");
            System.out.println("4. 退出系统");
            System.out.print("请选择操作（1-4）：");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    addProduct(scanner);
                    break;
                case 2:
                    queryAllProducts();
                    break;
                case 3:
                    queryProductById(scanner);
                    break;
                case 4:
                    System.out.println("退出系统成功！");
                    scanner.close();
                    return;
                default:
                    System.out.println("错误：无效的操作选择");
            }
        }
    }
    private static void addProduct(Scanner scanner) {
        System.out.print("请输入商品ID：");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (Product product : productList) {
            if (product.getId() == id) {
                System.out.println("错误：该商品ID已存在，添加失败");
                return;
            }
        }
        System.out.print("请输入商品名称：");
        String name = scanner.nextLine();
        System.out.print("请输入商品价格：");
        double price = scanner.nextDouble();
        productList.add(new Product(id, name, price));
        System.out.println("商品添加成功！");
    }
    private static void queryAllProducts() {
        System.out.println("\n===== 所有商品列表 =====");
        if (productList.isEmpty()) {
            System.out.println("暂无商品信息");
        } else {
            for (Product product : productList) {
                System.out.println(product);
            }
        }
    }
    private static void queryProductById(Scanner scanner) {
        System.out.print("请输入要查询的商品ID：");
        int id = scanner.nextInt();
        for (Product product : productList) {
            if (product.getId() == id) {
                System.out.println("\n查询结果：");
                System.out.println(product);
                return;
            }
        }
        System.out.println("错误：未找到ID为" + id + "的商品");
    }
}