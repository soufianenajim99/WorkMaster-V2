package controllers;


import entities.Admin;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.AdminRepositoryImpl;
import repositories.repositoryinterfaces.AdminRepository;
import services.AdminServiceImpl;
import services.serviceinterfaces.AdminService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "/admin",value = "/admin")
public class AdminServlet extends HttpServlet {
    AdminRepository adminRepository = new AdminRepositoryImpl();
    AdminService adminService = new AdminServiceImpl(adminRepository);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Display list of admins
        List<Admin> admins = adminService.findAll();

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Admin List</h1>");
        out.println("<table border='1'>");
        out.println("<tr><th>ID</th><th>Username</th><th>Email</th><th>Address</th></tr>");

        for (Admin admin : admins) {
            out.println("<tr>");
            out.println("<td>" + admin.getId() + "</td>");
            out.println("<td>" + admin.getUsername() + "</td>");
            out.println("<td>" + admin.getAddress() + "</td>");

            out.println("</tr>");
        }

        out.println("</table>");
        out.println("<h2>Add New Admin</h2>");
        out.println("<form method='POST' action='/WorkMaster_V2_war_exploded/admin'>");
        out.println("Username: <input type='text' name='username'/><br>");
        out.println("Email: <input type='text' name='password'/><br>");
        out.println("Address: <input type='text' name='address'/><br>");
        out.println("<input type='submit' value='Add Admin'/>");
        out.println("</form>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Handle adding a new admin
        String username = req.getParameter("username");
        String email = req.getParameter("password");
        String address = req.getParameter("address");

        // Create a new Admin object
        Admin newAdmin = new Admin();
        newAdmin.setId(UUID.randomUUID()); // Generate a random UUID for the ID
        newAdmin.setUsername(username);
        newAdmin.setPassword(email);
        newAdmin.setAddress(address);

        // Save the admin using AdminService
        adminService.save(newAdmin);

        // Redirect back to the GET method to display updated list
        resp.sendRedirect("/WorkMaster_V2_war_exploded/admin");
    }
}
