/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll_user;

import dal.Positions;
import dalSessionBean.PositionsFacadeLocal;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author User
 */
@Stateless
public class CreateUserBean implements CreateUserBeanLocal {

    @EJB
    private PositionsFacadeLocal positionsFacade;

    @Override
    public boolean createPosition(String positionName) {
        
        Integer begin = positionsFacade.count();
        Integer end;
        
        Positions position = new Positions();
        position.setPosition(positionName);
        positionsFacade.create(position);
        
        end = positionsFacade.count();
        
        if( (Integer.compare(begin, end)) >= 0 ){
            return false;
        }
        return true;
    }

    
}
