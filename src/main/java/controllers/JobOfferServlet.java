package controllers;
import entities.JobOffer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import repositories.JobOfferRepositoryImpl;
import repositories.repositoryinterfaces.JobOfferRepository;
import services.AutenticationServiceImpl;
import services.JobOfferServiceImpl;
import services.serviceinterfaces.AuthenticationService;
import services.serviceinterfaces.JobOfferService;


import java.io.IOException;
import java.util.List;


@WebServlet("/jobOffers")
public class JobOfferServlet extends HttpServlet {
    JobOfferRepository jobOfferRepository = new JobOfferRepositoryImpl();
    JobOfferService jobOfferService = new JobOfferServiceImpl(jobOfferRepository);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<JobOffer> jobOffers = jobOfferService.findAll(); // Fetch job offers from the service
        request.setAttribute("jobOffers", jobOffers);
        request.getRequestDispatcher("/views/condidature/jobOffers.jsp").forward(request, response);
    }
}
