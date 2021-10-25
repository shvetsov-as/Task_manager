/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll_data;

import dal.Task;
import dalSessionBean.TaskFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author User
 */
@Stateful
public class UpdateTaskBean implements UpdateTaskBeanLocal {

    @EJB
    private TaskFacadeLocal taskFacade;

    @Override
    public boolean updateTask(Task taskNewInfo) {// to update task by id
        Task task;
        task = taskFacade.find(taskNewInfo.getTaskId());

        if (!task.getTaskId().toString().isEmpty()) {
            taskFacade.edit(taskNewInfo);

            return true;
        }
        return false;
    }
}
