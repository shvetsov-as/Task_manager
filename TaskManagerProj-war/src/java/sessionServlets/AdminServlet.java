/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionServlets;

//import dal.Positions;
import bll_user.ReadUserBeanLocal;
import dal.Role;
import dal.Users;
import java.io.IOException;
//import java.io.PrintWriter;
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
@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {

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

        //from admin.jsp menu buttons
        String allUsers = request.getParameter("allUsers");
        String createUser = request.getParameter("createUser");
        String updateUser = request.getParameter("updateUser");
        String newPosition = request.getParameter("newPosition");

        if (session.isNew() || !((Role) session.getAttribute("role")).equals(Role.ADMIN)) {
            String s = "Время Вашей сессии истекло";// internal error? null pointer exc !((Role) session.getAttribute("role")).equals(Role.ADMIN); session.isNew() didnt work
            session.invalidate();
            request.setAttribute("answerInputCheck", s);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {

            if (allUsers != null) {

                List<Users> listUsers = new ArrayList<>();

                
                    listUsers = readUserBean.findAllusers();
                    request.setAttribute("readUserAnswer", listUsers);
                    request.getRequestDispatcher("admin_menu.jsp").forward(request, response);
                
//                try (PrintWriter out = response.getWriter()) {
//                    /* TODO output your page here. You may use following sample code. */
//                    out.println("<!DOCTYPE html>");
//                    out.println("<html>");
//                    out.println("<head>");
//                    out.println("<title>Servlet AdmServlet</title>");
//                    out.println("</head>");
//                    out.println("<body>");
//                    out.println("<h1>Servlet AdmServlet at " + request.getContextPath() + "</h1>");
//                    out.println("<h1>Servlet AdmServlet at " + test1 + "</h1>");
//                    out.println("</body>");
//                    out.println("</html>");
//                }
            }// end if allusers

            if (createUser != null) {
                request.getRequestDispatcher("admin_menu_create.jsp").forward(request, response);

            }

            if (updateUser != null) {
                request.getRequestDispatcher("admin_menu_update.jsp").forward(request, response);

            }

            if (newPosition != null) {
                request.getRequestDispatcher("admin_menu_positions.jsp").forward(request, response);

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
