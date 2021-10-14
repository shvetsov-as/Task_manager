/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll_data;

import dal.EmpJoinTask;
import dalSessionBean.EmpJoinTaskFacadeLocal;
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

        return empJoinTaskFacade.findAll();
    }
}
