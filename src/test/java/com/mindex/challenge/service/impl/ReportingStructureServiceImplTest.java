package com.mindex.challenge.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;

/**
 * 
 * Task 1:
 * 
 * Test class for ReportingStructureServiceImpl to validate the JSON response with the expected result
 * 
 * @author Surabhi S. Gurav
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReportingStructureServiceImplTest {
    private String reportingStructureUrl;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private ReportingStructureService reportingStructureService;
       
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private Employee testEmp1;
    private Employee testEmp2;

    @Before
    public void setup() {
        reportingStructureUrl = "http://localhost:" + port + "/reportingStructure/{id}";
        testEmp1 = employeeService.read("16a596ae-edd3-4847-99fe-c4518e82c86f");
        testEmp2 = employeeService.read("03aa1462-ffa9-4978-901b-7c001562cf6f");        
    }
    
    /**
     * Test 1: 
     * Checks for total reports for employee: John Lennon (EmployeeId = 16a596ae-edd3-4847-99fe-c4518e82c86f)
     * John Lennon has 2 direct reports out of which reporting employee Ringo Starr (EmployeeId = 03aa1462-ffa9-4978-901b-7c001562cf6f)
     * has 2 direct reports.
     *  
     *  Thus, employee John Lennon should have total 4 reports.
     * 
     * 
     * */
    @Test
    public void testReadReportingStructure1() {
        ReportingStructure testReportingStructure = new ReportingStructure(testEmp1, 4);
        // Read checks
        ResponseEntity readReportingStructureResponse = restTemplate.getForEntity(reportingStructureUrl, ReportingStructure.class, testEmp1.getEmployeeId());
        //Testing HTTP 200 response from reportingStructure endpoint
        assertEquals(HttpStatus.OK, readReportingStructureResponse.getStatusCode());
        ReportingStructure readReportingStructure = (ReportingStructure)readReportingStructureResponse.getBody();
        //Testing if JSON response is not null
        assertNotNull(readReportingStructure);
        //Testing received numberOfReports to expected numberOfReports = 4 for John Lennon
        assertEquals(readReportingStructure.getNumberOfReports(), testReportingStructure.getNumberOfReports());
   }
    
    /**
     * Test 2: 
     * Checks for total reports for employee: Ringo Starr (EmployeeId = 03aa1462-ffa9-4978-901b-7c001562cf6f)
     * Employee Ringo Starr has 2 direct reports; none of which have other direct reports
     *  
     *  Thus, employee Ringo Starr should have total 2 reports.
     * 
     * 
     * */
    @Test
    public void testReadReportingStructure2() {
        ReportingStructure testReportingStructure = new ReportingStructure(testEmp2, 2);
        // Read checks
        ResponseEntity readReportingStructureResponse = restTemplate.getForEntity(reportingStructureUrl, ReportingStructure.class, testEmp2.getEmployeeId());
        //Testing HTTP 200 response from reportingStructure endpoint
        assertEquals(HttpStatus.OK, readReportingStructureResponse.getStatusCode());
        ReportingStructure readReportingStructure = (ReportingStructure)readReportingStructureResponse.getBody();
        //Testing if JSON response is not null
        assertNotNull(readReportingStructure);
        //Testing received numberOfReports to expected numberOfReports = 2 for Ringo Starr
        assertEquals(readReportingStructure.getNumberOfReports(), testReportingStructure.getNumberOfReports());
   }
}
