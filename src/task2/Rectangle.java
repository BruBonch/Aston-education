package task2;

public class Rectangle implements Measurable {
    private String backgroundColor;
    private String borderColor;
    private double a;
    private double b;

    public Rectangle(String backgroundColor, String borderColor, double a, double b) {
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.a = a;
        this.b = b;
    }

    public double areaCalculation() {
        return a * b;
    }

    public double perimeterCalculation() {
        return 2 * (a + b);
    }

    public void printInfo() {
        System.out.println("Rectangle info: ");
        System.out.println(
                "\t background color - " + backgroundColor + "\n"
                        + "\t border color - " + borderColor + "\n"
                        + "\t area - " + areaCalculation() + "\n"
                        + "\t perimeter - " + perimeterCalculation() + "\n"
        );
    }
}
