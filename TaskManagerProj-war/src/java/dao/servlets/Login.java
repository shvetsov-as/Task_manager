/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.servlets;

import bll_user.ReadUserBeanLocal;
import dal.Role;
import dal.Users;
import dao.BrutCheck;
import dao.password.HashCheck;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
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
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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

        List<Users> listUsers = new ArrayList<>(); // List for resul
        HashMap<String, String> map_salt_hash = new HashMap<>();

        String submit = request.getParameter("submit");
        String test = request.getParameter("test");

        HttpSession session = request.getSession();
        String sessionLogin = (String) session.getAttribute("login");
        String sessionPass = (String) session.getAttribute("pass");
        boolean flagHashChecked = false;

        boolean flagBrutTestPassed = false;
        String param = "";//test param

        try {
            Integer testCheck;
            testCheck = Integer.parseInt(test); //NUMBER FORMAT EXCEPTION 
            flagBrutTestPassed = BrutCheck.check(testCheck);
        } catch (NumberFormatException e) {
            System.out.println("dao.Login exception in Integer.parseInt(test) " + e.getMessage());

            String s = "Вы ввели не число";
            request.setAttribute("answerInputCheck", s);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

        if (flagBrutTestPassed) {

            Users user = new Users();
            user = readUserBean.findByUserLogin(sessionLogin); //checking login - input & DB match
            map_salt_hash.put("salt", user.getUserMark());
            map_salt_hash.put("hash", user.getUserPasswd());

            //checking password - input & DB match
            try {
                flagHashChecked = HashCheck.hashCheck(map_salt_hash, sessionPass);

                //if match
                if (flagHashChecked) {

                    session.setAttribute("userToHtml", user.toHtmlString());
                    session.setAttribute("userId", user.getUserId());
                    session.setAttribute("userSalt", user.getUserMark());
                    session.setAttribute("userRole", user.getUserRole());

                    switch ((Integer) session.getAttribute("userRole")) {
                        case (1):
                            session.setAttribute("role", Role.ADMIN);
                            request.getRequestDispatcher("admin_menu.jsp").forward(request, response);
                            
                            break;
                        case (2):
                            session.setAttribute("role", Role.MANAGER);
                            request.getRequestDispatcher("manager_menu.jsp").forward(request, response);
                            break;
                        case (3):
                            session.setAttribute("role", Role.USER);
                            request.getRequestDispatcher("user_menu.jsp").forward(request, response);
                            break;
                        case (4):
                            session.setAttribute("role", Role.UNKNOWN);
                            String s = "Ошибка аутентификации. Обратитесь за помощью к администратору безопасности";
                            request.setAttribute("answerInputCheck", s);
                            request.getRequestDispatcher("index.jsp").forward(request, response);
                            break;

                    }

                    param = (String) session.getAttribute("userToHtml");

                } else {
                    String s = "Ошибка в логине или пароле. Проверьте отсутсвие пробелов. <Пароль> и <пароль> - разные пароли";
                    request.setAttribute("answerInputCheck", s);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }

            } catch (NoSuchAlgorithmException ex) {
                System.out.println("HashCheck.hashCheck(map_salt_hash, sessionPass) ERROR " + ex.getMessage());
            } //try hash end

        } //if (flagBrutTestPassed) end

//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet Login</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Login at BRUTCHECK it works! </h1>");
//            out.println("<h1>Your answer is correct </h1>");
//            out.println("<h1>Your login is " + sessionLogin + " </h1>");
//            out.println("<h1>Your password is " + sessionPass + " </h1>");
//            out.println("<h1>Can you log in? " + flagHashChecked + " </h1>");
//            out.println("<h1>Your session param is < " + param + " > </h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }

        String s = "Сумма чисел - ошибочный ответ";
        request.setAttribute("answerInputCheck", s);
        request.getRequestDispatcher("index.jsp").forward(request, response);

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
