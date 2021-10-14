/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalSessionBean;

import dal.Employee;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author User
 */
@Stateless
public class EmployeeFacade extends AbstractFacade<Employee> implements EmployeeFacadeLocal {

    @PersistenceContext(unitName = "TaskManagerProj-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmployeeFacade() {
        super(Employee.class);
    }

    @Override
    public Integer findEmpIDbyFullName(String surname, String name, String midname) {
        Employee employee;
        Query findEmpIDbyFullName = em.createNamedQuery("Employee.findByEmpFullName");
        findEmpIDbyFullName.setParameter("empSurname", surname);
        findEmpIDbyFullName.setParameter("empName", name);
        findEmpIDbyFullName.setParameter("empMidName", midname);
        employee = (Employee) findEmpIDbyFullName.getSingleResult();
        return employee.getEmpId();
    }
    
}
