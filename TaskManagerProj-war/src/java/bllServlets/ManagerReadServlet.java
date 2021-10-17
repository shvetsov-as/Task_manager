/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bllServlets;

import bll_data.ReadTaskBeanLocal;
import bll_user.ReadUserBeanLocal;
import dal.EmpJoinTask;
import dal.Task;
import dal.Task_status;
import dal.Task_type;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@WebServlet(name = "ManagerReadServlet", urlPatterns = {"/ManagerReadServlet"})
public class ManagerReadServlet extends HttpServlet {

    ReadTaskBeanLocal readTaskBean = lookupReadTaskBeanLocal();

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
        String find = request.getParameter("find");//button on form read

        String taskName = request.getParameter("taskName");
        String taskType = request.getParameter("taskType");
        String dateFrom = request.getParameter("dateFrom");
        String dateTo = request.getParameter("dateTo");
        String taskToDo = request.getParameter("taskToDo");
        String taskNote = request.getParameter("taskNote");
        String employee = request.getParameter("employee");
        String status = request.getParameter("status");

        if (showAll != null) {
            String s = "show";
            request.setAttribute("showAlltask", s);
            request.getRequestDispatcher("manager_menu_read.jsp").forward(request, response);
        }

        if (hideAll != null) {
            String showAlltask = null;
            request.setAttribute("showAlltask", showAlltask);
            request.getRequestDispatcher("manager_menu_read.jsp").forward(request, response);
        }

        if (find != null) {

            EmpJoinTask task = new EmpJoinTask();
            int count = 0;

            if (taskName != null && !taskName.isEmpty()) {
                //building new task
                task.setTaskName(taskName);
                count++;
            } else {
                task.setTaskName("null");
            }

            if (taskNote != null && !taskNote.isEmpty()) {
                //building new task
                task.setTaskNote(taskNote);
                count++;
            } else {
                task.setTaskNote("null");
            }

            if (taskToDo != null && !taskToDo.isEmpty()) {
                //building new task
                task.setTaskTodo(taskToDo);
                count++;
            } else {
                task.setTaskTodo("null");
            }

            //parsing inputed date from string
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

            if (dateFrom != null && !dateFrom.isEmpty()) {
                count++;
                Date dateFromParsed;
                try {
                    dateFromParsed = dateFormat.parse(dateFrom);

                    //building new task
                    task.setTaskDateFrom(dateFromParsed);
                } catch (ParseException ex) {
                    System.out.println("DateParsing exception " + ex.getMessage());
                }
            }

            if (dateTo != null && !dateTo.isEmpty()) {
                count++;
                Date dateToParsed;
                try {
                    dateToParsed = dateFormat.parse(dateTo);

                    //building new task
                    task.setTaskDateTo(dateToParsed);
                } catch (ParseException ex) {
                    System.out.println("DateParsing exception " + ex.getMessage());
                }
            }

            if (employee != null && !employee.isEmpty()) {
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

                task.setEmpIdEmployee(empID);
                count++;
            } else {
                task.setEmpIdEmployee(0);// 0 - to avoid NullPointerExc when we building a jpql query in session bean, in database id starts from 1
            }

            if (status != null && !status.isEmpty()) {
                //getting status from string
                boolean statusCode = Task_status.getStatusCodeByName(status);

                //building new task
                task.setTaskIsCompl(statusCode);
                count++;
            }

            if (taskType != null && !taskType.isEmpty()) {
                //getting task_type code
                Integer taskCode = Task_type.getTaskCodeByRUname(taskType);

                //building new task
                task.setTaskType(taskCode);
                count++;
            } else {
                task.setTaskType(0);// 0 - to avoid NullPointerExc when we building a jpql query in session bean, in database no 0 code, code starts from 1
            }

            if (count == 0) {
                String s = "Параметры поиска не заданы.";
                request.setAttribute("answerReadServ", s);
                request.getRequestDispatcher("manager_menu_read.jsp").forward(request, response);
            } else {

                System.out.println(task.toString());
                System.out.println("################");
                List<EmpJoinTask> listSearchRes = readTaskBean.findByParameter(task);

                if (listSearchRes.size() > 0) {//do we have a result of search?
                    request.setAttribute("find", find);
                    request.setAttribute("listSearchRes", listSearchRes);
                    request.getRequestDispatcher("manager_menu_read.jsp").forward(request, response);
                } else {
                    String s = "Результат поиска отсутствует. Попробуйте изменить параметры.";
                    request.setAttribute("answerReadServ", s);
                    request.getRequestDispatcher("manager_menu_read.jsp").forward(request, response);
                }

                for (EmpJoinTask res : listSearchRes) {
                    System.out.println(res.toString() + "*************************************************");

                }
                System.out.println(listSearchRes.size() + "*************************************************");
            }


        }//if (find != null)
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

    private ReadTaskBeanLocal lookupReadTaskBeanLocal() {
        try {
            Context c = new InitialContext();
            return (ReadTaskBeanLocal) c.lookup("java:global/TaskManagerProj/TaskManagerProj-ejb/ReadTaskBean!bll_data.ReadTaskBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
