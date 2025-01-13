package controllers;

import entities.Condidature;
import enums.Status;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.CondidatureRepositoryImpl;
import repositories.repositoryinterfaces.CondidatureRepository;
import services.CondidatureServiceImpl;
import services.serviceinterfaces.CondidatureService;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/acceptCandidate")
public class AcceptCandidateServlet extends HttpServlet {

    CondidatureRepository candidateRepository = new CondidatureRepositoryImpl();
    CondidatureService candidateService = new CondidatureServiceImpl(candidateRepository);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        String candidateId = request.getParameter("candidateId");

        // Fetch the candidate by ID
        Condidature candidate = candidateService.findById(UUID.fromString(candidateId)).orElseThrow(() -> new ServletException("Candidate not found"));

        // Update the candidate status to "Accepted"
        candidate.setStatus(Status.ACCEPTED);
        candidateService.update(candidate);

        response.sendRedirect("recruiterDashboard");
    }
}

