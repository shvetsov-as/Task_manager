/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bll_user;

import dal.Employee;
import dal.Positions;
import dal.UserJoinThree;
import dal.Users;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface ReadUserBeanLocal {
    
    List <Users> findAllusers ();// criteria api
    
    Users findByUserLogin(String login);//to find only one user with uniqe login or duplicate check
    
//    List <Users> joinUserEmployee ();
    
    List <Positions> allPositions();//to get list of all positions on jsp page
    
    public String positionNameByID(Integer posID);//to get position name by its ID on jsp page
       
   
    public List<Employee> allEmployee(); //to get list of all employees on jsp page
    
    List<UserJoinThree> userJoinThree();//to get list of all users with employee and position
    
    Integer findPosIDbyName(String posName);//to get position id by position name from positions

    //check user by login
    //boolean userLoginMatches (String login);
        
    //get users passwd, salt, role by login
    //List<Users> getPassSaltRole (String login);

    
}
