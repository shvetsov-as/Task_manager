/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalSessionBean;

import dal.Employee;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface EmployeeFacadeLocal {

    void create(Employee employee);

    void edit(Employee employee);

    void remove(Employee employee);

    Employee find(Object id);

    List<Employee> findAll();

    List<Employee> findRange(int[] range);

    Integer findEmpIDbyFullName(String surname, String name, String midname); //to get employee id by its full name

    int count();

}
