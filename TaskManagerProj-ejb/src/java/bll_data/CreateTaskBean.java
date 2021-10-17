/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll_data;

import dal.Task;
import dalSessionBean.TaskFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author User
 */
@Stateless
public class CreateTaskBean implements CreateTaskBeanLocal {

    @EJB
    private TaskFacadeLocal taskFacade;

    @Override
    public boolean createTask(Task task) { // add new task and check add status
        
        Integer begin = taskFacade.count();
        Integer end;

        taskFacade.create(task);
        end = taskFacade.count();

        if ((Integer.compare(begin, end)) >= 0) {// not added
            return false;
        }
        return true;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
