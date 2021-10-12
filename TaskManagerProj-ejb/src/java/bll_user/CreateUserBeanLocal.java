/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll_user;

import dal.UserJoinThree;
import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface CreateUserBeanLocal {
    
    boolean createPosition(String positionName);// add new position and check add status
    boolean createUserJoinThree(UserJoinThree user);// add new user, employee, position and check add status
    
}
