package test;

import model.CustomerDataBase;
import org.junit.jupiter.api.Test;
import persistence.JsonReader;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

//Test cases for JsonReader
// Citation:
//https://github.students.cs.ubc.ca/CPSC210/JsonSerializationD
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            CustomerDataBase db = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyDataBase() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDataBase.json");
        try {
            CustomerDataBase db = reader.read();
            assertEquals("Customer Data Base", db.getDataBaseName());
            assertEquals(0, db.getSize());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralDataBase() {
        JsonReader reader = new JsonReader("./data/testWriterGeneralDataBase.json");
        try {
            CustomerDataBase db = reader.read();
            assertEquals("Customer Data Base", db.getDataBaseName());
            assertEquals(2, db.getSize());
            assertTrue(db.containUserName("user1"));
            assertTrue(db.containUserName("user2"));
            checkCustomer(db.findObject("user1"), "Person1", "user1", "123456",
                    "Addr1", 99, "2501111", true, false, "47301111");
            checkCustomer(db.findObject("user2"), "Person2", "user2", "654321",
                    "Addr2", 99, "2501231", false, true, "37401111");
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
