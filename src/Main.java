import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] strings = {
                "dog",
                "cat",
                "horse",
                "lion",
                "rat",
                "snake",
                "panda",
                "parrot",
                "hamster",
                "mouse",
                "chicken",
                "cow",
                "pig",
                "sheep",
                "rabbit",
                "goat",
                "donkey",
                "cat",
                "lion",
                "panda",
                "cat",
                "horse"
        };

        Set<String> uniqueAnimals = new HashSet<>(Arrays.asList(strings));
        System.out.println(uniqueAnimals);

        Map<String, Integer> duplicateInfo = new HashMap<>();

        for(String animal : strings) {
            if (duplicateInfo.containsKey(animal)) {
                duplicateInfo.put(animal, duplicateInfo.get(animal) + 1);
            } else {
                duplicateInfo.put(animal, 1);
            }
        }
        // вывод мапы в которой ключ это слово, а значение количество его повторений
        System.out.println(duplicateInfo);

        System.out.println("--------------------------------------");

        PhoneBook.addPhoneNumber("Курлыков", "Иван","89111345687");
        PhoneBook.addPhoneNumber("Бискупов", "Алексей","89213467832");
        PhoneBook.addPhoneNumber("Андреев", "Игорь", "89536675424");
        PhoneBook.addPhoneNumber("Курлыков", "Илья", "89991457685");

        System.out.println(PhoneBook.getPhoneNumber("Курлыков"));
    }
}
