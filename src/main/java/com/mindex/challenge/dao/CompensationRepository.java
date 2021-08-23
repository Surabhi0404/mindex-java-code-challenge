/**
 * 
 */
package com.mindex.challenge.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.mindex.challenge.data.Compensation;
import com.mindex.challenge.data.Employee;

/**
 * @author Surabhi S. Gurav
 *
 *Task 2:
 *
 *The CompensationRepository class encapsulates storage for employee object
 */

@Repository
public interface CompensationRepository extends MongoRepository<Compensation, String>{
	//retrieves Employee from Mongo
	Compensation findByEmployee(Employee employee);
}
