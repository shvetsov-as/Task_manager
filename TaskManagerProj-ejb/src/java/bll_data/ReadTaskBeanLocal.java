/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll_data;

import dal.EmpJoinTask;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface ReadTaskBeanLocal {
    
    List<EmpJoinTask> allIncompTasks();//to get list of all incompleted tasks
    List<EmpJoinTask> allCompTasks();//to get list of all completed tasks
    
}
