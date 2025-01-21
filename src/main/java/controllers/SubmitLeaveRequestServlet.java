package controllers;

import entities.Employee;
import entities.Leave;
import enums.Status;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.EmployeeRepositoryImpl;
import repositories.LeaveRepositoryImpl;
import repositories.repositoryinterfaces.EmployeeRepository;
import repositories.repositoryinterfaces.LeaveRepository;
import services.EmployeeServiceImpl;
import services.LeaveServiceImpl;
import services.serviceinterfaces.EmployeeService;
import services.serviceinterfaces.LeaveService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;
@WebServlet("/submitLeaveRequest")
public class SubmitLeaveRequestServlet extends HttpServlet {

    LeaveRepository leaveRepository = new LeaveRepositoryImpl();
    LeaveService leaveService = new LeaveServiceImpl(leaveRepository); // Assuming LeaveService is implemented
    EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
    EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward to JSP for adding leave request form
        request.getRequestDispatcher("views/employee/leaveForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve form data
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        String reason = request.getParameter("reason");

        // Get employeeId from session (assumed to be set during login)
        Employee employee = (Employee) request.getSession().getAttribute("user"); // Retrieve employee from session

        if (employee == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You must be logged in as an employee.");
            return;
        }

        // Parse the dates
        LocalDate startDate = LocalDate.parse(startDateStr);
        LocalDate endDate = LocalDate.parse(endDateStr);

        // Create a new Leave object
        Leave leaveRequest = new Leave();
        leaveRequest.setRequestId(UUID.randomUUID()); // Generate a unique request ID
        leaveRequest.setStartDate(startDate);
        leaveRequest.setEndDate(endDate);
        leaveRequest.setReason(reason);
        leaveRequest.setStatus(Status.IN_PROGRESS); // Default status for a new leave request
        leaveRequest.setEmployee(employee); // Set the employee who is requesting the leave

        try {
            // Save the leave request
            leaveService.save(leaveRequest);

            // Redirect to view leave requests page or confirmation page
            response.sendRedirect("listLeaves"); // Modify this URL as needed
        } catch (Exception e) {
            // Handle exception (e.g., database error)
            request.setAttribute("errorMessage", "There was an issue submitting your leave request.");
            request.getRequestDispatcher("views/employee/addLeaveRequest.jsp").forward(request, response);
        }
    }
}
