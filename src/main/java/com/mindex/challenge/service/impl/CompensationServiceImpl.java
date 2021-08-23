package com.mindex.challenge.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindex.challenge.dao.CompensationRepository;
import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.CompensationService;
import com.mindex.challenge.service.EmployeeService;

/**
 * Task 2:
 * 
 * Compensation endpoints to create and read from employeeId
 * @author Surabhi S. Gurav
 *
 */
@Service
public class CompensationServiceImpl implements CompensationService{
	private static final Logger LOG = LoggerFactory.getLogger(CompensationServiceImpl.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private CompensationRepository compensationRepository;

	/**
	 * Create method creates a compensation for an existing employee.
	 * The employee details i.e employeeId are passed from the payload i.e requestBody along with salary and effectiveDate
	 * The new Compensation object is then inserted in the database are persisted
	 * If the given employeeId in payload does not exist; an exception is thrown 
	 * */
	@Override
	public Compensation create(Compensation compensation) {
		LOG.debug("Creating compensation for Employee[{}]", compensation);
		Employee employee = employeeService.read(compensation.getEmployee().getEmployeeId());
		compensation.setEmployee(employee);
		compensationRepository.insert(compensation);
				
		return compensation;
	}

	/**
	 * Read method reads an existing compensation for an employee by reading the employeeId
	 * The employeeId is passed as path param
	 * If a compensation exists for the given employeeId, then the Compensation object is returned in ResponseBody else an Error message is displayed
	 * If the employeeId does not exist; an exception is thrown
	 * 
	 * */
	@Override
	public Compensation read(String id) {
		LOG.debug("Reading compensation for employeeId [{}]",id);
		Employee employee = employeeService.read(id);
		Compensation compensation = compensationRepository.findByEmployee(employee);
		if(compensation == null)
            throw new RuntimeException("No compensation exists for employeeId: " + id);		
		return compensation;
	}
	
	
	
}
