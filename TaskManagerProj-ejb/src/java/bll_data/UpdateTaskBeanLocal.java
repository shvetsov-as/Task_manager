/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll_data;

import dal.Task;
import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface UpdateTaskBeanLocal {
    boolean updateTask(Task taskNewInfo);// to update task by id
    
}
