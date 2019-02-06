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
                    customerdb1.setCustomerIDs("C7");
                    customerdb1.setCustomerName("KKA");
                    customerdb1.setCustomerPassword("123456");
                    customerdb1.setCustomerPhone("0987654321"); 
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
	public void testCustomerPhoneSizeLessThan() {
		Customer customerdb2 = new Customer();
		customerdb2.setCustomerIDs("C8");
		customerdb2.setCustomerName("KKB");
		customerdb2.setCustomerPassword("123456");
		customerdb2.setCustomerPhone("0987"); 
        try {
            entityManager.persist(customerdb2);
            entityManager.flush();
            fail("CustomerPhone Size Less Error");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println(); 
            System.out.println();   
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> 2 \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(); 
            System.out.println(); 
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 2);
        }
    }

    @Test
	public void testCustomerPhoneSizeLongThan() {
		Customer customerdb2 = new Customer();
		customerdb2.setCustomerIDs("C10");
		customerdb2.setCustomerName("KKB");
		customerdb2.setCustomerPassword("123456");
		customerdb2.setCustomerPhone("098765432111111111"); 
        try {
            entityManager.persist(customerdb2);
            entityManager.flush();
            fail("CustomerPhone Size Less Error");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println(); 
            System.out.println();   
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> 3 \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(); 
            System.out.println(); 
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 2);
        }
    }
    @Test
	public void testCustomerNotNull() {
		Customer customerdb3 = new Customer();
		customerdb3.setCustomerIDs(null);
		customerdb3.setCustomerName(null);
		customerdb3.setCustomerPassword(null);
		customerdb3.setCustomerPhone(null); 
        try {
            entityManager.persist(customerdb3);
            entityManager.flush();
            fail("Test Customer Not Null Error");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println(); 
            System.out.println();   
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> 4 \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(); 
            System.out.println(); 
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 4);
        }
    }
    @Test
	public void testCustomerPhoneFirstZero() {
		Customer customerdb4 = new Customer();
		customerdb4.setCustomerIDs("C9");
		customerdb4.setCustomerName("KKC");
		customerdb4.setCustomerPassword("123456");
		customerdb4.setCustomerPhone("9987654321"); 
        try {
            entityManager.persist(customerdb4);
            entityManager.flush();
            fail("Test CustomerPhone First Zero Error");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println(); 
            System.out.println();   
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> 5 \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(); 
            System.out.println(); 
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }

    @Test
    //(expected=javax.persistence.PersistenceException.class)
    public void TestUniqeCustomerIdError() {
        Customer customerdb4 = new Customer();
		customerdb4.setCustomerIDs("C9");
		customerdb4.setCustomerName("KNJN");
		customerdb4.setCustomerPassword("123456");
		customerdb4.setCustomerPhone("0987654321"); 
        entityManager.persist(customerdb4);
		entityManager.flush();
		
		Customer customerdb5 = new Customer();
		customerdb5.setCustomerIDs("C9");
		customerdb5.setCustomerName("KKCLO");
		customerdb5.setCustomerPassword("1234567");
		customerdb5.setCustomerPhone("0987654321"); 
        try{
            entityManager.persist(customerdb5);
            entityManager.flush();
        }catch(javax.persistence.PersistenceException e) {
            System.out.println(); 
            System.out.println();   
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> 6 \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(); 
            System.out.println(); 


        }
        

    }
}

