interface Shape {
    double calculateArea();
    double calculatePerimeter();
    String getShapeName();
    void displayInfo();
}
class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
    @Override
    public String getShapeName() {
        return "圆形";
    }
    @Override
    public void displayInfo() {
        System.out.println(getShapeName() + " - 半径：" + radius
                + "，面积：" + calculateArea()
                + "，周长：" + calculatePerimeter());
    }
}
class Rectangle implements Shape {
    private double width, height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    @Override
    public double calculateArea() {
        return width * height;
    }
    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }
    @Override
    public String getShapeName() {
        return "矩形";
    }
    @Override
    public void displayInfo() {
        System.out.println(getShapeName() + " - 宽：" + width + "，高：" + height
                + "，面积：" + calculateArea()
                + "，周长：" + calculatePerimeter());
    }
}

class Triangle implements Shape {
    private double a, b, c;
    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
    @Override
    public double calculateArea() {
        double s = (a + b + c) / 2;
        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }
    @Override
    public double calculatePerimeter() {
        return a + b + c;
    }
    @Override
    public String getShapeName() {
        return "三角形";
    }
    @Override
    public void displayInfo() {
        System.out.println(getShapeName() + " - 三边：" + a + "," + b + "," + c
                + "，面积：" + calculateArea()
                + "，周长：" + calculatePerimeter());
    }
}

class ShapeManager {
    public static void main(String[] args) {
        Shape circle = new Circle(5);
        Shape rectangle = new Rectangle(4, 6);
        Shape triangle = new Triangle(3, 4, 5);
        circle.displayInfo();
        rectangle.displayInfo();
        triangle.displayInfo();
    }
}
