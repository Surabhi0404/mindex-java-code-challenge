/**
 * 
 */
package com.mindex.challenge.service;

import com.mindex.challenge.data.Compensation;

/**
 * Task 2:
 * 
 * CompensationService interface
 * 
 * @author Surabhi S. Gurav
 *
 */

public interface CompensationService {
	Compensation create(Compensation compensation);
	Compensation read(String id);
}
