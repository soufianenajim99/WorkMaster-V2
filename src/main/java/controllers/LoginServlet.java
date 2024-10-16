package controllers;

import entities.Admin;
import entities.Employee;
import entities.Recruiter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import services.AutenticationServiceImpl;
import services.serviceinterfaces.AuthenticationService;


import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // Use AuthenticationService instead of UserService
        AuthenticationService authenticationService = new AutenticationServiceImpl();

        boolean isAuthenticated = false;

        switch (role) {
            case "admin":
                isAuthenticated = authenticationService.authenticateAdmin(username, password);
                if (isAuthenticated) {
                    HttpSession session = request.getSession();
                    Admin admin =(Admin) authenticationService.getUser("Admin",username,password);
                    session.setAttribute("user", admin);
                    response.sendRedirect("views/admin/dashboard.jsp"); // Redirect to admin dashboard
                }
                break;

            case "recruiter":
                isAuthenticated = authenticationService.authenticateRecruiter(username, password);
                if (isAuthenticated) {
                    HttpSession session = request.getSession();
                    Recruiter recruiter =(Recruiter) authenticationService.getUser("Recruiter",username,password);
                    session.setAttribute("user", recruiter);

                    response.sendRedirect("views/recruiter/dashboard.jsp"); // Redirect to recruiter dashboard
                }
                break;

            case "employee":
                isAuthenticated = authenticationService.authenticateEmployee(username, password);
                if (isAuthenticated) {
                    HttpSession session = request.getSession();
                    Employee employee =(Employee) authenticationService.getUser("Employee",username,password);

                    session.setAttribute("user", employee);
                    response.sendRedirect("views/employee/dashboard.jsp"); // Redirect to employee dashboard
                }
                break;

            default:
                request.setAttribute("errorMessage", "Invalid role selected.");
                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
        }

        if (!isAuthenticated) {
            request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
