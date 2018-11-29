package lesson3;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class PhoneBook {
    private HashMap phonebook;

    public PhoneBook() {
        this.phonebook = new HashMap();
    }

    public void getPhone(String fio) {
        Iterator<Map.Entry<String, String>> iter = phonebook.entrySet().iterator();
        while(iter.hasNext()){
            Map.Entry<String, String> e = iter.next();
            if(e.getValue().equals(fio))
                System.out.println(fio + " телефон " + e.getKey());
        }
    }

    public void addPhone(String fio, String phone) {
        phonebook.put(phone, fio);
    }
}
