package com.team22.backend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.OptionalInt;
import java.util.Set;
import java.util.Date;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import com.team22.backend.Entity.*;
import com.team22.backend.Repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CustomerTest {

	@Autowired
	private CustomerRepository customerRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Validator validator;
	@Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
	}
	
    @Test
	public void testCustomerInsertDataSuccess() {
                    Customer customerdb1 = new Customer();
                    String dateB1 = "20:04:1997";
                    DateTimeFormatter lformatterb = DateTimeFormatter.ofPattern("dd:MM:yyyy");
                    LocalDate dateB1c = LocalDate.parse(dateB1, lformatterb);
                    customerdb1.setCustomerIDs("C7");
                    customerdb1.setCustomerName("KKA");
                    customerdb1.setCustomerPassword("123456");
                    customerdb1.setCustomerPhone("0987654321"); 
                    customerdb1.setCustomerBirthday(dateB1c); 
                    customerdb1.setCustomerGender("Man"); 
                    customerdb1.setCustomerAddress("Korat");
        try {
            entityManager.persist(customerdb1);
            entityManager.flush();
            System.out.println(); 
            System.out.println();   
            System.out.println("\n\n\n\n\n\n\n\n\n----------------->> 1.Test Customer Insert DataSuccess \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(); 
            System.out.println(); 
        } catch(javax.validation.ConstraintViolationException e) {
            fail("Test Customer Insert DataSuccess Error");
        }
	}
	
    @Test
	public void testCustomerPhoneSizeEqual10Than() {
        Customer customerdb2 = new Customer();
        String dateB1 = "20:04:1997";
        DateTimeFormatter lformatterb = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate dateB1c = LocalDate.parse(dateB1, lformatterb);
		customerdb2.setCustomerIDs("C8");
		customerdb2.setCustomerName("KKB");
		customerdb2.setCustomerPassword("123456");
        customerdb2.setCustomerPhone("0987654321111"); 
        customerdb2.setCustomerBirthday(dateB1c); 
        customerdb2.setCustomerGender("Women"); 
        customerdb2.setCustomerAddress("Korat"); 
        try {
            entityManager.persist(customerdb2);
            entityManager.flush();
            fail("CustomerPhone Size Not Equal 10 Error");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println(); 
            System.out.println();   
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> 2 .CustomerPhone Size Not Equal 10 Error \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(); 
            System.out.println(); 
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 2);
        }
    }

    @Test
	public void testCustomerAddressLessThan() {
        Customer customerdb3 = new Customer();
        String dateB1 = "20:04:1997";
        DateTimeFormatter lformatterb = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate dateB1c = LocalDate.parse(dateB1, lformatterb);
		customerdb3.setCustomerIDs("C10");
		customerdb3.setCustomerName("KKB");
		customerdb3.setCustomerPassword("123456");
        customerdb3.setCustomerPhone("0987654321"); 
        customerdb3.setCustomerBirthday(dateB1c); 
        customerdb3.setCustomerGender("Women"); 
        customerdb3.setCustomerAddress("K");
        try {
            entityManager.persist(customerdb3);
            entityManager.flush();
            fail("CustomerPhone Size Not Equal 10 Long Error");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println(); 
            System.out.println();   
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> 3.CustomerPhone Size CustomerAddress Less Error \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(); 
            System.out.println(); 
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }

    @Test
	public void testCustomerAddressLongThan() {
        Customer customerdb4 = new Customer();
        String dateB1 = "20:04:1997";
        DateTimeFormatter lformatterb = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate dateB1c = LocalDate.parse(dateB1, lformatterb);
		customerdb4.setCustomerIDs("C10");
		customerdb4.setCustomerName("KKB");
		customerdb4.setCustomerPassword("123456");
        customerdb4.setCustomerPhone("0987654321"); 
        customerdb4.setCustomerBirthday(dateB1c); 
        customerdb4.setCustomerGender("Man"); 
        customerdb4.setCustomerAddress("01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567891");
        
        try {
            entityManager.persist(customerdb4);
            entityManager.flush();
            fail("CustomerAddress Size 100 Long Error");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println(); 
            System.out.println();   
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> 3.CustomerAddress Size Long 100 Error \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(); 
            System.out.println(); 
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }

    @Test
	public void testCustomerNotNull() {
		Customer customerdb5 = new Customer();
		customerdb5.setCustomerIDs(null);
		customerdb5.setCustomerName(null);
		customerdb5.setCustomerPassword(null);
        customerdb5.setCustomerPhone(null); 
        customerdb5.setCustomerBirthday(null); 
        customerdb5.setCustomerAddress(null); 
        customerdb5.setCustomerGender(null); 
        try {
            entityManager.persist(customerdb5);
            entityManager.flush();
            fail("Test Customer Not Null Error");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println(); 
            System.out.println();   
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> 4. Test Customer Not Null Error \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(); 
            System.out.println(); 
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 7);
        }
    }
    @Test
	public void testCustomerPhoneFirstZero() {
        Customer customerdb6 = new Customer();
        String dateB1 = "20:04:1997";
        DateTimeFormatter lformatterb = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate dateB1c = LocalDate.parse(dateB1, lformatterb);
		customerdb6.setCustomerIDs("C9");
		customerdb6.setCustomerName("KKC");
		customerdb6.setCustomerPassword("123456");
        customerdb6.setCustomerPhone("9987654321"); 
        customerdb6.setCustomerBirthday(dateB1c); 
        customerdb6.setCustomerGender("Man"); 
        customerdb6.setCustomerAddress("Korat");
        try {
            entityManager.persist(customerdb6);
            entityManager.flush();
            fail("Test CustomerPhone First Zero Error");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println(); 
            System.out.println();   
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> 5.Test CustomerPhone First Zero Error \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(); 
            System.out.println(); 
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }

    @Test
    //(expected=javax.persistence.PersistenceException.class)
    public void TestUniqeCustomerIdError() {
        Customer customerdb7 = new Customer();
        String dateB1 = "20:04:1997";
        DateTimeFormatter lformatterb = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate dateB1c = LocalDate.parse(dateB1, lformatterb);
		customerdb7.setCustomerIDs("C9");
		customerdb7.setCustomerName("KNJN");
		customerdb7.setCustomerPassword("123456");
        customerdb7.setCustomerPhone("0987654321"); 
        customerdb7.setCustomerBirthday(dateB1c); 
        customerdb7.setCustomerGender("Man"); 
        customerdb7.setCustomerAddress("Korat");
        entityManager.persist(customerdb7);
		entityManager.flush();
		
        Customer customerdb8 = new Customer();
        String dateB2 = "20:04:1997";
        DateTimeFormatter lformatterb2 = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate dateB2c = LocalDate.parse(dateB2, lformatterb2);
		customerdb8.setCustomerIDs("C9");
		customerdb8.setCustomerName("KKCLO");
		customerdb8.setCustomerPassword("1234567");
        customerdb8.setCustomerPhone("0987654321"); 
        customerdb8.setCustomerAddress("Korat");
        customerdb8.setCustomerGender("Man"); 
        customerdb8.setCustomerBirthday(dateB2c); 
        try{
            entityManager.persist(customerdb8);
            entityManager.flush();
        }catch(javax.persistence.PersistenceException e) {
            System.out.println(); 
            System.out.println();   
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> 6.UniqeCustomerId \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(); 
            System.out.println(); 


        }
        

    }
}

