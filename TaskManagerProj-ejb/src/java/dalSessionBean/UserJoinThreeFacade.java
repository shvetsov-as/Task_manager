/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalSessionBean;

import dal.Employee;
import dal.Positions;
import dal.UserJoinThree;
import dal.Users;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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

        List<Users> listU;
        List<Employee> listE;
        List<Positions> listP;
        //Query userJoinThree = em.createNamedQuery("UserJoinThree.findAll");
        TypedQuery t = em.createQuery("SELECT u FROM UserJoinThree u", UserJoinThree.class);

        TypedQuery t1 = em.createQuery("SELECT u FROM Users u", Users.class);
        TypedQuery t2 = em.createQuery("SELECT e FROM Employee e", Employee.class);
        TypedQuery t3 = em.createQuery("SELECT p FROM Positions p", Positions.class);

        listU = t1.getResultList();
        listE = t2.getResultList();
        listP = t3.getResultList();

        System.out.println("JOIN FACADE");

        for (Users us : listU) {
            System.out.println(us.toString());
        }
        for (Employee e : listE) {
            System.out.println(e.toString());
        }
        for (Positions p : listP) {
            System.out.println(p.toString());
        }

        //listUsers = userJoinThree.getResultList();
        listUsers = t.getResultList();

        for (UserJoinThree u : listUsers) {
            System.out.println(u.toString());
        }
        System.out.println("-----------------------------JOIN FACADE");

        return listUsers;
    }

}
