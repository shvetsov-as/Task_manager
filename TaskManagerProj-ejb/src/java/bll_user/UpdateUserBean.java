/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll_user;

import dal.Positions;
import dal.UserJoinThree;
import dalSessionBean.PositionsFacadeLocal;
import dalSessionBean.UserJoinThreeFacadeLocal;
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
    private UserJoinThreeFacadeLocal userJoinThreeFacade;

    @EJB
    private PositionsFacadeLocal positionsFacade;

    @Override
    public boolean updatePosition(Integer positionID, String positionName) {// to update poition by id
        Positions position;
        position = positionsFacade.find(positionID);
        if (!position.getPositionId().toString().isEmpty()) {
            position.setPosition(positionName);
            positionsFacade.edit(position);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateUser(Integer userID, UserJoinThree userNewInfo) {
        UserJoinThree user;
        user = userJoinThreeFacade.find(userID);
        if (!user.getUserId().toString().isEmpty()) {
            if (userNewInfo.getUserLogin() != null && !user.getUserLogin().equals(userNewInfo.getUserLogin())) {
                user.setUserLogin(userNewInfo.getUserLogin());
            }

            if (userNewInfo.getUserPasswd() != null) {
                user.setUserPasswd(userNewInfo.getUserPasswd());
            }
            
            if (userNewInfo.getUserRole() != null) {
                user.setUserRole(userNewInfo.getUserRole());
            }
            
            if (userNewInfo.getEmpSurname() != null) {
                user.setEmpSurname(userNewInfo.getEmpSurname());
            }
            
            if (userNewInfo.getEmpName() != null) {
                user.setEmpName(userNewInfo.getEmpName());
            }
            
            if (userNewInfo.getEmpMidName() != null) {
                user.setEmpMidName(userNewInfo.getEmpMidName());
            }
            
            if (userNewInfo.getPosIdPosition() != null) {
                user.setPosIdPosition(userNewInfo.getPosIdPosition());
            }

            userJoinThreeFacade.edit(user);
            return true;
        }
        return false;
    }
}
