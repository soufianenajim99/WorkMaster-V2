package controllers;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.EmployeeRepositoryImpl;
import repositories.repositoryinterfaces.EmployeeRepository;
import services.EmployeeServiceImpl;
import services.serviceinterfaces.EmployeeService;

import java.io.IOException;

import java.util.UUID;
@WebServlet("/deleteEmployee")
public class DeleteEmployeeServlet extends HttpServlet {

    EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
    EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        UUID employeeId = UUID.fromString(id);
        employeeService.deleteById(employeeId);
        response.sendRedirect("/WorkMaster_V2_war_exploded/manageEmployees");
    }
}

