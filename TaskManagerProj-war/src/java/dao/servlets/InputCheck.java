/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao.servlets;

import dao.BrutCheck;
import dao.password.PasswdCheck;
import java.io.IOException;
import java.util.Random;
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
@WebServlet(name = "InputCheck", urlPatterns = {"/InputCheck"})
public class InputCheck extends HttpServlet {

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

        //Random digits to generate random sum
        Integer rand1;
        Integer rand2;
        Integer randomResult;

        String submit = request.getParameter("submit");
        String login = request.getParameter("login");
        String pass = request.getParameter("pass");

        HttpSession session = request.getSession();//creating new session

        if (submit != null && login != null && PasswdCheck.passwdCheck(pass)) {//Check login & password, generate random sum to check human input

            //generating random
            Random random = new Random();
            rand1 = random.nextInt(10);
            rand2 = random.nextInt(10);
            randomResult = rand1 + rand2;

            BrutCheck.setRandomResult(randomResult);

            request.setAttribute("answerRandom1", rand1.toString());
            request.setAttribute("answerRandom2", rand2.toString());

            session.setAttribute("login", login);
            session.setAttribute("pass", pass);

            request.getRequestDispatcher("page1.jsp").forward(request, response);
            request.getRequestDispatcher("/Login").forward(request, response);
//            try (PrintWriter out = response.getWriter()) {
//                /* TODO output your page here. You may use following sample code. */
//                out.println("<!DOCTYPE html>");
//                out.println("<html>");
//                out.println("<head>");
//                out.println("<title>Servlet Brut</title>");
//                out.println("</head>");
//                out.println("<body>");
//                out.println("<h1>Servlet Brut at " + request.getContextPath() + "</h1>");
//                out.println("<h1>Servlet Brut at " + "A " + rand1 + " B " + rand2 + " C " + randomResult + "</h1>");
//                out.println("</body>");
//                out.println("</html>");
//
//            }
        } else {
            String s = "Ошибка ввода логина или пароля";
            request.setAttribute("answerInputCheck", s);
            request.getRequestDispatcher("index.jsp").forward(request, response);
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

}
