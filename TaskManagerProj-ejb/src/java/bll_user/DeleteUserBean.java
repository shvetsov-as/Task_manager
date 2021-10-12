/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll_user;

import dalSessionBean.PositionsFacadeLocal;
import dalSessionBean.UsersFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author User
 */
@Stateless
public class DeleteUserBean implements DeleteUserBeanLocal {

    @EJB
    private UsersFacadeLocal usersFacade;

    @EJB
    private PositionsFacadeLocal positionsFacade;

    @Override
    public boolean deletePosition(Integer positionID) { // delete position by id
        if (positionsFacade.find(positionID).getPositionId().equals(positionID)) {
            positionsFacade.remove(positionsFacade.find(positionID));
            return true;
        } else {
            return false;
        }
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public boolean deleteUser(Integer userID) {// delete user by id
        if (usersFacade.find(userID).getUserId().equals(userID)) {
            usersFacade.remove(usersFacade.find(userID));
            return true;
        } else {
            return false;
        }
    }
}
