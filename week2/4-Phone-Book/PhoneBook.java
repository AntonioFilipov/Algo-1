import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class PhoneBook {

    public static class Contact {

        public String name;
        public int number;
        
        public Contact(int number, String name){
            this.number = number;
            this.name = name;
        }
    }

    // Find the names of people based on their phone numbers.
    public static List<String> lookupNames(List<Contact> phoneBook, List<Integer> numbers) {
        Map<Integer, String> map = new HashMap<Integer, String>();
        List<String> result = new ArrayList<String>();
        for (int i = 0; i < phoneBook.size(); i++) {
            map.put(phoneBook.get(i).number, phoneBook.get(i).name);
        }
        
        for (int i = 0; i < numbers.size(); i++) {
            result.add(map.get(numbers.get(i)));
        }
        
        return result;
    }

    public static void main(String[] args) {
        List<Integer> numbers = new Vector<Integer>();
        List<Contact> contacts = new Vector<Contact>();
        List<String> result = new ArrayList<String>();
        contacts.add(new Contact(1, "Stanislav"));
        contacts.add(new Contact(15, "Rado"));
        contacts.add(new Contact(6, "Ivan"));
        contacts.add(new Contact(8, "Ivan"));
        
        Collections.addAll(numbers, 15, 8);
        
        result = lookupNames(contacts, numbers);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }

    }

}
