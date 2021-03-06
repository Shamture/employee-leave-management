package com.lb.employeeleave.controller;

import com.lb.employeeleave.entity.LeaveType;
import com.lb.employeeleave.service.LeaveTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("leave-types")
public class LeaveTypeController {

    private static final Logger LOGGER = LoggerFactory.getLogger(LeaveTypeController.class);

    private final LeaveTypeService leaveTypeService;

    public LeaveTypeController(final LeaveTypeService leaveTypeService){
        this.leaveTypeService = leaveTypeService;
    }

    @GetMapping
    public ResponseEntity<?> retrieveAllLeaveTypes(){

        LOGGER.info("Retrieve all LeaveTypes");
        return new ResponseEntity<>(leaveTypeService.getAllLeaveTypes(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retrieveLeaveType(@PathVariable long id){

        LOGGER.info("Retrieve single LeaveType");
        return new ResponseEntity<>(leaveTypeService.getLeaveTypeById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createLeaveType(@RequestBody LeaveType leaveType){

        LOGGER.info("Create LeaveType");
        return new ResponseEntity<>( leaveTypeService.createLeaveType(leaveType), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateLeaveType(@RequestBody LeaveType leaveType){

        LOGGER.info("Update LeaveType");
        return new ResponseEntity<>(leaveTypeService.updateLeaveType(leaveType), HttpStatus.OK);
    }
}
