package com.zaurtregulov.spring.mvc_hibernate_aop.dao;

import com.zaurtregulov.spring.mvc_hibernate_aop.entity.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Employee> getAllEmployees() {

        Session session = sessionFactory.getCurrentSession();

//        // единое выражение
//        List<Employee> employees = session.createQuery("from Employee", Employee.class).getResultList();

        //то же выражение, разделив его на две части
        Query<Employee> query = session.createQuery("from Employee", Employee.class);
        List<Employee> employees = query.getResultList();

        return employees;
    }

    @Override
    public void saveEmp(Employee employee) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(employee);
    }

    @Override
    public Employee getEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        Employee employee = session.get(Employee.class, id);

        return employee;
    }

    @Override
    public void deleteEmp(int id) {
       // первый вариант
        Session session = sessionFactory.getCurrentSession();
        Employee employee = session.get(Employee.class, id);
        session.delete(employee);

//        // второй вариант
//        Session session1 = sessionFactory.getCurrentSession();
//        Query<Employee> query = session1.createQuery("delete from Employee " +
//                "where id =:employeeID");
//        query.setParameter("employeeID", id);
//        query.executeUpdate();

    }
}
