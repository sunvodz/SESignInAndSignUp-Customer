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
public class PaymentTest {

	@Autowired
	private PayMentRepository payMentRepository;

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
	public void testPayMentInsertDataSuccess() {
        PayMent paymentdb = new PayMent();
        Date paydate = new Date();
        Customer c1 = customerRepository.findByCusId(1L);
        paymentdb.setTypePay("Renting");
        paymentdb.setStatusPay("paid");
        paymentdb.setDatePay(paydate);
        paymentdb.setCustomer(c1);
        try {
            entityManager.persist(paymentdb);
            entityManager.flush();
            System.out.println(); 
            System.out.println();   
            System.out.println("\n\n\n\n\n\n\n\n\n----------------->> 1.Test PayMent Insert DataSuccess \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(); 
            System.out.println(); 
        } catch(javax.validation.ConstraintViolationException e) {
            fail("Test PayMent Insert DataSuccess Error");
        }
    }

    @Test
	public void testTypePaySize() {
        PayMent paymentdb = new PayMent();
        Date paydate = new Date();
        Customer c2 = customerRepository.findByCusId(2L);
        paymentdb.setTypePay("Sellingggggg");
        paymentdb.setStatusPay("paid");
        paymentdb.setDatePay(paydate);
        paymentdb.setCustomer(c2);
        try {
            entityManager.persist(paymentdb);
            entityManager.flush();
            fail("TypePay Size Not Long and Not Less 7 Error");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println(); 
            System.out.println();   
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> 2 .TypePay Size Not Long and Not Less 7 Error \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(); 
            System.out.println(); 
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 2);
        }
    }
    @Test
	public void testPaymentNotNull() {
        PayMent paymentdb = new PayMent();
        Customer customer = new Customer();
        customer.setCustomerIDs(null);
        entityManager.persist(customer);
        paymentdb.setPmId(null);
        paymentdb.setTypePay(null);
        paymentdb.setStatusPay(null);
        paymentdb.setDatePay(null);
        paymentdb.setCustomer(customer);
        try {
            entityManager.persist(paymentdb);
            entityManager.flush();
            fail("Test Payment Not Null Error");
            
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println(); 
            System.out.println();   
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> 4.Test Payment Not Null Error \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(); 
            System.out.println(); 
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 5);
        }
    }
    @Test
	public void testPaymentFirstRSB() {
        PayMent paymentdb = new PayMent();
        Date paydate = new Date();
        Customer c3 = customerRepository.findByCusId(3L);
        paymentdb.setTypePay("Aooking");
        paymentdb.setStatusPay("paid");
        paymentdb.setDatePay(paydate);
        paymentdb.setCustomer(c3);
        try {
            entityManager.persist(paymentdb);
            entityManager.flush();
            fail("Test Payment First RSB Error");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println(); 
            System.out.println();   
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> 5.Test Payment First RSB Error \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(); 
            System.out.println(); 
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }

}

