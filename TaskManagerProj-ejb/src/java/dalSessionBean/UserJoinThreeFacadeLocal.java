/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalSessionBean;

import dal.UserJoinThree;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface UserJoinThreeFacadeLocal {

    void create(UserJoinThree userJoinThree);// criteria api

    void edit(UserJoinThree userJoinThree);// criteria api

    void remove(UserJoinThree userJoinThree);// criteria api

    UserJoinThree find(Object id);// criteria api

    List<UserJoinThree> findAll();// criteria api

    List<UserJoinThree> findRange(int[] range);// criteria api

    int count();// criteria api
    
    List<UserJoinThree> userJoinThree();//to get list of all users with employee and position
    
}
