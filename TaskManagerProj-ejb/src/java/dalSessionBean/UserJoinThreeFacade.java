/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalSessionBean;

import dal.UserJoinThree;
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
public class UserJoinThreeFacade extends AbstractFacade<UserJoinThree> implements UserJoinThreeFacadeLocal {

    @PersistenceContext(unitName = "TaskManagerProj-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserJoinThreeFacade() {
        super(UserJoinThree.class);
    }

    @Override
    public List<UserJoinThree> userJoinThree() {//to get list of all users with employee and position
        List<UserJoinThree> listUsers;
        Query userJoinThree = em.createNamedQuery("UserJoinThree.findAll");
        listUsers = userJoinThree.getResultList();
        return listUsers;
    }
    
}
