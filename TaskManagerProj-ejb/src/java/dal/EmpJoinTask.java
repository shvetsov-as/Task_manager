/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "emp_join_task")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EmpJoinTask.findAll", query = "SELECT e FROM EmpJoinTask e")
    , @NamedQuery(name = "EmpJoinTask.findByEmpId", query = "SELECT e FROM EmpJoinTask e WHERE e.empId = :empId")
    , @NamedQuery(name = "EmpJoinTask.findByEmpSurname", query = "SELECT e FROM EmpJoinTask e WHERE e.empSurname = :empSurname")
    , @NamedQuery(name = "EmpJoinTask.findByEmpName", query = "SELECT e FROM EmpJoinTask e WHERE e.empName = :empName")
    , @NamedQuery(name = "EmpJoinTask.findByEmpMidName", query = "SELECT e FROM EmpJoinTask e WHERE e.empMidName = :empMidName")
    , @NamedQuery(name = "EmpJoinTask.findByPosIdPosition", query = "SELECT e FROM EmpJoinTask e WHERE e.posIdPosition = :posIdPosition")
    , @NamedQuery(name = "EmpJoinTask.findByTaskId", query = "SELECT e FROM EmpJoinTask e WHERE e.taskId = :taskId")
    , @NamedQuery(name = "EmpJoinTask.findByEmpIdEmployee", query = "SELECT e FROM EmpJoinTask e WHERE e.empIdEmployee = :empIdEmployee")
    , @NamedQuery(name = "EmpJoinTask.findByTaskType", query = "SELECT e FROM EmpJoinTask e WHERE e.taskType = :taskType")
    , @NamedQuery(name = "EmpJoinTask.findByTaskName", query = "SELECT e FROM EmpJoinTask e WHERE e.taskName = :taskName")
    , @NamedQuery(name = "EmpJoinTask.findByTaskDateFrom", query = "SELECT e FROM EmpJoinTask e WHERE e.taskDateFrom = :taskDateFrom")
    , @NamedQuery(name = "EmpJoinTask.findByTaskDateTo", query = "SELECT e FROM EmpJoinTask e WHERE e.taskDateTo = :taskDateTo")
    , @NamedQuery(name = "EmpJoinTask.findByTaskTodo", query = "SELECT e FROM EmpJoinTask e WHERE e.taskTodo = :taskTodo")
    , @NamedQuery(name = "EmpJoinTask.findByTaskNote", query = "SELECT e FROM EmpJoinTask e WHERE e.taskNote = :taskNote")
    , @NamedQuery(name = "EmpJoinTask.findByTaskIsCompl", query = "SELECT e FROM EmpJoinTask e WHERE e.taskIsCompl = :taskIsCompl")})
public class EmpJoinTask implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "task_id")
    private Integer taskId;
    
    @Column(name = "emp_id")
    private Integer empId;
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
    
    
    @Column(name = "emp_id_employee")
    private Integer empIdEmployee;
    @Column(name = "task_type")
    private Integer taskType;
    @Size(max = 50)
    @Column(name = "task_name")
    private String taskName;
    @Column(name = "task_date_from")
    @Temporal(TemporalType.DATE)
    private Date taskDateFrom;
    @Column(name = "task_date_to")
    @Temporal(TemporalType.DATE)
    private Date taskDateTo;
    @Size(max = 1200)
    @Column(name = "task_todo")
    private String taskTodo;
    @Size(max = 1200)
    @Column(name = "task_note")
    private String taskNote;
    @Column(name = "task_is_compl")
    private Boolean taskIsCompl;

    public EmpJoinTask() {
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

    public Integer getPosIdPosition() {
        return posIdPosition;
    }

    public void setPosIdPosition(Integer posIdPosition) {
        this.posIdPosition = posIdPosition;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getEmpIdEmployee() {
        return empIdEmployee;
    }

    public void setEmpIdEmployee(Integer empIdEmployee) {
        this.empIdEmployee = empIdEmployee;
    }

    public Integer getTaskType() {
        return taskType;
    }

    public void setTaskType(Integer taskType) {
        this.taskType = taskType;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getTaskDateFrom() {
        return taskDateFrom;
    }

    public void setTaskDateFrom(Date taskDateFrom) {
        this.taskDateFrom = taskDateFrom;
    }

    public Date getTaskDateTo() {
        return taskDateTo;
    }

    public void setTaskDateTo(Date taskDateTo) {
        this.taskDateTo = taskDateTo;
    }

    public String getTaskTodo() {
        return taskTodo;
    }

    public void setTaskTodo(String taskTodo) {
        this.taskTodo = taskTodo;
    }

    public String getTaskNote() {
        return taskNote;
    }

    public void setTaskNote(String taskNote) {
        this.taskNote = taskNote;
    }

    public Boolean getTaskIsCompl() {
        return taskIsCompl;
    }

    public void setTaskIsCompl(Boolean taskIsCompl) {
        this.taskIsCompl = taskIsCompl;
    }

    @Override
    public String toString() {
        return "EmpJoinTask{" + "empId=" + empId 
                + ", empSurname=" + empSurname 
                + ", empName=" + empName 
                + ", empMidName=" + empMidName 
                + ", posIdPosition=" + posIdPosition 
                + ", taskId=" + taskId 
                + ", empIdEmployee=" + empIdEmployee 
                + ", taskType=" + taskType 
                + ", taskName=" + taskName 
                + ", taskDateFrom=" + taskDateFrom 
                + ", taskDateTo=" + taskDateTo 
                + ", taskTodo=" + taskTodo 
                + ", taskNote=" + taskNote 
                + ", taskIsCompl=" + taskIsCompl + '}';
    }
    
    public String toHtmlStringTABLE() {
        return "<tr> <td align=\"center\">" + taskId + "</td> "
                + "<td align=\"center\">" + taskName + "</td> "
                + "<td align=\"center\">" + Task_type.getTaskRUnameByCode(taskType) + "</td> "
                + "<td align=\"center\">" + Task_date.getDateDDMonthYYYY(taskDateFrom) + "</td> "
                + "<td align=\"center\">" + Task_date.getDateDDMonthYYYY(taskDateTo) + "</td> "
                + "<td align=\"center\">" + taskTodo + "</td> "
                + "<td align=\"center\">" + taskNote + "</td> "
                + "<td align=\"center\">" + empSurname + " " + empName + " " + empMidName + "</td> "
                + "<td align=\"center\">" + Task_status.getStatusNameByCode(taskIsCompl) + "</td> </tr>";
    }
    
}
