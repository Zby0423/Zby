import java.io.FileInputStream;
import java.util.Properties;

public class Z24 {
    public static void main(String[] args) {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream("config.properties")) {
            props.load(fis);
            String username = props.getProperty("db.username");
            String password = props.getProperty("db.password");
            System.out.println("用户名：" + username + "，密码：" + password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}