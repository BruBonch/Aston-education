import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    static private Map<String, List<String>> listOfAbonents = new HashMap<>();

    public static List<String> getPhoneNumber(String lastName) {
        return listOfAbonents.get(lastName);
    }

    public static void addPhoneNumber(String lastName, String firstName, String phoneNumber) {
        if (listOfAbonents.containsKey(lastName)) {
            List<String> phoneNumbers = listOfAbonents.get(lastName);
            phoneNumbers.add(String.format("%s %s - %s", lastName, firstName, phoneNumber));
        } else {
            List<String> phoneNumbers = new ArrayList<>();
            phoneNumbers.add(String.format("%s %s - %s", lastName, firstName, phoneNumber));

            listOfAbonents.put(lastName, phoneNumbers);
        }
    }
}
