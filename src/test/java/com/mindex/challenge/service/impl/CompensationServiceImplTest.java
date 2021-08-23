package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;

/**
 * Task 2:
 * CompensationServiceImpl test class
 * 2 positive and 1 negative scenario is tested for 3 existin employees
 * 
 * @author Surabhi S. Gurav
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CompensationServiceImplTest {
	private String compensationUrl;
	private String compensationIdUrl;
	
	@Autowired
	private CompensationService compensationService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	private Employee testEmp1;
    private Employee testEmp2;
    private Employee testEmp3;
        
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	@Before
	public void setup() {
		compensationUrl = "http://localhost:" + port + "/compensation";
		compensationIdUrl = "http://localhost:" + port + "/compensation/{id}";
		
        testEmp1 = employeeService.read("c0c2293d-16bd-4603-8e08-638a9d18b22c");
        testEmp2 = employeeService.read("03aa1462-ffa9-4978-901b-7c001562cf6f");
        testEmp3 = employeeService.read("16a596ae-edd3-4847-99fe-c4518e82c86f");
	}
	
	@Test
    public void testCreateRead1() {
        Compensation compensationTest = null;
		try {
			compensationTest = new Compensation(testEmp1, (double)2000.00, df.parse("2021-08-01"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		/**
		 * Testing the /compensation POST endpoint
		 * Creating payload for an existing employee -testEmp1 and checking if the responseBody employeeId, salary and effectiveDate are same as the one posted in payload
		 * 
		 * */
        // Create checks
        ResponseEntity<Compensation> responseCreateCompensation = restTemplate.postForEntity(compensationUrl, compensationTest, Compensation.class);
        assertEquals(HttpStatus.OK, responseCreateCompensation.getStatusCode());
        Compensation createdCompensation = (Compensation)responseCreateCompensation.getBody();
        //Testing if JSON response is not null
        assertNotNull(createdCompensation);
        assertEquals(createdCompensation.getEmployee().getEmployeeId(), compensationTest.getEmployee().getEmployeeId());
        assertEquals("Incorrect Salary", (double)createdCompensation.getSalary(), (double)compensationTest.getSalary(), 0);
        try {
			assertEquals(df.parse(createdCompensation.getEffectiveDate().toString()), df.parse(compensationTest.getEffectiveDate().toString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

        /**
		 * Testing the /compensation/{id} GET endpoint
		 * Asserting if the employeeId, salary and effectiveDate from responseBody is same as the Compensation object for Employee testEmp1
		 * 
		 * */
        // Read checks
        ResponseEntity<Compensation> responseReadCompensation = restTemplate.getForEntity(compensationIdUrl, Compensation.class, testEmp1.getEmployeeId());
        assertEquals(HttpStatus.OK, responseReadCompensation.getStatusCode());
        Compensation readCompensation = (Compensation)responseReadCompensation.getBody();
        assertNotNull(readCompensation);
        assertEquals(readCompensation.getEmployee().getEmployeeId(), compensationTest.getEmployee().getEmployeeId());
        assertEquals("Incorrect Salary", (double)readCompensation.getSalary(), (double)compensationTest.getSalary(), 0);
        try {
			assertEquals(df.parse(readCompensation.getEffectiveDate().toString()), df.parse(compensationTest.getEffectiveDate().toString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}
     }
	
	@Test
    public void testCreateRead2() {
        Compensation compensationTest = null;
		try {
			compensationTest = new Compensation(testEmp2, (double)5000.00, df.parse("2021-09-01"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
        // Create checks
        ResponseEntity<Compensation> responseCreateCompensation = restTemplate.postForEntity(compensationUrl, compensationTest, Compensation.class);
        assertEquals(HttpStatus.OK, responseCreateCompensation.getStatusCode());
        Compensation createdCompensation = (Compensation)responseCreateCompensation.getBody();
        //Testing if JSON response is not null
        assertNotNull(createdCompensation);
        assertEquals(createdCompensation.getEmployee().getEmployeeId(), compensationTest.getEmployee().getEmployeeId());
        assertEquals("Incorrect Salary", (double)createdCompensation.getSalary(), (double)compensationTest.getSalary(), 0);
        try {
			assertEquals(df.parse(createdCompensation.getEffectiveDate().toString()), df.parse(compensationTest.getEffectiveDate().toString()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Read checks
        ResponseEntity<Compensation> responseReadCompensation = restTemplate.getForEntity(compensationIdUrl, Compensation.class, testEmp2.getEmployeeId());
        assertEquals(HttpStatus.OK, responseReadCompensation.getStatusCode());
        Compensation readCompensation = (Compensation)responseReadCompensation.getBody();
        assertNotNull(readCompensation);
        assertEquals(readCompensation.getEmployee().getEmployeeId(), compensationTest.getEmployee().getEmployeeId());
        assertEquals("Incorrect Salary", (double)readCompensation.getSalary(), (double)compensationTest.getSalary(), 0);
        try {
			assertEquals(df.parse(readCompensation.getEffectiveDate().toString()), df.parse(compensationTest.getEffectiveDate().toString()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
	
	
	/**
	 * Testing responseBody for GET endpoint /compensation/{id} for a valid employeeId which has no existing compensatio
	 * Response should return 500 Status Code
	 * 
	 * */
	@Test
	public void negativeTestRead() throws Exception{
		 // Read checks
        ResponseEntity<Compensation> responseReadCompensation = restTemplate.getForEntity(compensationIdUrl, Compensation.class, testEmp3.getEmployeeId());
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseReadCompensation.getStatusCode());
	}
}
