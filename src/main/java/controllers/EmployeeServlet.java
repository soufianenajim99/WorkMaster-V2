package controllers;

import entities.Admin;
import entities.Employee;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.AdminRepositoryImpl;
import repositories.EmployeeRepositoryImpl;
import repositories.repositoryinterfaces.AdminRepository;
import repositories.repositoryinterfaces.EmployeeRepository;
import services.AdminServiceImpl;
import services.EmployeeServiceImpl;
import services.serviceinterfaces.AdminService;
import services.serviceinterfaces.EmployeeService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/manageEmployees")
public class EmployeeServlet extends HttpServlet {

//    @Inject
//    private AdminService employeeService;
    EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
    EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);





    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> employees = employeeService.findAll();
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("views/admin/employees.jsp").forward(request, response);

    }
}

