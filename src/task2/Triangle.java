package task2;

public class Triangle implements Measurable, Colorable {
    private String backgroundColor;
    private String borderColor;
    private double a;
    private double b;
    private double c;
    private double h;

    public Triangle(double a, double b, double c, double h) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.h = h;
    }

    public void setColor(String backgroundColor, String borderColor) {
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
    }

    public double areaCalculation() {
        return a * h / 2;
    }

    ;

    public double perimeterCalculation() {
        return a + b + c;
    }

    ;

    public void printInfo() {
        System.out.println("Triangle info: ");
        System.out.println(
                "\t background color - " + backgroundColor + "\n"
                        + "\t border color - " + borderColor + "\n"
                        + "\t area - " + areaCalculation() + "\n"
                        + "\t perimeter - " + perimeterCalculation() + "\n"
        );
    }
}
