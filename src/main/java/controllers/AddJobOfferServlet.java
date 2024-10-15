package controllers;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;
@WebServlet("/addJobOffer")
public class AddJobOfferServlet extends HttpServlet {
    private JobOfferServiceInterface jobOfferService = new JobOfferService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("views/recruiter/addJobOffer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String jobTitle = request.getParameter("jobTitle");
        String jobDescription = request.getParameter("jobDescription");
        String status = request.getParameter("status");
        Date postedDate = new Date();
        Date validUntil = new Date(request.getParameter("validUntil"));

        JobOffer jobOffer = new JobOffer();
        jobOffer.setJobId(UUID.randomUUID());
        jobOffer.setJobTitle(jobTitle);
        jobOffer.setJobDescription(jobDescription);
        jobOffer.setStatus(Status.valueOf(status));
        jobOffer.setPostedDate(postedDate);
        jobOffer.setValidUntil(validUntil);

        // Assuming you have a recruiter object retrieved from session
        jobOffer.setRecruiter((Recruiter) request.getSession().getAttribute("user"));

        jobOfferService.addJobOffer(jobOffer);
        response.sendRedirect("listJobOffers");
    }}
