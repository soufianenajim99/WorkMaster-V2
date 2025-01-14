package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import utils.XMLFileLoader;
import utils.XSLTransformer;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
@WebServlet("/recruiterDashboard")
public class RecruiterDashboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            File xmlFile = XMLFileLoader.getXMLFile("condidatures/condidature.xml");
            File xslFile = XMLFileLoader.getXMLFile("condidatures/condidature.xsl");


            String htmlOutput = XSLTransformer.transformXML(xmlFile, xslFile);
            response.setContentType("text/html");
            response.getWriter().write(htmlOutput);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
