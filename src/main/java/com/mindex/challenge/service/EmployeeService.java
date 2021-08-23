package com.mindex.challenge.service;

import com.mindex.challenge.data.Employee;

public interface EmployeeService {
    Employee create(Employee employee);
    Employee read(String id);
    Employee update(Employee employee);
    
    /**
     * Task 1:
     * 
     * getNumReports method returns the number of total reports for a particular employeeId
     * 
     * */
    public int getNumReports(String id);
}
