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
@Table(name = "positions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Positions.findAll", query = "SELECT p FROM Positions p")
    , @NamedQuery(name = "Positions.findByPositionId", query = "SELECT p FROM Positions p WHERE p.positionId = :positionId")
    , @NamedQuery(name = "Positions.findByPosition", query = "SELECT p FROM Positions p WHERE p.position = :position")})
public class Positions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "position_id")
    private Integer positionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "position")
    private String position;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "posIdPosition")
    private Collection<Employee> employeeCollection;

    public Positions() {
    }

    public Positions(Integer positionId) {
        this.positionId = positionId;
    }

    public Positions(Integer positionId, String position) {
        this.positionId = positionId;
        this.position = position;
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

    @XmlTransient
    public Collection<Employee> getEmployeeCollection() {
        return employeeCollection;
    }

    public void setEmployeeCollection(Collection<Employee> employeeCollection) {
        this.employeeCollection = employeeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (positionId != null ? positionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Positions)) {
            return false;
        }
        Positions other = (Positions) object;
        if ((this.positionId == null && other.positionId != null) || (this.positionId != null && !this.positionId.equals(other.positionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Positions{" + "positionId=" + positionId + ", position=" + position + ", employeeCollection=" + employeeCollection + '}';
    }

    
    
    
    public String toHtmlString() {
        return "<li> КОД ДОЛЖНОСТИ [ " + positionId + " ] " + "НАИМЕНОВАНИЕ [ " + position + " ] </li>";
    }
    
    public String toHtmlStringTABLE() {////////////////////////////////////////////
        return "<tr> <td align=\"center\">" + positionId + "</td> "
                + "<td align=\"center\">" + position + "</td> </tr>";
    }
    
    public String toHtmlStringBUTTON() {////////////////////////////////////////////
        return "<option>" + positionId + "</option>";
    }
    
    public String toHtmlStringBUTTONname() {////////////////////////////////////////////
        return "<option>" + position + "</option>";
    }
}
