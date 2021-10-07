/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll_user;

import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface DeleteUserBeanLocal {
    
    boolean deletePosition(Integer positionID);// delete position by id
    boolean deleteUser(Integer userID);// delete user by id
    
}
