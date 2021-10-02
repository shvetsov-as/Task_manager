/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bllServlets;

import bll_user.CreateUserBeanLocal;
import bll_user.DeleteUserBeanLocal;
import bll_user.ReadUserBeanLocal;
import bll_user.UpdateUserBeanLocal;
import dal.Positions;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "AdminPositionServlet", urlPatterns = {"/AdminPositionServlet"})
public class AdminPositionServlet extends HttpServlet {

    ReadUserBeanLocal readUserBean = lookupReadUserBeanLocal();
    UpdateUserBeanLocal updateUserBean = lookupUpdateUserBeanLocal();
    CreateUserBeanLocal createUserBean = lookupCreateUserBeanLocal();
    DeleteUserBeanLocal deleteUserBean = lookupDeleteUserBeanLocal();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private boolean duplicateCheck(String inputField) {

        List<Positions> posList = new ArrayList<>();
        posList = readUserBean.allPositions();
        for (Positions p : posList) {
            if (p.getPosition().equalsIgnoreCase(inputField.trim())) {//Checking for duplicate
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("empty-statement")
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        String inputField = request.getParameter("posName");
        String positionDrop = request.getParameter("positionDrop");
        String buttonCreateUpdate = request.getParameter("posCreateUpdate");
        String buttonDelete = request.getParameter("posDelete");

        if (buttonCreateUpdate != null) {

            //Update position
            if (!inputField.isEmpty() && !positionDrop.equals("Новая должность")) {

                if (duplicateCheck(inputField)) {//duplicate check
                    Integer intPositionDrop = Integer.parseInt(positionDrop);
                    if (updateUserBean.updatePosition(intPositionDrop, inputField)) {//Updating position
                        String s = "Данные должности обновлены";
                        request.setAttribute("answerPosServ", s);
                        request.getRequestDispatcher("admin_menu_positions.jsp").forward(request, response);
                    } else {
                        String s = "Ошибка обновления, повторите запрос позже...";
                        request.setAttribute("answerPosServ", s);
                        request.getRequestDispatcher("admin_menu_positions.jsp").forward(request, response);
                    }

                } else {
                    String s = "Данная должность уже добавлена в список";
                    request.setAttribute("answerPosServ", s);
                    request.getRequestDispatcher("admin_menu_positions.jsp").forward(request, response);
                }//if(duplicateCheck(inputField))

            }//if (!inputField.isEmpty() && !positionDrop.equals("Новая должность"))

            //Create position
            if (!inputField.isEmpty()) {

                if (duplicateCheck(inputField)) {//duplicate check
                    if (createUserBean.createPosition(inputField)) {//Creating new position
                        String s = "Новая должность добавлена";
                        request.setAttribute("answerPosServ", s);
                        request.getRequestDispatcher("admin_menu_positions.jsp").forward(request, response);
                    }

                } else {

                    String s = "Данная должность уже добавлена в список";
                    request.setAttribute("answerPosServ", s);
                    request.getRequestDispatcher("admin_menu_positions.jsp").forward(request, response);
                }

            } else {
                String s = "Укажите наименование должности";
                request.setAttribute("answerPosServ", s);
                request.getRequestDispatcher("admin_menu_positions.jsp").forward(request, response);
            }

        }//if (buttonCreateUpdate != null)

        if (buttonDelete != null) {
            if (!positionDrop.equals("Новая должность")) {
                Integer intPositionDrop = Integer.parseInt(positionDrop);
                if (deleteUserBean.deletePosition(intPositionDrop)) {
                    String s = "Должность успешно удалена";
                    request.setAttribute("answerPosServ", s);
                    request.getRequestDispatcher("admin_menu_positions.jsp").forward(request, response);

                } else {
                    String s = "Ошибка удаления, повторите запрос позже...";
                    request.setAttribute("answerPosServ", s);
                    request.getRequestDispatcher("admin_menu_positions.jsp").forward(request, response);
                }
            } else {
                String s = "Код должности не указан. Удаление не выполнено.";
                request.setAttribute("answerPosServ", s);
                request.getRequestDispatcher("admin_menu_positions.jsp").forward(request, response);
            }
        }//if (buttonDelete != null)

    }//processRequest

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

    private UpdateUserBeanLocal lookupUpdateUserBeanLocal() {
        try {
            Context c = new InitialContext();
            return (UpdateUserBeanLocal) c.lookup("java:global/TaskManagerProj/TaskManagerProj-ejb/UpdateUserBean!bll_user.UpdateUserBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private CreateUserBeanLocal lookupCreateUserBeanLocal() {
        try {
            Context c = new InitialContext();
            return (CreateUserBeanLocal) c.lookup("java:global/TaskManagerProj/TaskManagerProj-ejb/CreateUserBean!bll_user.CreateUserBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private DeleteUserBeanLocal lookupDeleteUserBeanLocal() {
        try {
            Context c = new InitialContext();
            return (DeleteUserBeanLocal) c.lookup("java:global/TaskManagerProj/TaskManagerProj-ejb/DeleteUserBean!bll_user.DeleteUserBeanLocal");
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

}
