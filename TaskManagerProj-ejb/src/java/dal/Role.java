package dal;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author User
 */
public enum Role {
    ADMIN(1),
    MANAGER(2),
    USER(3),
    UNKNOWN(4);
    
    private Integer roleCode;
    
    Role(Integer roleCode){
        this.roleCode = roleCode;
    }

    public Integer getRoleCode() {
        return roleCode;
    }

    @Override
    public String toString() {
        return "Role{" + "roleCode=" + roleCode + '}';
    }
    
    
}
