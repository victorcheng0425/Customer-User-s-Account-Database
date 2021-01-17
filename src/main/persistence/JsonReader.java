package persistence;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Customer;
import model.CustomerDataBase;
import model.Password;
import org.json.*;

// Represents a reader that reads workroom from JSON data stored in file
// Citation:
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads CustomerDataBase from file and returns it;
    // throws IOException if an error occurs reading data from file
    public CustomerDataBase read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return processDataBase(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: Retrieve Customer Data Base from JSON object and returns it
    private CustomerDataBase processDataBase(JSONObject jsonObject) {
        CustomerDataBase database = new CustomerDataBase();
        addDataBase(database, jsonObject);
        return database;
    }

    // MODIFIES: database
    // EFFECTS: parses Data Base from JSON object and adds them to Customer Data Base
    private void addDataBase(CustomerDataBase database, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("dataBase");
        for (Object json : jsonArray) {
            JSONObject nextThingy = (JSONObject) json;
            addCustomer(database, nextThingy);
        }
    }

    // MODIFIES: database
    // EFFECTS: parses Customer from JSON object and adds it to Data Base
    private void addCustomer(CustomerDataBase database, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String address = jsonObject.getString("address");
        String cardNumber = jsonObject.getString("cardNumber");
        int age = jsonObject.getInt("age");
        boolean masterCardBoolean = jsonObject.getBoolean("masterCardBoolean");
        boolean visaCardBoolean = jsonObject.getBoolean("visaCardBoolean");
        String username = jsonObject.getString("username");
        Password password = new Password(jsonObject.getString("password"), true);
        String phoneNumber = jsonObject.getString("phoneNumber");
        Customer c1 = new Customer(name, username, password.getPassword(), address, age,
                phoneNumber, masterCardBoolean, visaCardBoolean, cardNumber);
        database.addCustomer(c1);
    }
}

