package controllers;

import entities.Department;
import entities.Employee;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.DepartmentRepositoryImpl;
import repositories.EmployeeRepositoryImpl;
import repositories.repositoryinterfaces.DepartmentRepository;
import repositories.repositoryinterfaces.EmployeeRepository;
import services.DepartmentServiceImpl;
import services.EmployeeServiceImpl;
import services.serviceinterfaces.DepartmentService;
import services.serviceinterfaces.EmployeeService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

// SaveEmployeeServlet.java
@WebServlet("/saveEmployee")
public class SaveEmployeeServlet extends HttpServlet {
    EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
    EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);

    DepartmentRepository departmentRepository = new DepartmentRepositoryImpl();
    DepartmentService departmentService = new DepartmentServiceImpl(departmentRepository);




    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            UUID employeeId = UUID.fromString(id);
            // If an employee is found, add it to the request attributes
            employeeService.findById(employeeId).ifPresent(employee -> request.setAttribute("employee", employee));
        }

        // Fetch all departments and add them to the request attributes
        List<Department> departments = departmentService.findAll();
        request.setAttribute("departments", departments);

        // Forward to the employee form view
        request.getRequestDispatcher("views/admin/employeeForm.jsp").forward(request, response);
    }






    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String address = request.getParameter("address");
        String password = request.getParameter("password");
        double salary = Double.parseDouble(request.getParameter("salary"));
        LocalDate hireDate = LocalDate.parse(request.getParameter("hireDate"));
        LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dateOfBirth"));
        String socialSecurityNumber = request.getParameter("socialSecurityNumber");
        double performanceRating = Double.parseDouble(request.getParameter("performanceRating"));
        double leaveBalance = Double.parseDouble(request.getParameter("leaveBalance"));
        int childrenNumber = Integer.parseInt(request.getParameter("children_number"));
        String departmentId = request.getParameter("department.id");

        Department department = departmentService.findById(UUID.fromString(departmentId)).orElseThrow();

        Employee employee;
        if (id == null || id.isEmpty()) {
            // Adding a new employee
            employee = new Employee(address, password, username, department, salary, dateOfBirth, socialSecurityNumber, hireDate, performanceRating, leaveBalance, childrenNumber);
            employeeService.save(employee);
        } else {

            UUID employeeId = UUID.fromString(id);
            employee = employeeService.findById(employeeId).orElseThrow();
            employee.setUsername(username);
            employee.setAddress(address);
            employee.setPassword(password);
            employee.setSalary(salary);
            employee.setHireDate(hireDate);
            employee.setDateOfBirth(dateOfBirth);
            employee.setSocialSecurityNumber(socialSecurityNumber);
            employee.setPerformanceRating(performanceRating);
            employee.setLeaveBalance(leaveBalance);
            employee.setChildren_number(childrenNumber);
            employee.setDepartment(department);
            employeeService.update(employee);
        }

        // Redirect to the employees list page after saving or updating
        response.sendRedirect(request.getContextPath() + "/manageEmployees");
    }

}
