package com.pushpa.creditcardvalidation;

import com.pushpa.creditcardvalidation.controller.CreditCardValidationController;
import com.pushpa.creditcardvalidation.entity.CreditCard;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CreditCardValidationApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void testAddEmployee() {
		// Create a new instance of the credit card validation controller
		CreditCardValidationController creditCardValidationController = new CreditCardValidationController();

		// Case 1: Add a new CreditCard with all required fields
//		CreditCard creditCard = new CreditCard(1,"41111111111111", 123, "05/2025", "VISA");
//		boolean result1 = creditCardValidationController.saveCreditCard(creditCard);
//		assertTrue(result1);
//
//		// Case 2: Add a new employee with missing fields
//		Employee employee2 = new Employee("", "", "janedoe@example.com", "");
//		boolean result2 = ems.addEmployee(employee2);
//		assertFalse(result2);
//
//		// Case 3: Add a new employee with a duplicate email address
//		Employee employee3 = new Employee("Mary Smith", "456 Oak St", "johndoe@example.com", "555-123-4567");
//		boolean result3 = ems.addEmployee(employee3);
//		assertFalse(result3);
//
//		// Case 4: Add a new employee with an invalid phone number format
//		Employee employee4 = new Employee("Bob Johnson", "789 Elm St", "bjohnson@example.com", "1234-567-890");
//		boolean result4 = ems.addEmployee(employee4);
//		assertFalse(result4);
	}

}
