/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalSessionBean;

import dal.Users;
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
public class UsersFacade extends AbstractFacade<Users> implements UsersFacadeLocal {

    @PersistenceContext(unitName = "TaskManagerProj-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsersFacade() {
        super(Users.class);
    }
    
    @Override
    public List<Users> findByUserLogin(String login) {
        List <Users> users = new ArrayList<>();
        Query findByUserLogin = em.createNamedQuery("Users.findByUserLogin");
        findByUserLogin.setParameter("userLogin", login);
        users = findByUserLogin.getResultList();
        return users;
    }

    @Override
    public List<Users> joinUserEmployee() {
        List <Users> listEmployee = new ArrayList<>();
        Query joinUserEmployee = em.createNamedQuery("Users.joinUserEmployee");
        
        listEmployee = joinUserEmployee.getResultList();
        return listEmployee;
    }
}
