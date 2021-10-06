/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "user_join_three")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserJoinThree.findAll", query = "SELECT u FROM UserJoinThree u")
    , @NamedQuery(name = "UserJoinThree.findByUserId", query = "SELECT u FROM UserJoinThree u WHERE u.userId = :userId")
    , @NamedQuery(name = "UserJoinThree.findByUserRole", query = "SELECT u FROM UserJoinThree u WHERE u.userRole = :userRole")
    , @NamedQuery(name = "UserJoinThree.findByUserLogin", query = "SELECT u FROM UserJoinThree u WHERE u.userLogin = :userLogin")
    , @NamedQuery(name = "UserJoinThree.findByUserPasswd", query = "SELECT u FROM UserJoinThree u WHERE u.userPasswd = :userPasswd")
    , @NamedQuery(name = "UserJoinThree.findByUserMark", query = "SELECT u FROM UserJoinThree u WHERE u.userMark = :userMark")
    , @NamedQuery(name = "UserJoinThree.findByEmpId", query = "SELECT u FROM UserJoinThree u WHERE u.empId = :empId")
    , @NamedQuery(name = "UserJoinThree.findByUserIdUsers", query = "SELECT u FROM UserJoinThree u WHERE u.userIdUsers = :userIdUsers")
    , @NamedQuery(name = "UserJoinThree.findByEmpSurname", query = "SELECT u FROM UserJoinThree u WHERE u.empSurname = :empSurname")
    , @NamedQuery(name = "UserJoinThree.findByEmpName", query = "SELECT u FROM UserJoinThree u WHERE u.empName = :empName")
    , @NamedQuery(name = "UserJoinThree.findByEmpMidName", query = "SELECT u FROM UserJoinThree u WHERE u.empMidName = :empMidName")
    , @NamedQuery(name = "UserJoinThree.findByPosIdPosition", query = "SELECT u FROM UserJoinThree u WHERE u.posIdPosition = :posIdPosition")
    , @NamedQuery(name = "UserJoinThree.findByPositionId", query = "SELECT u FROM UserJoinThree u WHERE u.positionId = :positionId")
    , @NamedQuery(name = "UserJoinThree.findByPosition", query = "SELECT u FROM UserJoinThree u WHERE u.position = :position")})
public class UserJoinThree implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "user_role")
    private Integer userRole;
    @Size(max = 50)
    @Column(name = "user_login")
    private String userLogin;
    @Size(max = 250)
    @Column(name = "user_passwd")
    private String userPasswd;
    @Size(max = 250)
    @Column(name = "user_mark")
    private String userMark;
    @Column(name = "emp_id")
    private Integer empId;
    @Column(name = "user_id_users")
    private Integer userIdUsers;
    @Size(max = 50)
    @Column(name = "emp_surname")
    private String empSurname;
    @Size(max = 50)
    @Column(name = "emp_name")
    private String empName;
    @Size(max = 50)
    @Column(name = "emp_mid_name")
    private String empMidName;
    @Column(name = "pos_id_position")
    private Integer posIdPosition;
    @Column(name = "position_id")
    private Integer positionId;
    @Size(max = 250)
    @Column(name = "position")
    private String position;

    public UserJoinThree() {
    }
    
//    public UserJoinThree(String userLogin, String userPasswd, String userMark, Integer userRole, String empSurname, String empName, String empMidName, Integer posIdPosition) {////
//        
//        this.userLogin = userLogin;
//        this.userPasswd = userPasswd;
//        this.userMark = userMark;
//        this.userRole = userRole;
//        
//        this.empSurname = empSurname;
//        this.empName = empName;
//        this.empMidName = empMidName;
//        this.posIdPosition = posIdPosition;
//    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public String getUserLogin() {
        return userLogin;
    }

    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserPasswd() {
        return userPasswd;
    }

    public void setUserPasswd(String userPasswd) {
        this.userPasswd = userPasswd;
    }

    public String getUserMark() {
        return userMark;
    }

    public void setUserMark(String userMark) {
        this.userMark = userMark;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Integer getUserIdUsers() {
        return userIdUsers;
    }

    public void setUserIdUsers(Integer userIdUsers) {
        this.userIdUsers = userIdUsers;
    }

    public String getEmpSurname() {
        return empSurname;
    }

    public void setEmpSurname(String empSurname) {
        this.empSurname = empSurname;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpMidName() {
        return empMidName;
    }

    public void setEmpMidName(String empMidName) {
        this.empMidName = empMidName;
    }

    public Integer getPosIdPosition() {
        return posIdPosition;
    }

    public void setPosIdPosition(Integer posIdPosition) {
        this.posIdPosition = posIdPosition;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public void setPositionId(Integer positionId) {
        this.positionId = positionId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "UserJoinThree{"
                + "userId=" + userId + ", userRole="
                + userRole + ", userLogin=" + userLogin
                + ", empSurname=" + empSurname + ", empName="
                + empName + ", empMidName=" + empMidName + ", position=" + position + '}';
    }

    public String toHtmlStringTABLE() {////////////////////////////////////////////
        return "<tr> <td align=\"center\">" + userId + "</td> "
                + "<td align=\"center\">" + userLogin + "</td> "
                + "<td align=\"center\">" + Role.userRoleToString(userRole) + "</td> "
                + "<td align=\"center\">" + empSurname + "</td> "
                + "<td align=\"center\">" + empName + "</td> "
                + "<td align=\"center\">" + empMidName + "</td> "
                + "<td align=\"center\">" + position + "</td> ";
    }
    
    public String toHtmlStringTABLEtest() {////////////////////////////////////////////
        return "<tr> <td align=\"center\">" + userId + "</td> "
                + "<td align=\"center\">" + userLogin + "</td> "
                + "<td align=\"center\">" + Role.userRoleToString(userRole) + "</td> "
                + "<td align=\"center\">" + userPasswd + "</td> "
                + "<td align=\"center\">" + userMark + "</td> "
                + "<td align=\"center\">" + empId + "</td> "
                + "<td align=\"center\">" + userIdUsers + "</td> "
                + "<td align=\"center\">" + empSurname + "</td> "
                + "<td align=\"center\">" + empName + "</td> "
                + "<td align=\"center\">" + empMidName + "</td> "
                + "<td align=\"center\">" + posIdPosition + "</td> "
                + "<td align=\"center\">" + positionId + "</td> "
                + "<td align=\"center\">" + position + "</td> ";
    }

}
