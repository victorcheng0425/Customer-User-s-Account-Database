package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//CustomerDataBase Class will manage Customer Data/Object
//This Class use HashMap to store User account(Customer object), the key is the username
//JSON code from https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class CustomerDataBase {

    private int size;
    private Map<String, Customer> dataBase;
    private String nameOfClass = "Customer Data Base";

    public CustomerDataBase() {
        size = 0;
        dataBase = new HashMap<>();
    }

    //MODIFIES: this
    //EFFECTS: return false if username(key) existed,
    //otherwise the application will add the customer object to the database and return true.
    public boolean addCustomer(Customer c) {
        if (containUserName(c.getUserName())) {
            return false;
        }
        dataBase.put(c.getUserName(), c);
        size++;
        return true;
    }

    //EFFECTS: Return true if username already existed, false otherwise
    public boolean containUserName(String name) {
        return dataBase.containsKey(name);
    }

    //MODIFIES: this
    //EFFECTS: if username exists in the database, delete customer object from the database and return true
    //return false otherwise
    public boolean deleteCustomer(String username) {
        if (containUserName(username)) {
            dataBase.remove(username);
            return true;
        }
        return false;
    }

    //EFFECTS: if username exists in the database, return that customer object
    //otherwise return null
    public Customer findObject(String username) {
        if (containUserName(username)) {
            return dataBase.get(username);
        }
        return null;
    }

    //EFFECTS: return the name of the class/database
    public String getDataBaseName() {
        return nameOfClass;
    }

    //EFFECTS: returns this as JSON object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", nameOfClass);
        json.put("dataBase", dbToJson());
        return json;
    }

    // EFFECTS: returns Customer in this CustomerDataBase as a JSON array
    private JSONArray dbToJson() {
        JSONArray jsonArray = new JSONArray();

        for (String key : dataBase.keySet()) {
            //jsonArray.put(key);
            jsonArray.put(findObject(key).toJson());
        }
        return jsonArray;
    }

    //EFFECTS: return the size of database
    public int getSize() {
        return size;
    }

    //EFFECTS: Get all key set
    public Set<String> getAllKey() {
        return dataBase.keySet();
    }
}
