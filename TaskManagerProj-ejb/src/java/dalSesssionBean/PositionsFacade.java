/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalSesssionBean;

import dal.Positions;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author User
 */
@Stateless
public class PositionsFacade extends AbstractFacade<Positions> implements PositionsFacadeLocal {

    @PersistenceContext(unitName = "TaskManagerProj-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PositionsFacade() {
        super(Positions.class);
    }
    
}
