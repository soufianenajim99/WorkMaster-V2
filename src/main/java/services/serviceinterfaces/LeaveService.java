package services.serviceinterfaces;

import entities.Leave;

import java.util.List;
import java.util.UUID;

public interface LeaveService extends GenericService<Leave>{
    List<Leave> findByEmployeeId(UUID employeeId);
}
