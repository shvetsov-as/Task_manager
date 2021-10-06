/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalSessionBean;

import dal.Tasks;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface TasksFacadeLocal {

    void create(Tasks tasks);// criteria api

    void edit(Tasks tasks);// criteria api

    void remove(Tasks tasks);// criteria api

    Tasks find(Object id);// criteria api

    List<Tasks> findAll();// criteria api

    List<Tasks> findRange(int[] range);// criteria api

    int count();// criteria api
    
}
