enum UserRole {
    ADMIN("管理员", "拥有所有权限"),
    USER("普通用户", "拥有查看和编辑权限"),
    GUEST("访客", "仅拥有查看权限");
    private final String name;
    private final String permission;
    UserRole(String name, String permission) {
        this.name = name;
        this.permission = permission;
    }
    public void showPermission() {
        System.out.println(name + "：" + permission);
    }
}

public class Z18 {
    public static void main(String[] args) {
        for (UserRole role : UserRole.values()) {
            role.showPermission();
        }
    }
}