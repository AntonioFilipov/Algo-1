package week3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class PhoneBook {

    public static class Contact implements Comparable<Contact> {
        public int number;
        public String name;

        public Contact(int number, String name) {
            this.number = number;
            this.name = name;
        }

        @Override
        public String toString() {
            return "[" + number + ", " + name + "]";
        }

        @Override
        public int compareTo(Contact o) {
            return this.number - o.number;
        }
    }

    public static final int NOT_FOUND = -1;

    public static int interpolationSearch(List<Contact> contacts, int key) {
        int low = 0, high = contacts.size() - 1, mid = 0;
        double p = 0.0;

        while (high - low >= 0) {

            if (contacts.get(low).number == contacts.get(high).number) {
                if (contacts.get(low).number == key) {
                    return low;
                } else {
                    return NOT_FOUND;
                }
            }

            p = (key - contacts.get(low).number) / (contacts.get(high).number - contacts.get(low).number);
            if (p < 0.0 && p > 1.0) {
                return NOT_FOUND;
            }
            mid = (int) (low + Math.round(p * (high - low)));

            if (contacts.get(mid).number < key) {
                low = mid + 1;
            } else if (contacts.get(mid).number > key) {
                high = mid - 1;
            } else {
                return mid;
            }
        }

        return NOT_FOUND;
    }

    public static List<String> lookUpNames(List<Contact> phoneBook, List<Integer> numbers) {
        List<String> result = new ArrayList<String>();

        Collections.sort(phoneBook);
        
        for (Integer number : numbers) {
            int index = interpolationSearch(phoneBook, number);
            if (index != NOT_FOUND) {
                result.add(phoneBook.get(index).name);
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int phoneBookLength = scanner.nextInt(), queriesCount = scanner.nextInt();
        List<Contact> phoneBook = new ArrayList<Contact>();
        
        for (int i = 0; i < phoneBookLength; ++i) {
            phoneBook.add(new Contact(scanner.nextInt(), scanner.next()));
        }
        
        Collections.sort(phoneBook);
        
        for (int i = 0; i < queriesCount; ++i) {
            int index = interpolationSearch(phoneBook, scanner.nextInt());            
            System.out.print(index != NOT_FOUND ? phoneBook.get(index).name : NOT_FOUND);
            System.out.println();
        }
        
        scanner.close();
    }
    
}
