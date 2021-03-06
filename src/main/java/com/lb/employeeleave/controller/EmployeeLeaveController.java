package com.lb.employeeleave.controller;

import com.lb.employeeleave.entity.EmployeeLeave;
import com.lb.employeeleave.service.EmployeeLeaveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employee-leaves")
public class EmployeeLeaveController {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeLeaveController.class);

    private final EmployeeLeaveService employeeLeaveService;

    public EmployeeLeaveController(final EmployeeLeaveService employeeLeaveService) {
        this.employeeLeaveService = employeeLeaveService;
    }

    /**
     * Retrieve all Employees
     * Http Get Method must be specified.
     * Url must be set on - server-url/base-path/employee-leaves
     * The data is returned in JSON format
     *
     * @param pageable
     * @return List of EmployeeLeave in JSON format
     */
    @GetMapping
    public ResponseEntity<?> retrieveAllEmployeeLeaves(@PageableDefault(page = 0, size = 10, sort = "createdDateTime", direction = Sort.Direction.DESC) Pageable pageable){

        LOGGER.info("Retrieve all EmployeeLeaves");
        return new ResponseEntity<>(employeeLeaveService.getAllEmployeeLeaves(pageable), HttpStatus.OK);
    }

    /**
     * Retrieve single EmployeeLeave
     * Http Get Method must be specified
     * Url must be set on - server-url/base-path/employee-leave/{id}
     *
     * @param id of EmployeeLeave that we want to retrieve
     * @return Single Employee in JSON format
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveEmployeeLeave(@PathVariable long id){

        LOGGER.info("Retrieve single EmployeeLeave");
        return new ResponseEntity<>(employeeLeaveService.getEmployeeLeaveById(id), HttpStatus.OK);
    }

    /**
     * Create EmployeeLeave
     * Http Post method must be specified
     * Url must be set on - server-url/base-path/employee-leave
     * The data is returned in JSON format
     *
     * @param employeeLeave json data specifying EmployeeLeave request to add
     * @return Created EmployeeLeave in JSON format
     */
    @PostMapping
    public ResponseEntity<?> createEmployeeLeave(@RequestBody EmployeeLeave employeeLeave){

        LOGGER.info("Create EmployeeLeave Request");
        return new ResponseEntity<>(employeeLeaveService.createEmployeeLeave(employeeLeave), HttpStatus.OK);
    }

    /**
     * Update EmployeeLeave
     * Http put method must be specified
     * Url must be set on - server-url/base-path/employee-leave/{id}
     *
     * @param employeeLeave JSON data specifying EmployeeLeave to update
     * @return Updated EmployeeLeave in JSON format
     */
    @PutMapping
    public ResponseEntity<?> updateEmployeeLeave(@RequestBody EmployeeLeave employeeLeave){

        LOGGER.info("Returning updated EmployeeLeave");
        return new ResponseEntity<>(employeeLeaveService.updateEmployeeLeave(employeeLeave), HttpStatus.OK);
    }

    /**
     * Approve EmployeeLeave
     * Http Put method must be specified
     * Url must be set on - server-url/base-path/approve-employee-leave
     *
     * @param employeeLeave JSON data specifying EmployeeLeave to approve
     * @return Approved EmployeeLeave in JSON format
     */
    @PutMapping("/approve-employee-leave")
    public ResponseEntity<?> approveEmployeeLeave(@RequestBody EmployeeLeave employeeLeave){

        LOGGER.info("Approve EmployeeLeave");
        return new ResponseEntity<>(employeeLeaveService.approveEmployeeLeave(employeeLeave), HttpStatus.OK);
    }

    /**
     * Delete Pending EmployeeLeave Request
     * Http Put method must be specified
     * Url must be set on - server-url/base-path/delete-pending-request/{id}
     *
     * @param id specifying EmployeeLeave to delete
     * @return Boolean status of EmployeeLeave deletion
     */
    @DeleteMapping("/delete-pending-request/{id}")
    public ResponseEntity<?> deletePendingLeaveRequest(@PathVariable long id){

        LOGGER.info("Delete Pending EmployeeLeave Request");
        return new ResponseEntity<>(employeeLeaveService.deletePendingEmployeeLeave(id), HttpStatus.OK);
    }
}
