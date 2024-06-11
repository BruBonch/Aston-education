import task1.Animal;
import task1.Bowl;
import task1.Cat;
import task1.Dog;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Cat> cats = new ArrayList<>() {{
            add(new Cat("Гарфилд"));
            add(new Cat("Том"));
            add(new Cat("Барсик"));
            add(new Cat("Борис"));
        }};

        Bowl bowl = new Bowl();
        bowl.pourFoodInBowl();

        // коты идут кушать
        for (Cat cat : cats) {
            cat.eat(bowl);
        }

        System.out.println("----------------------------------------------");

        // смотрим кому хватило еды
        for (Cat cat : cats) {
            if(cat.isSatiety()) {
                System.out.println("Кот " + cat.getName() + " сыт");
            } else {
                System.out.println("Кот " + cat.getName() + " голоден");
            }
        }
    }
}
