package controllers;
import entities.Leave;
import enums.Status;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.CondidatureRepositoryImpl;
import repositories.LeaveRepositoryImpl;
import repositories.repositoryinterfaces.CondidatureRepository;
import repositories.repositoryinterfaces.LeaveRepository;
import services.CondidatureServiceImpl;
import services.LeaveServiceImpl;
import services.serviceinterfaces.CondidatureService;
import services.serviceinterfaces.LeaveService;

import java.io.IOException;
import java.util.UUID;
@WebServlet("/rejectLeave")
public class RejectLeaveServlet extends HttpServlet {

    LeaveRepository leaveRepository = new LeaveRepositoryImpl();
    LeaveService leaveService = new LeaveServiceImpl(leaveRepository);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String requestId = request.getParameter("requestId");
        Leave leave = leaveService.findById(UUID.fromString(requestId)).get();
        leave.setStatus(Status.REJECTED);  // Update status to 'Rejected'
        leaveService.update(leave);
        response.sendRedirect("/WorkMaster_V2_war_exploded/listLeaves");
    }
}
