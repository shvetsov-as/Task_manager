/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalSessionBean;

import dal.Users;
import java.util.Collection;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface UsersFacadeLocal {

    void create(Users users);// criteria api

    void edit(Users users);// criteria api

    void remove(Users users);// criteria api

    Users find(Object id);// criteria api
    
    List<Users> findByUserLogin(String login);//to find only one user with uniqe login or duplicate check

    List<Users> findAll();// criteria api

    List<Users> findRange(int[] range);// criteria api

    int count();// criteria api
    
    // List<Users> joinUserEmployee();
    
    
}
