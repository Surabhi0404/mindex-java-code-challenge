package com.mindex.challenge.data;

/**
 * Task 1:
 * 
 * ReportingStructure class to define the object returned by reportingStructure endpoint
 * 
 * @author Surabhi S. Gurav
 *
 */
public class ReportingStructure {
	private Employee employee;
	private int numberOfReports;
	
	public ReportingStructure(Employee employee, int numberOfReports) {
		super();
		this.employee = employee;
		this.numberOfReports = numberOfReports;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public int getNumberOfReports() {
		return numberOfReports;
	}

	public void setNumberOfReports(int numberOfReports) {
		this.numberOfReports = numberOfReports;
	}   

}
