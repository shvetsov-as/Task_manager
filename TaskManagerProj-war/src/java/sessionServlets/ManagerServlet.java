/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionServlets;

import dal.Role;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ManagerServlet", urlPatterns = {"/ManagerServlet"})
public class ManagerServlet extends HttpServlet {

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
        
        String findTask = request.getParameter("findTask");
        String createTask = request.getParameter("createTask");
        String updateTask = request.getParameter("updateTask");

        if (session.isNew() || !((Role) session.getAttribute("role")).equals(Role.MANAGER)) {
            String s = "Время Вашей сессии истекло";// internal error? null pointer exc !((Role) session.getAttribute("role")).equals(Role.ADMIN); session.isNew() didnt work
            session.invalidate();
            request.setAttribute("answerInputCheck", s);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {

            if (findTask != null) {
                request.getRequestDispatcher("manager_menu_find.jsp").forward(request, response);

            }

            if (createTask != null) {
                request.getRequestDispatcher("manager_menu_create.jsp").forward(request, response);

            }

            if (updateTask != null) {
                request.getRequestDispatcher("manager_menu_update.jsp").forward(request, response);

            }

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
