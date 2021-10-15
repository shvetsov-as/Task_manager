/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bllServlets;

import bll_data.DeleteTaskBeanLocal;
import bll_data.UpdateTaskBeanLocal;
import bll_user.ReadUserBeanLocal;
import dal.Task;
import dal.Task_status;
import dal.Task_type;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author User
 */
@WebServlet(name = "ManagerUpdateServlet", urlPatterns = {"/ManagerUpdateServlet"})
public class ManagerUpdateServlet extends HttpServlet {

    DeleteTaskBeanLocal deleteTaskBean = lookupDeleteTaskBeanLocal();
    ;

    ReadUserBeanLocal readUserBean = lookupReadUserBeanLocal();

    UpdateTaskBeanLocal updateTaskBean = lookupUpdateTaskBeanLocal();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String showAll = request.getParameter("showAll");//button on form create to show all tasks
        String hideAll = request.getParameter("hideAll");//button on form create to hide all tasks
        String update = request.getParameter("update");//button on form update
        String delete = request.getParameter("delete");//button on form update
        String restart = request.getParameter("restart");//button on form success

        String taskID = request.getParameter("taskID");
        String taskName = request.getParameter("taskName");
        String taskType = request.getParameter("taskType");
        String dateFrom = request.getParameter("dateFrom");
        String dateTo = request.getParameter("dateTo");
        String taskToDo = request.getParameter("taskToDo");
        String taskNote = request.getParameter("taskNote");
        String employee = request.getParameter("employee");
        String status = request.getParameter("status");

        if (restart != null) {

            request.getRequestDispatcher("manager_menu_update.jsp").forward(request, response);
        }

        if (showAll != null) {
            String s = "show";
            request.setAttribute("showAlltask", s);
            request.getRequestDispatcher("manager_menu_update.jsp").forward(request, response);
        }

        if (hideAll != null) {
            String showAlltask = null;
            request.setAttribute("showAlltask", showAlltask);
            request.getRequestDispatcher("manager_menu_update.jsp").forward(request, response);
        }

        if (delete != null || update != null) {
            Integer intTaskID;
            intTaskID = Integer.parseInt(taskID);
        
        if (delete != null) {
            if (deleteTaskBean.deleteTask(intTaskID)) {
                request.getRequestDispatcher("WEB-INF/success_task_update.jsp").forward(request, response);
            } else {
                String s = "Ошибка удаления. Повторите запрос позже";
                request.setAttribute("answerUpdateServ", s);
                request.getRequestDispatcher("manager_menu_update.jsp").forward(request, response);
            }

        }

        if (update != null) {

            //parsing inputed date from string
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            Date dateFromParsed;
            Date dateToParsed;

            try {
                dateFromParsed = dateFormat.parse(dateFrom);
                dateToParsed = dateFormat.parse(dateTo);
            } catch (ParseException ex) {
                System.out.println("DateParsing exception " + ex.getMessage());
                dateFromParsed = new Date(1212121212121L);
                dateToParsed = new Date(1212121212121L);
            }

            //splitting employee to name, surname, midname
            String surname;
            String name;
            String midname;

            String[] nameParts = employee.trim().split(" ");

            surname = nameParts[0];
            name = nameParts[1];
            midname = nameParts[2];

            //getting employee id to give them new task
            Integer empID = readUserBean.findEmpIDbyFullName(surname, name, midname);

            if (empID.equals(0)) {
                String s = "Пользователь с такими ФИО не найден в базе данных. Обратитесть к администратору безопасности.";
                request.setAttribute("answerUpdateServ", s);
                request.getRequestDispatcher("manager_menu_update.jsp").forward(request, response);
            } else {

                //getting status from string
                boolean statusCode = Task_status.getStatusCodeByName(status);

                //getting task_type code
                Integer taskCode = Task_type.getTaskCodeByRUname(taskType);

                //building new task
                Task task = new Task();

                task.setTaskName(taskName);
                task.setTaskType(taskCode);
                task.setTaskDateFrom(dateFromParsed);
                task.setTaskDateTo(dateToParsed);
                task.setTaskTodo(taskToDo);
                task.setTaskNote(taskNote);
                task.setEmpIdEmployee(empID);
                task.setTaskIsCompl(statusCode);

//                if (createTaskBean.createTask(task)) {
//                    request.getRequestDispatcher("WEB-INF/success_task_create.jsp").forward(request, response);
//                } else {
//
//                    String s = "Запись не создана. Попробуйте позже.";
//                    request.setAttribute("answerCreateServ", s);
//                    request.getRequestDispatcher("manager_menu_create.jsp").forward(request, response);
//                }
            }//if(empID.equals(0))

        }//if (update != null)
        }//if (delete != null || update != null)
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private UpdateTaskBeanLocal lookupUpdateTaskBeanLocal() {
        try {
            Context c = new InitialContext();
            return (UpdateTaskBeanLocal) c.lookup("java:global/TaskManagerProj/TaskManagerProj-ejb/UpdateTaskBean!bll_data.UpdateTaskBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private ReadUserBeanLocal lookupReadUserBeanLocal() {
        try {
            Context c = new InitialContext();
            return (ReadUserBeanLocal) c.lookup("java:global/TaskManagerProj/TaskManagerProj-ejb/ReadUserBean!bll_user.ReadUserBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private DeleteTaskBeanLocal lookupDeleteTaskBeanLocal() {
        try {
            Context c = new InitialContext();
            return (DeleteTaskBeanLocal) c.lookup("java:global/TaskManagerProj/TaskManagerProj-ejb/DeleteTaskBean!bll_data.DeleteTaskBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
