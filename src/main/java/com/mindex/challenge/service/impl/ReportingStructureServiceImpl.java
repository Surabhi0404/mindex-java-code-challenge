package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.EmployeeService;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Task 1:
 * 
 * ReportingStructureServiceImpl implementation class returning JSON data
 * 
 * @author Surabhi S. Gurav
 *
 */
@Service
public class ReportingStructureServiceImpl implements ReportingStructureService{
    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureServiceImpl.class);

    @Autowired
    private EmployeeService empService;
    
    @Autowired
    private ReportingStructureService reportingStructureService;
    
    /**
     * Read method to return the ReportingStructure for an existing employeeId
     * If the employeeId does not exists; an error is thrown     * 
     * 
     * */
    @Override
	public ReportingStructure read(String id) {
		
    	LOG.debug("Reading employee ReportingStructure for employeeId: [{}]", id);
    	Employee emp = empService.read(id);
    	int numReports = empService.getNumReports(id);
    	
    	ReportingStructure reportingStructure = new ReportingStructure(emp, numReports);
		return reportingStructure;
	}   
}
