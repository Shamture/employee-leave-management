package com.lb.employeeleave.serviceImpl;

import com.lb.employeeleave.constant.ExceptionConstants;
import com.lb.employeeleave.entity.LeaveType;
import com.lb.employeeleave.exceptions.DataNotFoundException;
import com.lb.employeeleave.repository.LeaveTypeRepository;
import com.lb.employeeleave.service.LeaveTypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeaveTypeServiceImpl implements LeaveTypeService {

    private final LeaveTypeRepository leaveTypeRepository;

    public LeaveTypeServiceImpl(final LeaveTypeRepository leaveTypeRepository){
        this.leaveTypeRepository = leaveTypeRepository;
    }

    /**
     * Get all LeaveTypess Record
     * @return List of LeaveType
     */
    @Override
    public List<LeaveType> getAllLeaveTypes() {
        return leaveTypeRepository.findAll();
    }

    /**
     * Get single LeaveType Record
     * @param id
     * @return If present LeaveType else throws Exception
     */
    @Override
    public LeaveType getLeaveTypeById(Long id) {
        return leaveTypeRepository.findById(id).orElseThrow(()-> new DataNotFoundException(ExceptionConstants.LEAVE_TYPE_RECORD_NOT_FOUND));
    }

    /**
     * Create New LeaveType
     * @param leaveType
     * @return saved LeaveType
     */
    @Override
    public LeaveType createLeaveType(LeaveType leaveType) {
        return leaveTypeRepository.save(leaveType);
    }

    /**
     * Update LeaveType
     * LeaveType must be present in database else throws Exception
     * @param leaveType
     * @return updated LeaveType
     */
    @Override
    public LeaveType updateLeaveType(LeaveType leaveType) {
        Optional<LeaveType> returnedLeaveType = leaveTypeRepository.findById(leaveType.getId());
        if(!returnedLeaveType.isPresent()){
            throw new DataNotFoundException(ExceptionConstants.LEAVE_TYPE_RECORD_NOT_FOUND);
        }
        LeaveType leaveType1 = returnedLeaveType.get();
        leaveType1.setType_name(leaveType.getType_name());
        leaveType1.setStatus(leaveType.getStatus());
        return leaveTypeRepository.save(leaveType1);
    }
}
