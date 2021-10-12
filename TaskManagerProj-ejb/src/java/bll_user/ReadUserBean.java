/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll_user;

import dal.Employee;
import dal.Positions;
import dal.Role;
import dal.UserJoinThree;
import dal.Users;
import dalSessionBean.EmployeeFacadeLocal;
import dalSessionBean.PositionsFacadeLocal;
import dalSessionBean.UserJoinThreeFacadeLocal;
import dalSessionBean.UsersFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author User
 */
@Stateful
public class ReadUserBean implements ReadUserBeanLocal {

    @EJB
    private EmployeeFacadeLocal employeeFacade;
    
    @EJB
    private UserJoinThreeFacadeLocal userJoinThreeFacade;

    @EJB
    private PositionsFacadeLocal positionsFacade;

    @EJB
    private UsersFacadeLocal usersFacade;

    @Override
    public List<Users> findAllusers() {//criteria
        List<Users> userList;
        userList = usersFacade.findAll();
        return userList;
    }
  
    @Override
    public Users findByUserLogin(String login) {//to find only one user with uniqe login or duplicate check
        Users user = new Users();
        List<Users> userList;
        userList = usersFacade.findByUserLogin(login);
        
        if(userList.isEmpty()){// if there is no user with this login
            System.out.println("Users findByUserLogin#########ReadUserBean########List is emty UserId(9998)");
            user.setUserId(9998);
            user.setUserLogin("UNKNOWN");
            user.setUserPasswd("UNKNOWN");
            user.setUserMark("UNKNOWN");
            user.setUserRole(Role.UNKNOWN.getRoleCode());
            return user;
        }
        
        if(userList.size()>1){ // if there more than one user
            System.out.println("Users findByUserLogin#########ReadUserBean########List contains double login UserId(9999)");
            user.setUserId(9999);
            user.setUserLogin("DUPLICATE");
            user.setUserPasswd("UNKNOWN");
            user.setUserMark("UNKNOWN");
            user.setUserRole(Role.UNKNOWN.getRoleCode());
            return user;
        }
        
        for (Users u : userList){// only if there only one user found with this login
            user.setUserId(u.getUserId());
            user.setUserLogin(u.getUserLogin());
            user.setUserPasswd(u.getUserPasswd());
            user.setUserMark(u.getUserMark());
            user.setUserRole(u.getUserRole());
        }
        return user;
    }

//    @Override
//    public List<Users> joinUserEmployee() {
//        
//        return usersFacade.joinUserEmployee();
//    }

    @Override
    public List<Positions> allPositions() {//to get list of all positions on jsp page
        List<Positions> listPos;
        listPos = positionsFacade.findAll();
        return listPos;
    }
    
    @Override
    public List<Employee> allEmployee() { //to get list of all employees on jsp page
        return employeeFacade.findAll();
    }
    
    
    @Override
    public List<UserJoinThree> userJoinThree() {//to get list of all users with employee and position
        List<UserJoinThree> listUsers;   
        listUsers = userJoinThreeFacade.userJoinThree();
        return listUsers;
    }

    @Override
    public Integer findPosIDbyName(String posName) {//to get position id by position name from positions
        return positionsFacade.findPosIDbyName(posName);
    }

    @Override
    public String positionNameByID(Integer posID) {//to get position name by its ID on jsp page
        String posName;
        posName = positionsFacade.find(posID).getPosition();
        return posName;
    }

    
    
        
}
