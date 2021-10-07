/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalSessionBean;

import bll_user.ReadUserBeanLocal;
import dal.Positions;
import dal.UserJoinThree;
import dal.Users;
import java.beans.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author User
 */
public class BufBean implements Serializable {

    ReadUserBeanLocal readUserBean = lookupReadUserBeanLocal();

    public static final String PROP_SAMPLE_PROPERTY = "sampleProperty";

    private String sampleProperty;

    private PropertyChangeSupport propertySupport;

    
    
    

    public BufBean() {
        propertySupport = new PropertyChangeSupport(this);
    }

    public String getSampleProperty() {
        return sampleProperty;
    }

    public void setSampleProperty(String value) {
        String oldValue = sampleProperty;
        sampleProperty = value;
        propertySupport.firePropertyChange(PROP_SAMPLE_PROPERTY, oldValue, sampleProperty);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertySupport.removePropertyChangeListener(listener);
    }

    public List<Users> findAllusers() {//to get list of all users
        List<Users> listUsers;
        listUsers = readUserBean.findAllusers();
        return listUsers;
    }

//    public List<Users> joinUserEmployee() {
//        List<Users> listEmp;
//        listEmp = readUserBean.joinUserEmployee();
//        return listEmp;
//    }
    
    public List<UserJoinThree> userJoinThree() {//to get list of all users with employee and position
        List<UserJoinThree> listUsers;
        listUsers = readUserBean.userJoinThree();
        return listUsers;
    }

    public List<Positions> allPositions() {//to get list of all positions on jsp page
        List<Positions> posList;
        posList = readUserBean.allPositions();
        return posList;
    }

    private ReadUserBeanLocal lookupReadUserBeanLocal() {
        try {
            Context c = new InitialContext();
            return (ReadUserBeanLocal) c.lookup("java:global/TaskManagerProj/TaskManagerProj-ejb/ReadUserBean!bll_user.ReadUserBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
