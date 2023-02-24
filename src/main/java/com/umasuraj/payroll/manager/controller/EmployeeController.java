package com.umasuraj.payroll.manager.controller;

import com.umasuraj.payroll.manager.entity.Employee;
import java.util.List;
import org.modelmapper.ModelMapper;

import com.umasuraj.payroll.manager.view.EmployeeView;
import com.umasuraj.payroll.manager.repository.EmployeeRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 *
 * @author umasuraj
 */
public class EmployeeController {

    private EmployeeRepository employeeRepository;

    private ModelMapper modelMapper;

    public EmployeeView saveEmployee(EmployeeView employeeView) {

        Employee employee = this.modelMapper
                .map(employeeView, Employee.class);

        LocalDateTime employeeAddTimestamp = LocalDateTime.now();
        employee.setAddTimestamp(employeeAddTimestamp);

        Employee savedEmployee = this.employeeRepository.saveEmployee(employee);

        EmployeeView savedEmployeeView = this.modelMapper
                .map(savedEmployee, EmployeeView.class);

        return savedEmployeeView;
    }
    // end saveEmployee() method

    
    public EmployeeView updateEmployee(EmployeeView employeeView, Long employeeId) {

        Employee employee = this.employeeRepository.findEmployeeById(employeeId);
        
        employee.setFirstName(employeeView.getFirstName());
        employee.setLastName(employeeView.getFirstName());
        employee.setAddress(employeeView.getAddress());
        employee.setMobileNo(employeeView.getMobileNo());
        employee.setBirthDate(employeeView.getBirthDate());
        employee.setAadhaar(employeeView.getAadhaar());
        employee.setPan(employeeView.getPan());
        employee.setEmail(employeeView.getEmail());
        employee.setSsn(employeeView.getSsn());
        employee.setEmployeeCode(employeeView.getEmployeeCode());
        employee.setDesignation(employeeView.getDesignation());
        employee.setJoinDate(employeeView.getJoinDate());
        employee.setResignDate(employeeView.getResignDate());
        employee.setStatus(employeeView.getStatus());

        LocalDateTime employeeUpdateTimestamp = LocalDateTime.now();
        employee.setUpdateTimestamp(employeeUpdateTimestamp);

        Employee updatedEmployee = this.employeeRepository.saveEmployee(employee);

        EmployeeView updatedEmployeeDTO = this.modelMapper
                .map(updatedEmployee, EmployeeView.class);

        return updatedEmployeeDTO;
    }
    // end updateEmployee() method

    @Override
    public EmployeeView getEmployeeById(Long employeeId) {

        Employee foundEmployee = this.employeeRepository.findById(employeeId).get();
        //		.orElseThrow(() -> new ResourceNotFoundException("USER", "ID", userId));

        EmployeeView foundEmployeeView = this.modelMapper
                .map(foundEmployee, EmployeeView.class);

        return foundEmployeeView;
    }
    // end findEmployeeById() method

    @Override
    public List<EmployeeView> getAllEmployee() {

        List<Employee> employeeList = new ArrayList<>();

        employeeList = this.employeeRepository.findAll();

        List<EmployeeView> employeeViewList = employeeList.stream()
                .map(employee -> this.modelMapper.map(employee, EmployeeView.class))
                .collect(Collectors.toList());

        return employeeViewList;
    }
    // end getAllEmployee() method

    @Override
    public void deleteEmployeeById(Long employeeId) {

        Employee foundEmployee = this.employeeRepository.findById(employeeId).get();
        //		.orElseThrow(() -> new ResourceNotFoundException("USER", "ID", userId));
        this.employeeRepository.delete(foundEmployee);
    }
    // end deleteEmployeeById() method

}
//end class EmployeeController{}
