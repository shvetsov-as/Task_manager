/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bllServlets;

import bll_user.DeleteUserBeanLocal;
import bll_user.ReadUserBeanLocal;
import bll_user.UpdateUserBeanLocal;
import dal.Role;
import dao.password.HashGen;
import dao.password.PasswdCheck;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
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
@WebServlet(name = "AdminUpdateServlet", urlPatterns = {"/AdminUpdateServlet"})
public class AdminUpdateServlet extends HttpServlet {

    UpdateUserBeanLocal updateUserBean = lookupUpdateUserBeanLocal();

    ReadUserBeanLocal readUserBean = lookupReadUserBeanLocal();

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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        String delete = request.getParameter("delete");//button on form update
        String update = request.getParameter("update");//button on form update
        String restart = request.getParameter("restart");//button on form success

        String userId = request.getParameter("userId");
        String login = request.getParameter("login");
        String passwd1 = request.getParameter("passwd1");
        String passwd2 = request.getParameter("passwd2");
        String role = request.getParameter("role");
        String surname = request.getParameter("surname");
        String name = request.getParameter("name");
        String midname = request.getParameter("midname");
        String position = request.getParameter("position");

        Map<String, String> saltHash = new HashMap<>();
        //UserJoinThree user = new UserJoinThree();
        Integer roleCode;
        Integer positionCode;

        if (update != null) {
/////////checking input

            if (!PasswdCheck.passwdMatch(passwd1, passwd2)) {
                String s = "Данные ПАРОЛЕЙ не совпадают";
                request.setAttribute("answerUpdateServ", s);
                request.getRequestDispatcher("admin_menu_update.jsp").forward(request, response);
            }

            if (!PasswdCheck.passwdCheck(passwd2)) {
                String s = "ПАРОЛЬ не соответствует требованиям. Пароль должен содержать 8 символов, в том числе: строчный, прописной, цифру. Не содержать пробелов.";
                request.setAttribute("answerUpdateServ", s);
                request.getRequestDispatcher("admin_menu_update.jsp").forward(request, response);
            }

            if (readUserBean.findByUserLogin(login.trim()).getUserLogin().equals("DUPLICATE") || readUserBean.findByUserLogin(login.trim()).getUserLogin().equals(login.trim())) {
                String s = "ЛОГИН не уникален";
                request.setAttribute("answerUpdateServ", s);
                request.getRequestDispatcher("admin_menu_update.jsp").forward(request, response);
            }
            //input check end  

            //Start to build new user
            try {//get new pass and salt
                saltHash = HashGen.hashGen(passwd1);
            } catch (NoSuchAlgorithmException ex) {
                String s = "Внутренняя ошибка " + ex.getMessage();
                request.setAttribute("answerUpdateServ", s);
                request.getRequestDispatcher("admin_menu_update.jsp").forward(request, response);
            }

            roleCode = Role.getRoleCodeByRUname(role);//get role id by name
            positionCode = readUserBean.findPosIDbyName(position);//get position id by name

            
            
            
            
            saltHash.clear();

            request.getRequestDispatcher("WEB-INF/success_user_update.jsp").forward(request, response);

        }
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

    private DeleteUserBeanLocal lookupDeleteUserBeanLocal() {
        try {
            Context c = new InitialContext();
            return (DeleteUserBeanLocal) c.lookup("java:global/TaskManagerProj/TaskManagerProj-ejb/DeleteUserBean!bll_user.DeleteUserBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private UpdateUserBeanLocal lookupUpdateUserBeanLocal() {
        try {
            Context c = new InitialContext();
            return (UpdateUserBeanLocal) c.lookup("java:global/TaskManagerProj/TaskManagerProj-ejb/UpdateUserBean!bll_user.UpdateUserBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
