/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll_data;

import dal.EmpJoinTask;
import dal.Task;
import dal.Tasks;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface ReadTaskBeanLocal {
    List<EmpJoinTask> allTasks();//to get list of all tasks
    List<EmpJoinTask> allIncompTasks();//to get list of all incompleted tasks
    List<EmpJoinTask> allCompTasks();//to get list of all completed tasks
    List<Tasks> allTasksFromTasks();//to get list of all IDs tasks on jsp page
    Task findTaskById(Integer taskID);//to get single task by id
    List <EmpJoinTask> findByParameter (EmpJoinTask taskParameters);//to get List of tasks by parameters
}
