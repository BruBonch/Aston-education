package task1;

public class Cat extends Animal {
    private static int catsCount = 0;
    private final int maxRunDistance = 200;
    private boolean satiety = false;

    public Cat (String name) {
        this.name = name;
        catsCount++;
    }
    public static int getCatsCount() {
        return catsCount;
    }

    public String getName() {
        return name;
    }
    @Override
    public void run(int distance) {
        if (distance > maxRunDistance) {
            System.out.println(name + " пробежал " + maxRunDistance + "м." + " и выдохся");
            return;
        }

        System.out.println(name + " пробежал " + distance + "м.");
    };
    @Override
    public void swim(int distance) {
        System.out.println(name + " боится воды и не будет никуда плыть!");
    };

    public void eat(Bowl bowl) {
        int amountFood = bowl.getFeedAmount();

        // каждый кот съедает случайное количество еды от 1 до 25
        int amountFoodEaten = (int) (Math.random() * 25) + 1;

        if ((amountFood - amountFoodEaten) < 0) {
            System.out.println("Коту " + name + " не хватило еды, он остался голодным :(");
        } else {
            bowl.eatFromBowl(amountFoodEaten);
            System.out.println("Кот " + name + " наелся и пошел спать");
            satiety = true;
        }
    }

    public boolean isSatiety() {
        return satiety;
    }
}
