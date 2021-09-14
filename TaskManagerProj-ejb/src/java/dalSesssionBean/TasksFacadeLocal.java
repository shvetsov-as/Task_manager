/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalSesssionBean;

import dal.Tasks;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface TasksFacadeLocal {

    void create(Tasks tasks);

    void edit(Tasks tasks);

    void remove(Tasks tasks);

    Tasks find(Object id);

    List<Tasks> findAll();

    List<Tasks> findRange(int[] range);

    int count();
    
}
