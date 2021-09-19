/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalSessionBean;

import dal.Positions;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    @Override
    public List<Positions> findPosID() {

        //List<Positions> positions = new ArrayList<>();
        Query findPosID = em.createNamedQuery("Positions.findAll");
        
        //positions = findPosID.getResultList();
        //System.out.println("");
        return findPosID.getResultList();
    }

}
