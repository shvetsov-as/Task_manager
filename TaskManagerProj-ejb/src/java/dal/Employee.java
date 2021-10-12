/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "employee")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Employee.findAll", query = "SELECT e FROM Employee e")
    , @NamedQuery(name = "Employee.findByEmpId", query = "SELECT e FROM Employee e WHERE e.empId = :empId")
    , @NamedQuery(name = "Employee.findByEmpSurname", query = "SELECT e FROM Employee e WHERE e.empSurname = :empSurname")
    , @NamedQuery(name = "Employee.findByEmpName", query = "SELECT e FROM Employee e WHERE e.empName = :empName")
    , @NamedQuery(name = "Employee.findByEmpMidName", query = "SELECT e FROM Employee e WHERE e.empMidName = :empMidName")})
public class Employee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "emp_id")
    private Integer empId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "emp_surname")
    private String empSurname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "emp_name")
    private String empName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "emp_mid_name")
    private String empMidName;
    @JoinColumn(name = "pos_id_position", referencedColumnName = "position_id")
    @ManyToOne(optional = false)
    private Positions posIdPosition;
    @JoinColumn(name = "user_id_users", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private Users userIdUsers;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "empIdEmployee")
    private Collection<Tasks> tasksCollection;

    public Employee() {
    }

    public Employee(Integer empId) {
        this.empId = empId;
    }

    public Employee(Integer empId, String empSurname, String empName, String empMidName) {
        this.empId = empId;
        this.empSurname = empSurname;
        this.empName = empName;
        this.empMidName = empMidName;
    }

    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
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

    public Positions getPosIdPosition() {
        return posIdPosition;
    }

    public void setPosIdPosition(Positions posIdPosition) {
        this.posIdPosition = posIdPosition;
    }

    public Users getUserIdUsers() {
        return userIdUsers;
    }

    public void setUserIdUsers(Users userIdUsers) {
        this.userIdUsers = userIdUsers;
    }

    @XmlTransient
    public Collection<Tasks> getTasksCollection() {
        return tasksCollection;
    }

    public void setTasksCollection(Collection<Tasks> tasksCollection) {
        this.tasksCollection = tasksCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (empId != null ? empId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Employee)) {
            return false;
        }
        Employee other = (Employee) object;
        if ((this.empId == null && other.empId != null) || (this.empId != null && !this.empId.equals(other.empId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employee{" + "empId=" + empId + ", empSurname=" + empSurname + ", empName=" + empName + ", empMidName=" + empMidName + ", posIdPosition=" + posIdPosition + ", userIdUsers=" + userIdUsers + ", tasksCollection=" + tasksCollection + '}';
    }

    public String toHtmlString() {
        return "<li> dal.Employee[ empId=" + empId + " ] </li>";
    }

    public String toHtmlStringTABLE() {
        return "<tr> <td align=\"center\">" + empSurname + "</td> "
                + "<td align=\"center\">" + empName + "</td> "
                + "<td align=\"center\">" + empMidName + "</td> </tr>";
    }
    
    public String toHtmlStringTABLEsrnamNam() {
        return "<tr> <td align=\"center\">" + empSurname + " " + empName + "</td> </tr>";
    }

}
