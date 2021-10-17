/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "task")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Task.findAll", query = "SELECT t FROM Task t")
    , @NamedQuery(name = "Task.findByTaskId", query = "SELECT t FROM Task t WHERE t.taskId = :taskId")
    , @NamedQuery(name = "Task.findByEmpIdEmployee", query = "SELECT t FROM Task t WHERE t.empIdEmployee = :empIdEmployee")
    , @NamedQuery(name = "Task.findByTaskType", query = "SELECT t FROM Task t WHERE t.taskType = :taskType")
    , @NamedQuery(name = "Task.findByTaskName", query = "SELECT t FROM Task t WHERE t.taskName = :taskName")
    , @NamedQuery(name = "Task.findByTaskDateFrom", query = "SELECT t FROM Task t WHERE t.taskDateFrom = :taskDateFrom")
    , @NamedQuery(name = "Task.findByTaskDateTo", query = "SELECT t FROM Task t WHERE t.taskDateTo = :taskDateTo")
    , @NamedQuery(name = "Task.findByTaskTodo", query = "SELECT t FROM Task t WHERE t.taskTodo = :taskTodo")
    , @NamedQuery(name = "Task.findByTaskNote", query = "SELECT t FROM Task t WHERE t.taskNote = :taskNote")
    , @NamedQuery(name = "Task.findByTaskIsCompl", query = "SELECT t FROM Task t WHERE t.taskIsCompl = :taskIsCompl")})
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Integer taskId;
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

    public Task() {
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
        return "Task{" + "taskId=" + taskId + ", empIdEmployee=" + empIdEmployee + ", taskType=" + taskType + ", taskName=" + taskName + ", taskDateFrom=" + taskDateFrom + ", taskDateTo=" + taskDateTo + ", taskTodo=" + taskTodo + ", taskNote=" + taskNote + ", taskIsCompl=" + taskIsCompl + '}';
    }

}
