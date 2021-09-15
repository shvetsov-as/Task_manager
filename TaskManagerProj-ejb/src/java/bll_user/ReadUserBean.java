/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll_user;

import dal.Role;
import dal.Users;
import dalSesssionBean.UsersFacadeLocal;
import java.util.ArrayList;
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
    private UsersFacadeLocal usersFacade;

    @Override
    public List<Users> findAllusers() {
        List<Users> userList = new ArrayList<>();
        userList = usersFacade.findAll();
        return userList;
    }
  
    @Override
    public Users findByUserLogin(String login) {
        Users user = new Users();
        List<Users> userList = new ArrayList<>();
        userList = usersFacade.findByUserLogin(login);
        
        if(userList.isEmpty()){
            System.out.println("Users findByUserLogin#########ReadUserBean########List is emty UserId(9998)");
            user.setUserId(9998);
            user.setUserLogin("UNKNOWN");
            user.setUserPasswd("UNKNOWN");
            user.setUserMark("UNKNOWN");
            user.setUserRole(Role.UNKNOWN.getRoleCode());
            return user;
        }
        
        if(userList.size()>1){
            System.out.println("Users findByUserLogin#########ReadUserBean########List contains double login UserId(9999)");
            user.setUserId(9999);
            user.setUserLogin("UNKNOWN");
            user.setUserPasswd("UNKNOWN");
            user.setUserMark("UNKNOWN");
            user.setUserRole(Role.UNKNOWN.getRoleCode());
            return user;
        }
        
        for (Users u : userList){
            user.setUserId(u.getUserId());
            user.setUserLogin(u.getUserLogin());
            user.setUserPasswd(u.getUserPasswd());
            user.setUserMark(u.getUserMark());
            user.setUserRole(u.getUserRole());
        }
        return user;
    }

    @Override
    public List<Users> findByRegex(String regex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

    

}
