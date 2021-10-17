/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll_data;

import dal.EmpJoinTask;
import dal.Task;
import dal.Tasks;
import dalSessionBean.EmpJoinTaskFacadeLocal;
import dalSessionBean.TaskFacadeLocal;
import dalSessionBean.TasksFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author User
 */
@Stateful
public class ReadTaskBean implements ReadTaskBeanLocal {

    @EJB
    private TaskFacadeLocal taskFacade;

    @EJB
    private TasksFacadeLocal tasksFacade;

    @EJB
    private EmpJoinTaskFacadeLocal empJoinTaskFacade;

    @Override
    public List<EmpJoinTask> allIncompTasks() {//to get list of all incompleted tasks
        List<EmpJoinTask> taskList;
        List<EmpJoinTask> taskListRes = new ArrayList<>();
        taskList = empJoinTaskFacade.findAll();
        for (EmpJoinTask e : taskList) {
            if (!e.getTaskIsCompl()) {
                taskListRes.add(e);
            }
        }
        return taskListRes;
    }

    @Override
    public List<EmpJoinTask> allCompTasks() {//to get list of all completed tasks
        List<EmpJoinTask> taskList;
        List<EmpJoinTask> taskListRes = new ArrayList<>();
        taskList = empJoinTaskFacade.findAll();
        for (EmpJoinTask e : taskList) {
            if (e.getTaskIsCompl()) {
                taskListRes.add(e);
            }
        }
        return taskListRes;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @Override
    public List<EmpJoinTask> allTasks() {//to get list of all  tasks
//        Integer i = empJoinTaskFacade.count();
//        System.out.println("***************************************************************************************i  " + i); test out
        return empJoinTaskFacade.findAll();
    }

    @Override
    public List<Tasks> allTasksFromTasks() {//to get list of all IDs tasks on jsp page
        return tasksFacade.findAll();
    }

    @Override
    public Task findTaskById(Integer taskID) {//to get single task by id
        return taskFacade.find(taskID);
    }

    @Override
    public List<EmpJoinTask> findByParameter(EmpJoinTask taskParameters) {//to get List of tasks by parameters

        

        StringBuilder sb = new StringBuilder();

        sb.append("SELECT e FROM EmpJoinTask e WHERE e.taskIsCompl = :taskIsCompl");
        
        if(taskParameters.getEmpIdEmployee() != null && taskParameters.getEmpIdEmployee() != 0){
          sb.append(" AND e.empIdEmployee = :empIdEmployee");  
        }
        
        if(taskParameters.getTaskName() != null && !taskParameters.getTaskName().isEmpty() && !taskParameters.getTaskName().equals("null")){
          sb.append(" AND e.taskName LIKE :taskName");  
        }
        
        if(taskParameters.getTaskTodo() != null && !taskParameters.getTaskTodo().isEmpty() && !taskParameters.getTaskTodo().equals("null")){
          sb.append(" AND e.taskTodo LIKE :taskTodo");  
        }
        
        if(taskParameters.getTaskNote() != null && !taskParameters.getTaskNote().isEmpty() && !taskParameters.getTaskNote().equals("null")){
          sb.append(" AND e.taskNote LIKE :taskNote");  
        }
        
        if(taskParameters.getTaskType() != null && taskParameters.getTaskType() != 0){
          sb.append(" AND e.taskType = :taskType");  
        }
        
        if (taskParameters.getTaskDateFrom() != null) {
            sb.append(" AND e.taskDateFrom >= :taskDateFrom");
        }
        
        if (taskParameters.getTaskDateTo() != null) {
            sb.append(" AND e.taskDateTo <= :taskDateTo");
        }
        
        String query = sb.toString();
        System.out.println(sb.toString());
       
        List<EmpJoinTask> result = empJoinTaskFacade.findByParameter(taskParameters, query);

        return result;
    }
}
