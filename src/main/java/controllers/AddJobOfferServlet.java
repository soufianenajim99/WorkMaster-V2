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
@WebServlet("/addJobOffer")
public class AddJobOfferServlet extends HttpServlet {

JobOfferRepository jobOfferRepository = new JobOfferRepositoryImpl();
JobOfferService jobOfferService = new JobOfferServiceImpl(jobOfferRepository);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("views/recruiter/addJobOffer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jobTitle = request.getParameter("jobTitle");
        String jobDescription = request.getParameter("jobDescription");
        String validUntilStr = request.getParameter("validUntil");
        LocalDate postedDate = LocalDate.now(); // Current date
        LocalDate validUntil;
        try {
            validUntil = LocalDate.parse(validUntilStr);
        } catch (DateTimeParseException e) {
            // Handle parse exception, e.g., set an error message or redirect back to the form
            request.setAttribute("errorMessage", "Invalid date format. Please use yyyy-MM-dd.");
            request.getRequestDispatcher("views/recruiter/addJobOffer.jsp").forward(request, response);
            return;
        }
        JobOffer jobOffer = new JobOffer();
        jobOffer.setJobId(UUID.randomUUID());
        jobOffer.setJobTitle(jobTitle);
        jobOffer.setJobDescription(jobDescription);
        jobOffer.setStatus(Status.IN_PROGRESS);
        jobOffer.setPostedDate(postedDate);
        jobOffer.setValidUntil(validUntil);

        // Assuming you have a recruiter object retrieved from session
        jobOffer.setRecruiter((Recruiter) request.getSession().getAttribute("user"));

        jobOfferService.save(jobOffer);
        response.sendRedirect("listJobOffers");
    }}
