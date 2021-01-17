package test;


import model.Customer;
import model.CustomerDataBase;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Test cases for JsonWriter
// Citation:
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationD
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            CustomerDataBase wr = new CustomerDataBase();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyDataBase() {
        try {
            CustomerDataBase db = new CustomerDataBase();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDataBase.json");
            writer.open();
            writer.write(db);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDataBase.json");
            db = reader.read();
            assertEquals("Customer Data Base", db.getDataBaseName());
            assertEquals(0, db.getSize());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralDataBase() {
        try {
            CustomerDataBase dataBase = new CustomerDataBase();
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDataBase.json");
            Customer c1 = new Customer("Person1", "user1", "123456", "Addr1", 99,
                    "2501111", true, false, "47301111");
            Customer c2 = new Customer("Person2", "user2", "654321", "Addr2", 99,
                    "2501231", false, true, "37401111");
            dataBase.addCustomer(c1);
            dataBase.addCustomer(c2);
            writer.open();
            writer.write(dataBase);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDataBase.json");
            dataBase = reader.read();
            assertEquals("Customer Data Base", dataBase.getDataBaseName());
            assertEquals(2, dataBase.getSize());
            assertTrue(dataBase.containUserName("user1"));
            assertTrue(dataBase.containUserName("user2"));
            checkCustomer(dataBase.findObject("user1"), "Person1", "user1", "123456",
                    "Addr1", 99, "2501111", true, false, "47301111");
            checkCustomer(dataBase.findObject("user2"), "Person2", "user2", "654321",
                    "Addr2", 99, "2501231", false, true, "37401111");
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
