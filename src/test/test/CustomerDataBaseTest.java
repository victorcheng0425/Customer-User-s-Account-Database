package test;

import model.Customer;
import model.CustomerDataBase;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

//Test CustomerDataBase Class
public class CustomerDataBaseTest {

    private CustomerDataBase database;
    private Customer c1;
    private Customer c2;

    @BeforeEach
    public void setup() {
        database = new CustomerDataBase();
        c1 = new Customer("Person1", "user1", "121212", "Addr1", 99,
                "2501111111");
        c2 = new Customer("Person2", "user2", "131313", "Addr2", 50,
                "2501212212");
    }

    @Test
    public void testaddCustomer() {
        assertTrue(database.addCustomer(c1));
        assertTrue(database.addCustomer(c2));
        assertFalse(database.addCustomer(c1));
        assertFalse(database.addCustomer(c2));
    }

    @Test
    public void testcontainUserNameIfExist() {
        database.addCustomer(c1);
        database.addCustomer(c2);
        assertTrue(database.containUserName(c1.getUserName()));
        assertTrue(database.containUserName(c2.getUserName()));
    }

    @Test
    public void testcontainUserNameIfNotExist() {
        assertFalse(database.containUserName(c1.getUserName()));
        assertFalse(database.containUserName(c2.getUserName()));
    }

    @Test
    public void testdeleteCustomerIfExist() {
        database.addCustomer(c1);
        database.addCustomer(c2);
        assertTrue(database.containUserName(c1.getUserName()));
        assertTrue(database.containUserName(c2.getUserName()));
        assertTrue(database.deleteCustomer(c1.getUserName()));
        assertTrue(database.deleteCustomer(c2.getUserName()));
    }

    @Test
    public void testdeleteCustomerIfNotExist() {
        assertFalse(database.deleteCustomer(c1.getUserName()));
        assertFalse(database.deleteCustomer(c2.getUserName()));

    }

    @Test
    public void testfindObject() {
        database.addCustomer(c1);
        Customer temp1 = database.findObject(c1.getUserName());
        Customer temp2 = database.findObject(c2.getUserName());
        assertNull(temp2);
        assertEquals(c1.getUserName(), temp1.getUserName());
    }

    @Test
    public void testgetDataBaseName() {
        assertEquals("Customer Data Base",database.getDataBaseName());
    }

    @Test
    public void testtoJsonAnddbToJson() {
        Customer c3 = new Customer("Person1", "user1", "123456", "Addr1", 99,
                "2501111111", true, false, "473011111212");
        database.addCustomer(c3);
        JSONObject obj = database.toJson();
        assertEquals(obj.getString("name"), "Customer Data Base");
        JSONArray jsonArray = obj.getJSONArray("dataBase");
        JSONObject object = jsonArray.getJSONObject(0);
        assertEquals(object.getString("name"), "Person1");
        assertEquals(object.getString("username"), "user1");
        assertEquals(object.getString("password"), "13579;");
        assertEquals(object.getString("address"), "Addr1");
        assertEquals(object.getInt("age"), 99);
        assertEquals(object.getString("phoneNumber"), "2501111111");
        assertTrue(object.getBoolean("masterCardBoolean"));
        assertFalse(object.getBoolean("visaCardBoolean"));
        assertEquals(object.getString("cardNumber"), "473011111212");
        assertEquals(2, JSONObject.getNames(obj).length);
        assertEquals(9, JSONObject.getNames(object).length);
    }

    @Test
    public void testgetSize() {
        assertEquals(0, database.getSize());
        database.addCustomer(c1);
        assertEquals(1, database.getSize());
        database.addCustomer(c2);
        assertEquals(2, database.getSize());
    }

    @Test
    public void testgetAllKey() {
        database.addCustomer(c1);
        database.addCustomer(c2);
        Set<String> keyset = database.getAllKey();
        assertTrue(keyset.contains("user1"));
        assertTrue(keyset.contains("user2"));
        assertTrue(keyset.size() == 2);
    }

}
