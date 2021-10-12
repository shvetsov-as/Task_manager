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
import javax.ejb.Stateless;

/**
 *
 * @author User
 */
@Stateless
public class CreateUserBean implements CreateUserBeanLocal {

    @EJB
    private UserJoinThreeFacadeLocal userJoinThreeFacade;

    @EJB
    private PositionsFacadeLocal positionsFacade;

    @Override
    public boolean createPosition(String positionName) {// add new position and check add status

        Integer begin = positionsFacade.count();
        Integer end;

        Positions position = new Positions();
        position.setPosition(positionName);
        positionsFacade.create(position);

        end = positionsFacade.count();

        if ((Integer.compare(begin, end)) >= 0) {// not added
            return false;
        }
        return true;
    }

    @Override
    public boolean createUserJoinThree(UserJoinThree user) {// add new user, employee, position and check add status

        Integer begin = userJoinThreeFacade.count();
        Integer end;

        userJoinThreeFacade.create(user);
        end = userJoinThreeFacade.count();

        if ((Integer.compare(begin, end)) >= 0) {// not added
            return false;
        }
        return true;
    }

}
