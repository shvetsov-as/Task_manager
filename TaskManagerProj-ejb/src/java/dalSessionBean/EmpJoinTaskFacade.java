/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dalSessionBean;

import dal.EmpJoinTask;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/**
 *
 * @author User
 */
@Stateless
public class EmpJoinTaskFacade extends AbstractFacade<EmpJoinTask> implements EmpJoinTaskFacadeLocal {

    @PersistenceContext(unitName = "TaskManagerProj-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EmpJoinTaskFacade() {
        super(EmpJoinTask.class);
    }

    @Override
    public List<EmpJoinTask> findByParameter(EmpJoinTask taskParameters, String query) { //to get List of tasks by parameters
        TypedQuery<EmpJoinTask> resultQuery = em.createQuery(query, EmpJoinTask.class);

        resultQuery.setParameter("taskIsCompl", taskParameters.getTaskIsCompl());

        if (taskParameters.getEmpIdEmployee() != null && taskParameters.getEmpIdEmployee() != 0) {
            resultQuery.setParameter("empIdEmployee", taskParameters.getEmpIdEmployee());
        }
        if (taskParameters.getTaskName() != null && !taskParameters.getTaskName().isEmpty() && !taskParameters.getTaskName().equals("null")) {
            resultQuery.setParameter("taskName", "%" + taskParameters.getTaskName() + "%"); //double %% for LIKE operator. Anything before and anything after result
        }
        if (taskParameters.getTaskTodo() != null && !taskParameters.getTaskTodo().isEmpty() && !taskParameters.getTaskTodo().equals("null")) {
            resultQuery.setParameter("taskTodo", "%" + taskParameters.getTaskTodo() + "%");
        }
        if (taskParameters.getTaskNote() != null && !taskParameters.getTaskNote().isEmpty() && !taskParameters.getTaskNote().equals("null")) {
            resultQuery.setParameter("taskNote", "%" + taskParameters.getTaskNote() + "%");
        }
        if (taskParameters.getTaskType() != null && taskParameters.getTaskType() != 0) {
            resultQuery.setParameter("taskType", taskParameters.getTaskType());
        }
        if (taskParameters.getTaskDateFrom() != null) {
            resultQuery.setParameter("taskDateFrom", taskParameters.getTaskDateFrom());
        }
        if (taskParameters.getTaskDateTo() != null) {
            resultQuery.setParameter("taskDateTo", taskParameters.getTaskDateTo());
        }

        List<EmpJoinTask> result = resultQuery.getResultList();

        return result;

        
    }

}
