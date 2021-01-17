package test;

import model.Customer;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//Test Customer Class
class CustomerTest {

    private Customer customer1;
    private Customer customer2;

    @BeforeEach
    public void setUp() {
        customer1 = new Customer("Person1", "user1", "121212", "Addr1", 99,
                "2501111111");
        customer2 = new Customer("Person2", "user2", "131313", "Addr2", 50,
                "2501212212");
    }

    @Test
    public void testgetPhoneNumber() {
        assertEquals("2501111111", customer1.getPhoneNumber());
        assertEquals("2501212212", customer2.getPhoneNumber());
    }

    @Test
    public void testchangePhoneNumber() {
        customer1.changePhoneNumber("2501212212");
        customer2.changePhoneNumber("2501111111");
        assertEquals("2501111111", customer2.getPhoneNumber());
        assertEquals("2501212212", customer1.getPhoneNumber());
    }

    @Test
    public void testchangeAddress() {
        customer1.changeAddress("NewAddr1 1314");
        customer2.changeAddress("NewAddr2");
        assertEquals("NewAddr1 1314", customer1.getAddress());
        assertEquals("NewAddr2", customer2.getAddress());
    }

    @Test
    public void testgetName() {
        assertEquals("Person1", customer1.getName());
        assertEquals("Person2", customer2.getName());
    }

    @Test
    public void testgetUserName() {
        assertEquals("user1", customer1.getUserName());
        assertEquals("user2", customer2.getUserName());
    }

    @Test
    public void testverifyPassword() {
        assertTrue(customer1.verifyPassword("121212"));
        assertTrue(customer2.verifyPassword("131313"));
        assertFalse(customer1.verifyPassword("131313"));
        assertFalse(customer2.verifyPassword("121212"));
    }

    @Test
    public void testcustomerResetPassword() {
        customer1.customerResetPassword("131313");
        customer2.customerResetPassword("121212");
        assertTrue(customer2.verifyPassword("121212"));
        assertTrue(customer1.verifyPassword("131313"));
        assertFalse(customer2.verifyPassword("131313"));
        assertFalse(customer1.verifyPassword("121212"));
    }

    @Test
    public void testgetAddress() {
        assertEquals("Addr1", customer1.getAddress());
        assertEquals("Addr2", customer2.getAddress());
    }

    @Test
    public void testgetAge() {
        assertEquals(99, customer1.getAge());
        assertEquals(50, customer2.getAge());
    }

    @Test
    public void testcontainMasterCard() {
        assertFalse(customer1.containMasterCard());
        assertFalse(customer2.containMasterCard());
    }

    @Test
    public void testcontainVisaCard() {
        assertFalse(customer1.containVisaCard());
        assertFalse(customer2.containVisaCard());
    }

    @Test
    public void testgetCardNumber() {
        assertEquals("None", customer1.getCardNumber());
        assertEquals("None", customer2.getCardNumber());
    }

    @Test
    public void testaddMasterCardIfNotExist() {
        assertTrue(customer1.addMasterCard("1234567890123456"));
        assertTrue(customer2.addMasterCard("2345678901234567"));
        assertEquals("1234567890123456", customer1.getCardNumber());
        assertEquals("2345678901234567", customer2.getCardNumber());
    }

    @Test
    public void testaddMasterCardIfMasterCardExist() {
        customer1.addMasterCard("1234567890123456");
        customer2.addMasterCard("2345678901234567");
        assertFalse(customer1.addMasterCard("1234567890123456"));
        assertFalse(customer2.addMasterCard("2345678901234567"));
    }

    @Test
    public void testaddMasterCardIfVisaCardExist() {
        customer1.addVisaCard("1234567890123456");
        customer2.addVisaCard("2345678901234567");
        assertFalse(customer1.addMasterCard("1234567890123456"));
        assertFalse(customer2.addMasterCard("2345678901234567"));
    }


    @Test
    public void testaddVisaCardIfNotExist() {
        assertTrue(customer1.addVisaCard("1234567890123456"));
        assertTrue(customer2.addVisaCard("2345678901234567"));
        assertEquals("1234567890123456", customer1.getCardNumber());
        assertEquals("2345678901234567", customer2.getCardNumber());
    }

    @Test
    public void testaddVisaCardIfVisaCardExist() {
        customer1.addVisaCard("1234567890123456");
        customer2.addVisaCard("2345678901234567");
        assertFalse(customer1.addVisaCard("1234567890123456"));
        assertFalse(customer2.addVisaCard("2345678901234567"));
    }

    @Test
    public void testaddVisaCardIfMasterCardExist() {
        customer1.addMasterCard("1234567890123456");
        customer2.addMasterCard("2345678901234567");
        assertFalse(customer1.addVisaCard("1234567890123456"));
        assertFalse(customer2.addVisaCard("2345678901234567"));
    }

    @Test
    public void testremoveCardMasterCard() {
        customer1.addMasterCard("1234567890123456");
        customer2.addMasterCard("2345678901234567");
        assertTrue(customer1.containMasterCard());
        assertTrue(customer2.containMasterCard());
        assertFalse(customer1.containVisaCard());
        assertFalse(customer2.containVisaCard());
        assertEquals("1234567890123456", customer1.getCardNumber());
        assertEquals("2345678901234567", customer2.getCardNumber());
        customer1.removeCard();
        customer2.removeCard();
        assertFalse(customer1.containMasterCard());
        assertFalse(customer2.containMasterCard());
        assertFalse(customer1.containVisaCard());
        assertFalse(customer2.containVisaCard());
        assertEquals("None", customer1.getCardNumber());
        assertEquals("None", customer2.getCardNumber());
    }

    @Test
    public void testremoveCardVisaCard() {
        customer1.addVisaCard("1234567890123456");
        customer2.addVisaCard("2345678901234567");
        assertFalse(customer1.containMasterCard());
        assertFalse(customer2.containMasterCard());
        assertTrue(customer1.containVisaCard());
        assertTrue(customer2.containVisaCard());
        assertEquals("1234567890123456", customer1.getCardNumber());
        assertEquals("2345678901234567", customer2.getCardNumber());
        customer1.removeCard();
        customer2.removeCard();
        assertFalse(customer1.containMasterCard());
        assertFalse(customer2.containMasterCard());
        assertFalse(customer1.containVisaCard());
        assertFalse(customer2.containVisaCard());
        assertEquals("None", customer1.getCardNumber());
        assertEquals("None", customer2.getCardNumber());
    }

    @Test
    public void testtoJson() {
        Customer customer3 = new Customer("Person1", "user1", "123456", "Addr1", 99,
                "2501111111", true, false, "473011111212");
        JSONObject obj = customer3.toJson();
        assertEquals("Person1", obj.getString("name"));
        assertEquals( "user1", obj.getString("username"));
        assertEquals("13579;", obj.getString("password"));
        assertEquals( "Addr1", obj.getString("address"));
        assertEquals(99, obj.getInt("age"));
        assertEquals("2501111111", obj.getString("phoneNumber"));
        assertTrue(obj.getBoolean("masterCardBoolean"));
        assertFalse(obj.getBoolean("visaCardBoolean"));
        assertEquals("473011111212", obj.getString("cardNumber"));
        assertEquals(9, JSONObject.getNames(obj).length);
    }

}