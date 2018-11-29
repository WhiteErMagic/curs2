package lesson3;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        String[] arr = new String[]{"В", "пресс-службе",  "снаряде", "Техмаш", "заявили",
                                    "что", "работы", "по",  "созданию", "и" ,
                                    "испытаниям", "беспилотника", "запускаемого", "в", "снаряде",
                                    "концерна", "системы", "залпового", "беспилотника", "работы"};
        HashSet<String> m_set = new HashSet();
        for (int i = 0; i < arr.length; i++) {
            m_set.add(arr[i]);
        }

        System.out.println("Список без повторений:");
        System.out.println(m_set);

        System.out.println();
        System.out.println("Статистика элементов:");
        ArrayList<String> m_arr = new ArrayList<String>(Arrays.asList(arr));

        for (int i = 0; i < m_set.size(); i++) {
            System.out.println('"' + arr[i] + '"' + " встречается " + Collections.frequency(m_arr, arr[i]) + " раз");
        }

        //Заполнение телефонной книги
        PhoneBook phoneBook = new PhoneBook();
        phoneBook.addPhone("Иванов", "1111111");
        phoneBook.addPhone("Петров", "2222222");
        phoneBook.addPhone("Иванов", "3333333");
        phoneBook.addPhone("Сидоров", "4444444");
        phoneBook.addPhone("Иванов", "5555555");

        System.out.println();
        System.out.println("Поиск номера телефона по фамилии");
        phoneBook.getPhone("Петров");
        System.out.println();
        phoneBook.getPhone("Иванов");

    }
}
