package controllers;

import entities.JobOffer;
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
import java.util.List;

@WebServlet("/listJobOffers")
public class ListJobOffersServlet extends HttpServlet {
    JobOfferRepository jobOfferRepository = new JobOfferRepositoryImpl();
    JobOfferService jobOfferService = new JobOfferServiceImpl(jobOfferRepository);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<JobOffer> jobOffers = jobOfferService.findAll();
        request.setAttribute("jobOffers", jobOffers);
        request.getRequestDispatcher("views/recruiter/listJobOffers.jsp").forward(request, response);
    }
}
