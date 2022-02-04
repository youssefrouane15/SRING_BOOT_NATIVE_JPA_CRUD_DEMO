package com.luv2code.springbboot.cruddemo.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springbboot.cruddemo.entity.Employee;

@Repository
public class EmployeeDAOJpaImpl implements EmployeeDAO {
	
	private EntityManager entityManager;
	
	@Autowired
	public EmployeeDAOJpaImpl(EntityManager theEntityManager) {
		
		this.entityManager = theEntityManager;
	}

	@Override
	public List<Employee> findAll() {

		// creating the query
		Query query = entityManager.createQuery("from Employee");
		
		// execute the query and getting the result list
		List<Employee> employees = query.getResultList();
		
		// returning the employees
		return employees;
	}

	@Override
	public Employee findById(int employeeId) {
		
		// get employee
		Employee employee = entityManager.find(Employee.class, employeeId);
		
		// return the employee
		return employee;
	}

	@Override
	public void save(Employee employee) {
		
		// save or update the employee
		Employee dbEmployee = entityManager.merge(employee);
		
		// update it with the db employee Id
		employee.setId(dbEmployee.getId());
	}

	@Override
	public void deleteById(int employeeId) {
		
		// Delete the object with its primary key
		Query query = entityManager.createQuery("delete from Employee where id=:employeeId");
		
		query.setParameter("employeeId", employeeId);
		
		query.executeUpdate();

	}

}
