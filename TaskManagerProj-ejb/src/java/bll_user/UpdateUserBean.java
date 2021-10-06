/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll_user;

import dal.Positions;
import dalSessionBean.PositionsFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.swing.text.Position;

/**
 *
 * @author User
 */
@Stateful
public class UpdateUserBean implements UpdateUserBeanLocal {

    @EJB
    private PositionsFacadeLocal positionsFacade;

    @Override
    public boolean updatePosition(Integer positionID, String positionName) {// to update poition by id
        Positions position = new Positions();
        position = positionsFacade.find(positionID);
        if (!position.getPositionId().toString().isEmpty()) {
            position.setPosition(positionName);
            positionsFacade.edit(position);
            return true;
        }
        return false;
    }
}
