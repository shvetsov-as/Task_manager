/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bllServlets;

import bll_data.CreateTaskBeanLocal;
import bll_user.ReadUserBeanLocal;
import dal.Task;
import dal.Task_status;
import dal.Task_type;
import java.io.IOException;
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
@WebServlet(name = "ManagerCreateServlet", urlPatterns = {"/ManagerCreateServlet"})
public class ManagerCreateServlet extends HttpServlet {

    @EJB
    private CreateTaskBeanLocal createTaskBean;

    ReadUserBeanLocal readUserBean = lookupReadUserBeanLocal();

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
        String create = request.getParameter("create");//button on form create
        String restart = request.getParameter("restart");//button on form success

        String taskName = request.getParameter("taskName");
        String taskType = request.getParameter("taskType");
        String dateFrom = request.getParameter("dateFrom");
        String dateTo = request.getParameter("dateTo");
        String taskToDo = request.getParameter("taskToDo");
        String taskNote = request.getParameter("taskNote");
        String employee = request.getParameter("employee");
        String status = request.getParameter("status");

        if (restart != null) {
            request.getRequestDispatcher("manager_menu_create.jsp").forward(request, response);
        }

        if (showAll != null) {
            String s = "show";
            request.setAttribute("showAlltask", s);
            request.getRequestDispatcher("manager_menu_create.jsp").forward(request, response);
        }

        if (hideAll != null) {
            String showAlltask = null;
            request.setAttribute("showAlltask", showAlltask);
            request.getRequestDispatcher("manager_menu_create.jsp").forward(request, response);
        }

        if (create != null) {

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

            System.out.println("dateFromParsed " + dateFromParsed + " dateToParsed " + dateToParsed + "****************************");

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
                request.setAttribute("answerCreateServ", s);
                request.getRequestDispatcher("manager_menu_create.jsp").forward(request, response);
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

                System.out.println(task.toString());

                if (createTaskBean.createTask(task)) {
                    request.getRequestDispatcher("WEB-INF/success_task_create.jsp").forward(request, response);
                } else {

                    String s = "Запись не создана. Попробуйте позже.";
                    request.setAttribute("answerCreateServ", s);
                    request.getRequestDispatcher("manager_menu_create.jsp").forward(request, response);
                }

            }//if(empID.equals(0))

        }//if (create != null)

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

    private ReadUserBeanLocal lookupReadUserBeanLocal() {
        try {
            Context c = new InitialContext();
            return (ReadUserBeanLocal) c.lookup("java:global/TaskManagerProj/TaskManagerProj-ejb/ReadUserBean!bll_user.ReadUserBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
