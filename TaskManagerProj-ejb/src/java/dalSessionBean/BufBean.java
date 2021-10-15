/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalSessionBean;

import bll_data.ReadTaskBeanLocal;
import bll_user.ReadUserBeanLocal;
import dal.EmpJoinTask;
import dal.Employee;
import dal.Positions;
import dal.Tasks;
import dal.UserJoinThree;
import dal.Users;
import java.beans.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author User
 */
public class BufBean implements Serializable {

    ReadTaskBeanLocal readTaskBean = lookupReadTaskBeanLocal();

    ReadUserBeanLocal readUserBean = lookupReadUserBeanLocal();

    public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";

    private String sampleProperty;

    private PropertyChangeSupport propertySupport;

    public BufBean() {
        propertySupport = new PropertyChangeSupport(this);
    }

    public String getSampleProperty() {
        return sampleProperty;
    }

    public void setSampleProperty(String value) {
        String oldValue = sampleProperty;
        sampleProperty = value;
        propertySupport.firePropertyChange(PROP_SAMPLE_PROPERTY, oldValue, sampleProperty);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

    public List<Users> findAllusers() {//to get list of all users
        List<Users> listUsers;
        listUsers = readUserBean.findAllusers();
        return listUsers;
    }

    public List<Positions> allPositions() {//to get list of all positions on jsp page
        List<Positions> posList;
        posList = readUserBean.allPositions();
        return posList;
    }

    public String positionNameByID(Integer posID) {//to get position name by its ID on jsp page
        String posName;
        posName = "<td>" + readUserBean.positionNameByID(posID) + "</td></tr>";
        return posName;
    }

    public List<Employee> allEmployee() {//to get list of all employees on jsp page
        List<Employee> empList;
        empList = readUserBean.allEmployee();
        return empList;
    }

    public List<EmpJoinTask> allIncompTasks() {//to get list of all incompleted tasks on jsp page
        List<EmpJoinTask> taskList;
        taskList = readTaskBean.allIncompTasks();
        return taskList;
    }
    
    public List<EmpJoinTask> allTasks() {//to get list of all incompleted tasks on jsp page
        List<EmpJoinTask> taskList;
        taskList = readTaskBean.allTasks();
        return taskList;
    }
    
    public List<Tasks> allTasksFromTasks() {//to get list of all IDs tasks on jsp page
        List<Tasks> taskList;
        taskList = readTaskBean.allTasksFromTasks();
        return taskList;
    }

//    public List<Users> joinUserEmployee() {
//        List<Users> listEmp;
//        listEmp = readUserBean.joinUserEmployee();
//        return listEmp;
//    }
    public List<UserJoinThree> userJoinThree() {//to get list of all users with employee and position
        List<UserJoinThree> listUsers;
        listUsers = readUserBean.userJoinThree();
        return listUsers;
    }

//    public List<String> userJoin() { to be tested
//
//        String res = "";
//
//        List<String> listRes = new ArrayList<>();
//        List<Users> listReadUsers = findAllusers();
//        List<Employee> listReadEmp = allEmployee();
//
//        for (int i = 0; i < listReadUsers.size(); i++) {
//
//            for (int j = 0; j < listReadUsers.size(); j++) {
//                if (listReadUsers.get(i).getUserId().equals(listReadEmp.get(j).getUserIdUsers().getUserId())) {
//                    res = listReadUsers.get(i).toHtmlStringTABLEpart() + listReadEmp.get(j).toHtmlStringTABLEpart() + positionNameByID(listReadEmp.get(j).getPosIdPosition().getPositionId());
//                    System.out.println(res + "*****************************************************");
//                    listRes.add(i, res);
//                    break;
//                }
//            }
//            
//        }
//
//        return listRes;
//    }

    private ReadUserBeanLocal lookupReadUserBeanLocal() {
        try {
            Context c = new InitialContext();
            return (ReadUserBeanLocal) c.lookup("java:global/TaskManagerProj/TaskManagerProj-ejb/ReadUserBean!bll_user.ReadUserBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private ReadTaskBeanLocal lookupReadTaskBeanLocal() {
        try {
            Context c = new InitialContext();
            return (ReadTaskBeanLocal) c.lookup("java:global/TaskManagerProj/TaskManagerProj-ejb/ReadTaskBean!bll_data.ReadTaskBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
