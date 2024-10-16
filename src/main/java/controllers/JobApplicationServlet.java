package controllers;

import entities.Condidature;
import entities.JobOffer;
import enums.Status;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.CondidatureRepositoryImpl;
import repositories.JobOfferRepositoryImpl;
import repositories.repositoryinterfaces.CondidatureRepository;
import repositories.repositoryinterfaces.JobOfferRepository;
import services.CondidatureServiceImpl;
import services.JobOfferServiceImpl;
import services.serviceinterfaces.CondidatureService;
import services.serviceinterfaces.JobOfferService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

@WebServlet("/applyJob")
public class JobApplicationServlet extends HttpServlet {

    CondidatureRepository condidatureRepository = new CondidatureRepositoryImpl();
     CondidatureService jobApplicationService = new CondidatureServiceImpl(condidatureRepository); // Service class for handling applications

    JobOfferRepository jobOfferRepository = new JobOfferRepositoryImpl();
    JobOfferService jobOfferService = new JobOfferServiceImpl(jobOfferRepository);


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jobId = request.getParameter("jobId");
        JobOffer jobOffer = jobOfferService.findById(UUID.fromString(jobId)).get();

        String name = request.getParameter("name");
        String email = request.getParameter("email");

        Condidature application = new Condidature();
        application.setId(UUID.randomUUID());
        application.setEmail(email);
        application.setName(name);
        application.setJobOffer(jobOffer);
        application.setStatus(Status.IN_PROGRESS);
        jobApplicationService.save(application);

        response.sendRedirect(request.getContextPath() + "/jobOffers"); // Redirect after applying
    }
}

