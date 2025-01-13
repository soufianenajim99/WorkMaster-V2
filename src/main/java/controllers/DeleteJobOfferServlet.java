package controllers;
import entities.JobOffer;
import entities.Recruiter;
import enums.Status;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.JobOfferRepositoryImpl;
import repositories.repositoryinterfaces.JobOfferRepository;
import services.JobOfferServiceImpl;
import services.serviceinterfaces.JobOfferService;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.UUID;
@WebServlet("/deleteJobOffer")
public class DeleteJobOfferServlet extends HttpServlet {

    JobOfferRepository jobOfferRepository = new JobOfferRepositoryImpl();
    JobOfferService jobOfferService = new JobOfferServiceImpl(jobOfferRepository);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String jobId = request.getParameter("id");
        System.out.println(jobId);

        // Delete the job offer
        jobOfferService.deleteById(UUID.fromString(jobId));

        response.sendRedirect("listJobOffers");
    }
}

