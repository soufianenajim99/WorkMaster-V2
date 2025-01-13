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
@WebServlet("/editJobOffer")
public class EditJobOfferServlet extends HttpServlet {

    JobOfferRepository jobOfferRepository = new JobOfferRepositoryImpl();
    JobOfferService jobOfferService = new JobOfferServiceImpl(jobOfferRepository);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jobId = request.getParameter("id");

        // Fetch the job offer by ID
        JobOffer jobOffer = jobOfferService.findById(UUID.fromString(jobId)).get();

        // Set the job offer as a request attribute to pre-fill the edit form
        request.setAttribute("jobOffer", jobOffer);
        request.getRequestDispatcher("views/recruiter/editJobOffer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jobId = request.getParameter("id");
        String jobTitle = request.getParameter("jobTitle");
        String jobDescription = request.getParameter("jobDescription");
        String validUntilStr = request.getParameter("validUntil");
        LocalDate validUntil;

        try {
            validUntil = LocalDate.parse(validUntilStr);
        } catch (DateTimeParseException e) {
            request.setAttribute("errorMessage", "Invalid date format. Please use yyyy-MM-dd.");
            request.getRequestDispatcher("views/recruiter/editJobOffer.jsp").forward(request, response);
            return;
        }

        // Retrieve and update the job offer
        JobOffer jobOffer = jobOfferService.findById(UUID.fromString(jobId)).get();

        jobOffer.setJobTitle(jobTitle);
        jobOffer.setJobDescription(jobDescription);
        jobOffer.setValidUntil(validUntil);

        // Save updated job offer
        jobOfferService.update(jobOffer);

        response.sendRedirect("listJobOffers");
    }
}
