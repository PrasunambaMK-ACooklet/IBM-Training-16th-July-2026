package Junit;

import java.util.HashMap;
import java.util.Map;

public class Demo04_DatabaseService {
    private Map<String, String> store = new HashMap<>();

    public void connect() {
        System.out.println("Connected to DB");
    }

    public void disconnect() {
        System.out.println("Disconnected from DB");
    }

    public void insert(String key, String value) {
        store.put(key, value);
    }

    public String fetch(String key) {
        return store.get(key);
    }
}
