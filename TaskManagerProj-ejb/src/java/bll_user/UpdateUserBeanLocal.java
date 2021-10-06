/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll_user;

import dal.Positions;
import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface UpdateUserBeanLocal { // to update poition by id
    boolean updatePosition(Integer positionID, String positionName);
    
}
