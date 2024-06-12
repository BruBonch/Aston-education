package task2;

public class Circle implements Measurable {
    private String backgroundColor;
    private String borderColor;
    private double r;

    public Circle(String backgroundColor, String borderColor, double r) {
        this.backgroundColor = backgroundColor;
        this.borderColor = borderColor;
        this.r = r;
    }

    public double areaCalculation() {
        return 3.14 * (r * r);
    };
    public double perimeterCalculation() {
        return  2 * 3.14 * r;
    };

    public void printInfo() {
        System.out.println("Circle info: ");
        System.out.println(
                "\t background color - " + backgroundColor + "\n"
                        + "\t border color - " + borderColor + "\n"
                        + "\t area - " + areaCalculation() + "\n"
                        + "\t perimeter - " + perimeterCalculation() + "\n"
        );
    }
}
