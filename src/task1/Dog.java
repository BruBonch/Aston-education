package task1;

public class Dog extends Animal {
    private static int dogsCount = 0;
    private final int maxRunDistance = 500;
    private final int maxSwimDistance = 10;

    public Dog(String name) {
        this.name = name;
        dogsCount++;
    }
    public static int getDogsCount() {
        return dogsCount;
    }

    @Override
    public void run(int distance) {
        if (distance > maxRunDistance) {
            System.out.println(name + " пробежал " + maxRunDistance + "м." + " и выдохся.");
            return;
        }

        System.out.println(name + " пробежал " + distance + "м.");
    };
    @Override
    public void swim(int distance){
        if (distance > maxSwimDistance) {
            System.out.println(name + " проплыл " + maxSwimDistance + "м." + " выдохся и поплыл обратно.");
            return;
        }

        System.out.println(name + " проплыл " + distance + "м.");
    };

}
