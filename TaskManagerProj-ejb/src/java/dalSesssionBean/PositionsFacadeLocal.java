/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalSesssionBean;

import dal.Positions;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author User
 */
@Local
public interface PositionsFacadeLocal {

    void create(Positions positions);

    void edit(Positions positions);

    void remove(Positions positions);

    Positions find(Object id);

    List<Positions> findAll();

    List<Positions> findRange(int[] range);

    int count();
    
}
