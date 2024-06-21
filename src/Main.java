import task1.Animal;
import task1.Bowl;
import task1.Cat;
import task1.Dog;
import task2.Circle;
import task2.Measurable;
import task2.Rectangle;
import task2.Triangle;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Task 1");
        List<Cat> cats = new ArrayList<>() {{
            add(new Cat("Гарфилд"));
            add(new Cat("Том"));
            add(new Cat("Барсик"));
            add(new Cat("Борис"));
        }};

        Bowl bowl = new Bowl();
        bowl.pourFoodInBowl(50);

        // коты идут кушать
        for (Cat cat : cats) {
            cat.eat(bowl);
        }

        // смотрим кому хватило еды
        for (Cat cat : cats) {
            if(cat.isSatiety()) {
                System.out.println("Кот " + cat.getName() + " сыт");
            } else {
                System.out.println("Кот " + cat.getName() + " голоден");
            }
        }

        System.out.println("\nСоздано котов - " + Cat.getCatsCount());

        Dog pluto = new Dog("Плуто");

        System.out.println("Создано собак - " + Dog.getDogsCount());
        System.out.println("Всего создано " + Animal.getAnimalsCount() + " животных\n");



        System.out.println("Task 2");
        Rectangle rectangle = new Rectangle(10, 15);
        rectangle.setColor("green", "black");
        rectangle.printInfo();

        Triangle triangle = new Triangle(10, 12, 14, 8);
        triangle.setColor("black", "pink");
        triangle.printInfo();

        Circle circle = new Circle(4);
        circle.setColor("blue", "red");
        circle.printInfo();
    }
}
