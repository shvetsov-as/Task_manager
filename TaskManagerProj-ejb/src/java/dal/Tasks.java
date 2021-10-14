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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "tasks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tasks.findAll", query = "SELECT t FROM Tasks t")
    , @NamedQuery(name = "Tasks.findByTaskId", query = "SELECT t FROM Tasks t WHERE t.taskId = :taskId")
    , @NamedQuery(name = "Tasks.findByTaskType", query = "SELECT t FROM Tasks t WHERE t.taskType = :taskType")
    , @NamedQuery(name = "Tasks.findByTaskName", query = "SELECT t FROM Tasks t WHERE t.taskName = :taskName")
    , @NamedQuery(name = "Tasks.findByTaskDateFrom", query = "SELECT t FROM Tasks t WHERE t.taskDateFrom = :taskDateFrom")
    , @NamedQuery(name = "Tasks.findByTaskDateTo", query = "SELECT t FROM Tasks t WHERE t.taskDateTo = :taskDateTo")
    , @NamedQuery(name = "Tasks.findByTaskTodo", query = "SELECT t FROM Tasks t WHERE t.taskTodo = :taskTodo")
    , @NamedQuery(name = "Tasks.findByTaskNote", query = "SELECT t FROM Tasks t WHERE t.taskNote = :taskNote")
    , @NamedQuery(name = "Tasks.findByTaskIsCompl", query = "SELECT t FROM Tasks t WHERE t.taskIsCompl = :taskIsCompl")})
public class Tasks implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "task_id")
    private Integer taskId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "task_type")
    private int taskType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "task_name")
    private String taskName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "task_date_from")
    @Temporal(TemporalType.DATE)
    private Date taskDateFrom;
    @Basic(optional = false)
    @NotNull
    @Column(name = "task_date_to")
    @Temporal(TemporalType.DATE)
    private Date taskDateTo;
    @Size(max = 1200)
    @Column(name = "task_todo")
    private String taskTodo;
    @Size(max = 1200)
    @Column(name = "task_note")
    private String taskNote;
    @Basic(optional = false)
    @NotNull
    @Column(name = "task_is_compl")
    private boolean taskIsCompl;
    @JoinColumn(name = "emp_id_employee", referencedColumnName = "emp_id")
    @ManyToOne(optional = false)
    private Employee empIdEmployee;

    public Tasks() {
    }

    public Tasks(Integer taskId) {
        this.taskId = taskId;
    }

    public Tasks(Integer taskId, int taskType, String taskName, Date taskDateFrom, Date taskDateTo, boolean taskIsCompl) {
        this.taskId = taskId;
        this.taskType = taskType;
        this.taskName = taskName;
        this.taskDateFrom = taskDateFrom;
        this.taskDateTo = taskDateTo;
        this.taskIsCompl = taskIsCompl;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public int getTaskType() {
        return taskType;
    }

    public void setTaskType(int taskType) {
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

    public boolean getTaskIsCompl() {
        return taskIsCompl;
    }

    public void setTaskIsCompl(boolean taskIsCompl) {
        this.taskIsCompl = taskIsCompl;
    }

    public Employee getEmpIdEmployee() {
        return empIdEmployee;
    }

    public void setEmpIdEmployee(Employee empIdEmployee) {
        this.empIdEmployee = empIdEmployee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taskId != null ? taskId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tasks)) {
            return false;
        }
        Tasks other = (Tasks) object;
        if ((this.taskId == null && other.taskId != null) || (this.taskId != null && !this.taskId.equals(other.taskId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tasks{" + "taskId=" + taskId 
                + ", taskType=" + taskType 
                + ", taskName=" + taskName 
                + ", taskDateFrom=" + taskDateFrom 
                + ", taskDateTo=" + taskDateTo 
                + ", taskTodo=" + taskTodo 
                + ", taskNote=" + taskNote 
                + ", taskIsCompl=" + taskIsCompl 
                + ", empIdEmployee=" + empIdEmployee + '}';
    }

   
    
    public String toHtmlString() {
        return "<li> dal.Tasks[ taskId=" + taskId + " ] </li>";
    }

}
