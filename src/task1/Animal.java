package task1;

public abstract class Animal {
    private static int animalsCount = 0;
    String name;

    public Animal() {
        animalsCount++;
    }
    public static int getAnimalsCount() {
        return animalsCount;
    }
    public abstract void run(int distance);
    public abstract void swim(int distance);

}
