
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Z19 {
    public static void main(String[] args) {
        Class<?> clazz = String.class;
        System.out.println("类名：" + clazz.getName());
        System.out.println("\n属性：");
        for (Field field : clazz.getDeclaredFields()) {
            System.out.println(field);
        }
        System.out.println("\n方法：");
        for (Method method : clazz.getDeclaredMethods()) {
            System.out.println(method);
        }
    }
}