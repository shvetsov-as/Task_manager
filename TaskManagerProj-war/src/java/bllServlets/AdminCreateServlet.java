/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bllServlets;

import bll_user.CreateUserBeanLocal;
import bll_user.ReadUserBeanLocal;
import dal.Role;
import dal.UserJoinThree;
import dao.password.HashGen;
import dao.password.PasswdCheck;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
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
@WebServlet(name = "AdminCreateServlet", urlPatterns = {"/AdminCreateServlet"})
public class AdminCreateServlet extends HttpServlet {

    ReadUserBeanLocal readUserBean = lookupReadUserBeanLocal();
    CreateUserBeanLocal createUserBean = lookupCreateUserBeanLocal();

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

        String create = request.getParameter("create");//button on form create
        String restart = request.getParameter("restart");//button on form success

        String login = request.getParameter("login");
        String passwd1 = request.getParameter("passwd1");
        String passwd2 = request.getParameter("passwd2");
        String role = request.getParameter("role");
        String surname = request.getParameter("surname");
        String name = request.getParameter("name");
        String midname = request.getParameter("midname");
        String position = request.getParameter("position");

        HashMap<String, String> saltHash = new HashMap<>();
        UserJoinThree user = new UserJoinThree();
        Integer roleCode;
        Integer positionCode;
        String hash;
        String salt;
        
        if (restart != null) {
            request.getRequestDispatcher("admin_menu_create.jsp").forward(request, response);
        }

        if (create != null) {
/////////checking input

            if (!PasswdCheck.passwdMatch(passwd1, passwd2)) {
                String s = "Данные ПАРОЛЕЙ не совпадают";
                request.setAttribute("answerCreateServ", s);
                request.getRequestDispatcher("admin_menu_create.jsp").forward(request, response);
            }

            if (!PasswdCheck.passwdCheck(passwd2)) {
                String s = "ПАРОЛЬ не соответствует требованиям. Пароль должен содержать 8 символов, в том числе: строчный, прописной, цифру. Не содержать пробелов.";
                request.setAttribute("answerCreateServ", s);
                request.getRequestDispatcher("admin_menu_create.jsp").forward(request, response);
            }

            if (readUserBean.findByUserLogin(login.trim()).getUserLogin().equals("DUPLICATE") || readUserBean.findByUserLogin(login.trim()).getUserLogin().equals(login.trim())) {
                String s = "ЛОГИН не уникален";
                request.setAttribute("answerCreateServ", s);
                request.getRequestDispatcher("admin_menu_create.jsp").forward(request, response);
            }
            //input check end  

            //Start to build new user
            try {//get new pass and salt
                saltHash = HashGen.hashGen(passwd1);
                
            } catch (NoSuchAlgorithmException ex) {
                String s = "Внутренняя ошибка " + ex.getMessage();
                request.setAttribute("answerCreateServ", s);
                request.getRequestDispatcher("admin_menu_create.jsp").forward(request, response);
            }

            roleCode = Role.getRoleCodeByRUname(role);//get role id by name
            positionCode = readUserBean.findPosIDbyName(position);//get position id by name
            hash = saltHash.get("hash");
            salt = saltHash.get("salt");    

            user.setUserLogin(login);
            user.setUserPasswd(hash);
            user.setUserMark(salt);
            user.setUserRole(roleCode);
            user.setEmpSurname(surname);
            user.setEmpName(name);
            user.setEmpMidName(midname);
            user.setPosIdPosition(positionCode);
            //user builded
            //UserJoinThree user = new UserJoinThree(login, saltHash.get("hash"), saltHash.get("salt"), roleCode, surname, name, midname, positionCode);

//            try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet AdminCreateServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet user.setUserLogin(login) at " + user.getUserLogin() + "</h1>");
//            out.println("<h1>Servlet user.setUserPasswd(saltHash.get(\"hash\")) at " + user.getUserPasswd() + "</h1>");
//            out.println("<h1>Servlet user.setUserMark(saltHash.get(\"salt\")) at " + user.getUserMark() + "</h1>");
//            out.println("<h1>Servlet user.setUserRole(roleCode) at " + user.getUserRole() + "</h1>");
//            out.println("<h1>Servlet user.setEmpSurname(surname) at " + user.getEmpSurname() + "</h1>");
//            out.println("<h1>Servlet user.setEmpName(name) at " + user.getEmpName() + "</h1>");
//            out.println("<h1>Servlet user.setEmpMidName(midname) at " + user.getEmpMidName() + "</h1>");
//            out.println("<h1>Servlet user.setPosIdPosition(positionCode) at " + user.getPosIdPosition() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
            if (createUserBean.createUserJoinThree(user)) {
                saltHash.clear();
                
                request.getRequestDispatcher("WEB-INF/success_user_create.jsp").forward(request, response);
            } else {
                saltHash.clear();
                String s = "Запись не создана. Попробуйте позже.";
                request.setAttribute("answerCreateServ", s);
                request.getRequestDispatcher("admin_menu_create.jsp").forward(request, response);
            }

        }//if (create != null)

//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet AdminCreateServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet AdminCreateServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
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

    private CreateUserBeanLocal lookupCreateUserBeanLocal() {
        try {
            Context c = new InitialContext();
            return (CreateUserBeanLocal) c.lookup("java:global/TaskManagerProj/TaskManagerProj-ejb/CreateUserBean!bll_user.CreateUserBeanLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
