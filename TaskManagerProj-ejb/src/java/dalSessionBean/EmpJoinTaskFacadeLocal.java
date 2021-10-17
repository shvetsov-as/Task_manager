/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalSessionBean;

import dal.EmpJoinTask;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface EmpJoinTaskFacadeLocal {

    void create(EmpJoinTask empJoinTask);

    void edit(EmpJoinTask empJoinTask);

    void remove(EmpJoinTask empJoinTask);

    EmpJoinTask find(Object id);

    List<EmpJoinTask> findAll();

    List<EmpJoinTask> findRange(int[] range);

    int count();
    
    List<EmpJoinTask> findByParameter(EmpJoinTask taskParameters, String query);//to get List of tasks by parameters
    
}
