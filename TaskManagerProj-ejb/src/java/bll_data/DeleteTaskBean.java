/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll_data;

import dalSessionBean.TasksFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author User
 */
@Stateless
public class DeleteTaskBean implements DeleteTaskBeanLocal {

    @EJB
    private TasksFacadeLocal tasksFacade;

    @Override
    public boolean deleteTask(Integer taskID) {// delete task by id
        if (tasksFacade.find(taskID).getTaskId().equals(taskID)) {
            tasksFacade.remove(tasksFacade.find(taskID));
            return true;
        } else {
            return false;
        }
    }

   
}
