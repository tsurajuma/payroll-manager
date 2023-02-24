package com.umasuraj.payroll.manager.service;

import com.umasuraj.payroll.manager.entity.Employee;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/*----------------------------------------------------------------------------*/
/**
 *
 * @author umasuraj
 */
/*----------------------------------------------------------------------------*/
public class EmployeeDAO {

    private final SessionFactory sessionFactory;

    /*----------------------------------------------------------------------------*/
    // Constructor
    public EmployeeDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    // end Constructor()
    /*----------------------------------------------------------------------------*/
    // method to add employee
    public Employee saveEmployee(Employee employee) {

        Transaction txn;
        Session session = this.sessionFactory.openSession();
        txn = session.getTransaction();

        try {

            txn.begin();
            session.saveOrUpdate(employee);
            txn.commit();

            

        } catch (HibernateException e) {
            if (txn != null) {
                txn.rollback();
            }
            e.printStackTrace();

        } finally {
            if (txn == null) {
                session.close();
            }
        }

        return employee;
    }

    // end method addEmployee()
    /*----------------------------------------------------------------------------*/
    // method to update employee
    public boolean updateEmployee(Long employeeId, String name, String address,
            Long mobileNo, LocalDate birthDate, Long aadhaar, String pan,
            String email, String ssn, Long employeeCode, String designation,
            LocalDate joinDate, LocalDate resignDate, String status,
            LocalDateTime updateTimestamp, Long updateByUserId) {

        boolean flag = false;
        Transaction txn = null;
        Session session = this.sessionFactory.openSession();
        txn = session.getTransaction();
        try {

            txn.begin();

            Employee employee = (Employee) session.get(Employee.class, employeeId);

            employee.setName(name);
            employee.setAddress(address);
            employee.setMobileNo(mobileNo);
            employee.setBirthDate(birthDate);
            employee.setEmployeeCode(employeeCode);
            employee.setAadhaar(aadhaar);
            employee.setPan(pan);
            employee.setSsn(ssn);
            employee.setEmail(email);
            employee.setDesignation(designation);
            employee.setJoinDate(joinDate);
            employee.setResignDate(resignDate);
            employee.setStatus(status);
            employee.setUpdateTimestamp(updateTimestamp);
            employee.setUpdateByUserId(updateByUserId);

            session.update(employee);
            txn.commit();

            flag = true;

        } catch (HibernateException e) {
            if (txn != null) {
                txn.rollback();
                flag = false;
            }
            e.printStackTrace();
        } finally {
            if (txn == null) {
                session.close();
            }
        }

        return flag;
    }

    // end method updateEmployee()
    /*----------------------------------------------------------------------------*/
    // method to update employee
    public boolean updateEmployee(Long employeeId, Long salaryId) {

        boolean flag = false;
        Transaction txn = null;
        Session session = this.sessionFactory.openSession();
        txn = session.getTransaction();
        try {

            txn.begin();
            Employee employee = (Employee) session.get(Employee.class, employeeId);

            employee.setSalaryId(salaryId);

            session.update(employee);
            txn.commit();

            flag = true;

        } catch (HibernateException e) {
            if (txn != null) {
                txn.rollback();
                flag = false;
            }
            e.printStackTrace();
        } finally {
            if (txn == null) {
                session.close();
            }
        }

        return flag;
    }

    // end method updateEmployee()
    /*----------------------------------------------------------------------------*/
    // method to get employee by id
    public Employee getEmployeeById(Long employeeId) {

        Employee employee = null;
        Transaction txn = null;
        Session session = this.sessionFactory.openSession();
        txn = session.getTransaction();
        try {

            txn.begin();
            String prepareStatement = "from Employee where id = :id";
            Query query = session.createQuery(prepareStatement);
            query.setParameter("id", employeeId);
            employee = (Employee) query.uniqueResult();
            txn.commit();

        } catch (HibernateException e) {
            if (txn != null) {
                txn.rollback();
            }
            e.printStackTrace();

        } finally {
            if (txn == null) {
                session.close();
            }
        }

        return employee;
    }

    // end method getEmployeeById()
    /*----------------------------------------------------------------------------*/
    // method to get employee by salaryId
    public Employee getEmployeeBySalaryId(Long salaryId) {

        Employee employee = null;
        Transaction txn = null;
        Session session = this.sessionFactory.openSession();
        txn = session.getTransaction();
        try {

            txn.begin();
            String prepareStatement = "from Employee where salaryId = :id";
            Query query = session.createQuery(prepareStatement);
            query.setParameter("id", salaryId);
            employee = (Employee) query.uniqueResult();
            txn.commit();

        } catch (HibernateException e) {
            if (txn != null) {
                txn.rollback();
            }
            e.printStackTrace();

        } finally {
            if (txn == null) {
                session.close();
            }
        }

        return employee;
    }

    // end method getEmployeeBySalaryId()
    /*----------------------------------------------------------------------------*/
    public List<Employee> getEmployeeList() {

        List<Employee> employeeList = null;
        Transaction txn = null;
        Session session = this.sessionFactory.openSession();
        txn = session.getTransaction();

        try {

            txn.begin();
            String prepareStatement = "from Employee";
            Query query = session.createQuery(prepareStatement);
            employeeList = query.list();
            txn.commit();

        } catch (HibernateException e) {
            if (txn != null) {
                txn.rollback();
            }
            e.printStackTrace();

        } finally {
            if (txn == null) {
                session.close();
            }
        }

        return employeeList;

    }
    // end method getEmployeeList()
    /*----------------------------------------------------------------------------*/

 /*----------------------------------------------------------------------------*/
}
// end class EmployeeDAO
