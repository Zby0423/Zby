import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Z11 {
    private static final String FILE_PATH = "test.txt";
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入要写入文件的内容：");
        String content = scanner.nextLine();
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(content);
            System.out.println("内容已成功写入文件：" + FILE_PATH);
        } catch (IOException e) {
            System.out.println("写入文件失败：" + e.getMessage());
        }
        System.out.println("\n从文件中读取的内容：");
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("读取文件失败：" + e.getMessage());
        }
        scanner.close();
    }
}