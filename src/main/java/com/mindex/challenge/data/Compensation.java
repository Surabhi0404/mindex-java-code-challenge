package com.mindex.challenge.data;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author Surabhi S. Gurav
 *
 *Task 2:
 *
 *Compensation class to define the structure of compensation object
 */
public class Compensation {
	@DBRef
	private Employee employee;
	private double salary;
	//effectiveDate is assumed to Date type in yyyy-MM-dd format to maintain date hierarchy of data which most of the times can be useful while data analysis 
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date effectiveDate;	

	public Compensation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Compensation(Employee employee, double salary, Date effectiveDate) {
		super();
		this.employee = employee;
		this.salary = salary;
		this.effectiveDate = effectiveDate;
	}

	public Employee getEmployee() {
		return employee;
	}


	public void setEmployee(Employee employee) {
		this.employee = employee;
	}


	public double getSalary() {
		return salary;
	}
	

	public void setSalary(double salary) {
		this.salary = salary;
	}


	public Date getEffectiveDate() {
		return effectiveDate;
	}


	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}
	
	
}
