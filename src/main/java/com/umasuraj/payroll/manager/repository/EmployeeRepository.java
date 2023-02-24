package com.umasuraj.payroll.manager.repository;

import com.umasuraj.payroll.manager.entity.Employee;
import java.util.List;

/**
 *
 * @author umasuraj
 */
public interface EmployeeRepository {

    public Employee saveEmployee(Employee employee);

    public Employee updateEmployee(Employee employee, Long employeeId);

    public Employee findEmployeeById(Long employeeId);

    public List<Employee> findAllEmployee();

    public void deleteEmployeeById(Long employeeId);
}
