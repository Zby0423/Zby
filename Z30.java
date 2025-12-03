import java.util.*;
import java.util.stream.Collectors;

abstract class Employee {
    private String empId;
    private String name;
    private double baseSalary;
    public Employee(String empId, String name, double baseSalary) {
        this.empId = empId;
        this.name = name;
        this.baseSalary = baseSalary;
    }
    public abstract double calculateSalary();
    public String getEmployeeInfo() {
        return String.format("员工ID：%s，姓名：%s，基础工资：%.2f，实际工资：%.2f",
                empId, name, baseSalary, calculateSalary());
    }
    public double getBaseSalary() {
        return baseSalary;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(empId, employee.empId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(empId);
    }
}
class FullTimeEmployee extends Employee {
    private double performanceBonus;

    public FullTimeEmployee(String empId, String name, double baseSalary, double performanceBonus) {
        super(empId, name, baseSalary);
        this.performanceBonus = performanceBonus;
    }
    @Override
    public double calculateSalary() {
        return getBaseSalary() + performanceBonus;
    }
}

class PartTimeEmployee extends Employee {
    private double hourlyWage;
    private int workHours;

    public PartTimeEmployee(String empId, String name, double baseSalary, double hourlyWage, int workHours) {
        super(empId, name, baseSalary);
        this.hourlyWage = hourlyWage;
        this.workHours = workHours;
    }
    @Override
    public double calculateSalary() {
        return hourlyWage * workHours;
    }
}

class SalesEmployee extends Employee {
    private double salesAmount;
    public SalesEmployee(String empId, String name, double baseSalary, double salesAmount) {
        super(empId, name, baseSalary);
        this.salesAmount = salesAmount;
    }
    @Override
    public double calculateSalary() {
        return getBaseSalary() + salesAmount * 0.05;
    }
}

class Company {
    private List<Employee> employees = new ArrayList<>();
    public void addEmployee(Employee employee) {
        if (!employees.contains(employee)) {
            employees.add(employee);
            System.out.println("员工添加成功：" + employee.getEmployeeInfo());
        } else {
            System.out.println("员工ID已存在，添加失败：" + employee.getEmployeeInfo());
        }
    }
    public void removeEmployee(String empId) {
        boolean removed = employees.removeIf(emp -> emp.getEmployeeInfo().contains(empId));
        System.out.println(removed ? "员工删除成功" : "员工不存在，删除失败");
    }
    public double calculateTotalSalary() {
        return employees.stream()
                .mapToDouble(Employee::calculateSalary)
                .sum();
    }
    public List<Employee> sortEmployeesBySalary() {
        List<Employee> sortedList = new ArrayList<>(employees);
        Collections.sort(sortedList, Comparator.comparingDouble(Employee::calculateSalary));
        return sortedList;
    }
    public Employee findHighestSalaryEmployee() {
        return employees.stream()
                .max(Comparator.comparingDouble(Employee::calculateSalary))
                .orElse(null);
    }
    public Employee findLowestSalaryEmployee() {
        return employees.stream()
                .min(Comparator.comparingDouble(Employee::calculateSalary))
                .orElse(null);
    }
    public void showAllEmployees() {
        List<Employee> sortedEmployees = sortEmployeesBySalary();
        System.out.println("\n=== 所有员工信息（按工资升序）===");
        sortedEmployees.forEach(emp -> System.out.println(emp.getEmployeeInfo()));
        System.out.println("=== 统计信息 ===");
        System.out.printf("总员工数：%d\n", employees.size());
        System.out.printf("总工资：%.2f\n", calculateTotalSalary());
        System.out.printf("最高工资员工：%s\n", findHighestSalaryEmployee() != null ?
                findHighestSalaryEmployee().getEmployeeInfo() : "无");
        System.out.printf("最低工资员工：%s\n", findLowestSalaryEmployee() != null ?
                findLowestSalaryEmployee().getEmployeeInfo() : "无");
    }
}

public class Z30 {
    public static void main(String[] args) {
        Company company = new Company();
        company.addEmployee(new FullTimeEmployee("F001", "张三", 8000, 2000));
        company.addEmployee(new PartTimeEmployee("P001", "李四", 0, 50, 80));
        company.addEmployee(new SalesEmployee("S001", "王五", 5000, 100000));
        company.addEmployee(new FullTimeEmployee("F002", "赵六", 9000, 3000));
        company.showAllEmployees();
        company.removeEmployee("P001");
        System.out.println("\n删除兼职员工后：");
        company.showAllEmployees();
    }
}