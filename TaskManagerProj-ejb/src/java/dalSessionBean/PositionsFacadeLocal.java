/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalSessionBean;

import dal.Positions;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface PositionsFacadeLocal {

    void create(Positions positions);// criteria api

    void edit(Positions positions);// criteria api

    void remove(Positions positions);// criteria api

    Positions find(Object id);// criteria api

    List<Positions> findAll();// criteria api
    
    //List<Positions> findPosID();

    List<Positions> findRange(int[] range);// criteria api

    int count();
    
    public Integer findPosIDbyName(String posName);//to get position id by position name from positions
    
}
