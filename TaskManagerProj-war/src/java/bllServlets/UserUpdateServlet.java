/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bllServlets;

import bll_data.DeleteTaskBeanLocal;
import bll_data.ReadTaskBeanLocal;
import bll_data.UpdateTaskBeanLocal;
import bll_user.ReadUserBeanLocal;
import dal.Task;
import dal.Task_status;
import java.io.IOException;
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
@WebServlet(name = "UserUpdateServlet", urlPatterns = {"/UserUpdateServlet"})
public class UserUpdateServlet extends HttpServlet {

    ReadTaskBeanLocal readTaskBean = lookupReadTaskBeanLocal();

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
        String restart = request.getParameter("restart");//button on form success

        String taskID = request.getParameter("taskID");
        String taskToDo = request.getParameter("taskToDo");
        String taskNote = request.getParameter("taskNote");
        String status = request.getParameter("status");

        if (restart != null) {

            request.getRequestDispatcher("user_menu_update.jsp").forward(request, response);
        }

        if (showAll != null) {
            String s = "show";
            request.setAttribute("showAlltask", s);
            request.getRequestDispatcher("user_menu_update.jsp").forward(request, response);
        }

        if (hideAll != null) {
            String showAlltask = null;
            request.setAttribute("showAlltask", showAlltask);
            request.getRequestDispatcher("user_menu_update.jsp").forward(request, response);
        }

        
            Integer intTaskID;
            intTaskID = Integer.parseInt(taskID);

            if (update != null) {
                //building new task start
                Task task = readTaskBean.findTaskById(intTaskID);

                if (task == null) {
                    String s = "Пользователь с такими ФИО не найден в базе данных. Обратитесть к администратору безопасности.";
                    request.setAttribute("answerUpdateServ", s);
                    request.getRequestDispatcher("user_menu_update.jsp").forward(request, response);
                } else {

                    if (taskNote != null && !taskNote.isEmpty()) {
                        //building new task
                        task.setTaskNote(taskNote);
                    }

                    if (taskToDo != null && !taskToDo.isEmpty()) {
                        //building new task
                        task.setTaskTodo(taskToDo);
                    }

                    if (status != null && !status.isEmpty()) {
                        //getting status from string
                        boolean statusCode = Task_status.getStatusCodeByName(status);

                        //building new task
                        task.setTaskIsCompl(statusCode);
                    }

                if (updateTaskBean.updateTask(task)) {
                    request.getRequestDispatcher("WEB-INF/success_task_update_user.jsp").forward(request, response);
                } else {

                    String s = "Запись не создана. Попробуйте позже.";
                    request.setAttribute("answerUpdateServ", s);
                    request.getRequestDispatcher("user_menu_update.jsp").forward(request, response);
                }

                }// if (task == null)
            }//if (update != null)

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
