package com.mindex.challenge.service.impl;

import com.mindex.challenge.dao.EmployeeRepository;
import com.mindex.challenge.data.Employee;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOG = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee create(Employee employee) {
        LOG.debug("Creating employee [{}]", employee);

        employee.setEmployeeId(UUID.randomUUID().toString());
        employeeRepository.insert(employee);

        return employee;
    }

    @Override
    public Employee read(String id) {
        LOG.debug("Creating employee with id [{}]", id);

        Employee employee = employeeRepository.findByEmployeeId(id);

        if (employee == null) {
            throw new RuntimeException("Invalid employeeId: " + id);
        }

        return employee;
    }

    @Override
    public Employee update(Employee employee) {
        LOG.debug("Updating employee [{}]", employee);

        return employeeRepository.save(employee);
    }
    
    /**
     * Task 1:
     * 
     * Using Depth-First-Search (DFS) to indicate distinct total reports for given employeeId
     * Using DFS helps detect any cycles in the employee hierarchy and also returns only distinct direct reporting employees
     * 
     * */
    @Override
	public int getNumReports(String id) {
    	int numOfReports = 0;
    	//HashSet will maintain a set of distinct employees which have already been counted towards total reports
    	Set<String> visited = new HashSet<String>();
    	Stack<String> stack = new Stack<>();
    	stack.push(id);
    	visited.add(id);
    	while(!stack.isEmpty()) {
    		id = stack.pop();
    		Employee emp = read(id);
    		//List to capture direct reports for each individual employee present in the hierarchy
    		List<Employee> empReportsList= emp.getDirectReports();
    		if(empReportsList != null){
    			for(Employee e: empReportsList) {
    				if(visited.contains(e.getEmployeeId())==false) {
    					visited.add(e.getEmployeeId());
    					numOfReports++;
    					stack.push(e.getEmployeeId());
    				}
    			}
    		}
    	}
    	return numOfReports;
    }
}
