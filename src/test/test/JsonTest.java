package test;

import model.Customer;

import static org.junit.jupiter.api.Assertions.*;

abstract public class JsonTest {
    protected void checkCustomer(Customer c, String name, String username, String password, String address, int age,
                                 String phoneNumber, boolean masterCard, boolean visaCard, String cardNumber) {
        assertEquals(name, c.getName());
        assertEquals(username, c.getUserName());
        assertTrue(c.verifyPassword(password));
        assertEquals(address, c.getAddress());
        assertEquals(age, c.getAge());
        assertEquals(phoneNumber, c.getPhoneNumber());
        assertEquals(masterCard, c.containMasterCard());
        assertEquals(visaCard, c.containVisaCard());
        assertEquals(cardNumber, c.getCardNumber());
    }
}
