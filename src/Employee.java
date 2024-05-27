public class Employee {
    private String fullName;
    private String position;
    private String email;
    private String phoneNumber;
    private String salary;
    private int age;

    public Employee(String fullName, String position, String email, String phoneNumber, String salary, int age) {
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }

    @Override
    public String toString() {
        return "{fullName = '" + fullName + '\'' +
                ", position = '" + position + '\'' +
                ", email = '" + email + '\'' +
                ", phoneNumber = '" + phoneNumber + '\'' +
                ", salary = '" + salary + '\'' +
                ", age = " + age +
                '}';
    }

    public void printInfo() {
        System.out.println(this);
    }
}
