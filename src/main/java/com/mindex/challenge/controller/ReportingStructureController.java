package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.ReportingStructureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Task 1:
 * Rest Controller to add endpoint to access reportingStructure for given EmployeeId
 * 
 * @author Surabhi S. Gurav
 *
 */
@RestController
public class ReportingStructureController {

    private static final Logger LOG = LoggerFactory.getLogger(ReportingStructureController.class);

    @Autowired
    private ReportingStructureService ReportingStructureService;
    
    /**
     *  Get endpoint which accepts a valid employeeId and returns the complete reporting structure
     *  
     * */
    @GetMapping("/reportingStructure/{id}")
    public ReportingStructure read(@PathVariable String id) {
        LOG.debug("Received reportingStructure read request for id [{}]", id);

        return ReportingStructureService.read(id);
    }
    
    
}
