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

        
        System.out.println("*****************************************************task = taskFacade.find = " + task.toString());
        System.out.println("*****************************************************task = taskNewInfo = " + taskNewInfo.toString());
        
        if (!task.getTaskId().toString().isEmpty()) {
            taskFacade.edit(taskNewInfo);
            

            return true;
        }
        return false;
    }
    
}
    
// Task task;
//        task = taskFacade.find(taskNewInfo.getTaskId());
//        if (!task.getTaskId().toString().isEmpty()) {
//            
//            if (taskNewInfo.getTaskName() != null) {
//                task.setTaskName(taskNewInfo.getTaskName());
//            }
//
//            if (taskNewInfo.getEmpIdEmployee() != null) {
//                task.setEmpIdEmployee(taskNewInfo.getEmpIdEmployee());
//            }
//
//            if (taskNewInfo.getTaskIsCompl() != null) {
//                task.setTaskIsCompl(taskNewInfo.getTaskIsCompl());
//            }
//
//            if (taskNewInfo.getTaskDateFrom() != null) {
//                task.setTaskDateFrom(taskNewInfo.getTaskDateFrom());
//            }
//
//            if (taskNewInfo.getTaskDateTo() != null) {
//                task.setTaskDateTo(taskNewInfo.getTaskDateTo());
//            }
//
//            if (taskNewInfo.getTaskNote() != null) {
//                task.setTaskNote(taskNewInfo.getTaskNote());
//            }
//
//            if (taskNewInfo.getTaskTodo() != null) {
//                task.setTaskTodo(taskNewInfo.getTaskTodo());
//            }
//            
//            if (taskNewInfo.getTaskType() != null) {
//                task.setTaskType(taskNewInfo.getTaskType());
//            }
//
//            taskFacade.edit(task);
//            return true;
//        }
//        return false;
//    }


