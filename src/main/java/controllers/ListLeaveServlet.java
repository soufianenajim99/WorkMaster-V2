package controllers;
import dto.LeaveListXML;
import dto.LeaveXML;
import entities.Employee;
import entities.Leave;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;
import repositories.EmployeeRepositoryImpl;
import repositories.LeaveRepositoryImpl;
import repositories.repositoryinterfaces.EmployeeRepository;
import repositories.repositoryinterfaces.LeaveRepository;
import services.EmployeeServiceImpl;
import services.LeaveServiceImpl;
import services.serviceinterfaces.EmployeeService;
import services.serviceinterfaces.LeaveService;
import utils.XMLFileLoader;
import utils.XSLTransformer;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/listLeaves")
public class ListLeaveServlet extends HttpServlet {

    LeaveRepository leaveRepository = new LeaveRepositoryImpl();
    LeaveService leaveService = new LeaveServiceImpl(leaveRepository); // Assuming LeaveService is implemented
    EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
    EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);// Assuming EmployeeService is implemented

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the role from the session (admin, employee)
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        // Fetch all leaves (or only the user's leaves, depending on the role)
        List<Leave> leavesList = new ArrayList<>();
        if ("admin".equals(role)) {
            // Admin: fetch all leaves from all employees
            leavesList = leaveService.findAll();
        } else {
            // Employee: fetch only the current user's leaves
            Employee currentEmployee = (Employee) session.getAttribute("user");
            leavesList = leaveService.findByEmployeeId(currentEmployee.getId());
        }

        // Create an XML structure for leaves
        LeaveListXML leaveListXML = new LeaveListXML();
        for (Leave leave : leavesList) {
            LeaveXML leaveXML = new LeaveXML();
            leaveXML.setRequestId(leave.getRequestId());
            leaveXML.setStartDate(leave.getStartDate());
            leaveXML.setEndDate(leave.getEndDate());
            leaveXML.setReason(leave.getReason());
            leaveXML.setStatus(leave.getStatus().toString());
            leaveXML.setEmployeeId(leave.getEmployee().getId());
            leaveListXML.getLeaves().add(leaveXML);
        }


        try {
            ServletContext context = getServletContext();

            // Convert the leaveListXML object to an XML file for XSL transformation
            File xmlFile = XMLFileLoader.createXMLFileFromObject(leaveListXML, "leaves/leaves.xml",context);
            File xslFile = XMLFileLoader.getXMLFile("leaves/leaves.xsl");

            // Set up the XSLT transformer
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer(new StreamSource(xslFile));

            // Pass the role parameter to the XSLT to control the actions
            transformer.setParameter("role", role);

            // Perform the transformation and generate the HTML output
            StringWriter writer = new StringWriter();
            transformer.transform(new StreamSource(xmlFile), new StreamResult(writer));

            // Output the transformed HTML
            response.setContentType("text/html");
            response.getWriter().write(writer.toString());
        } catch (Exception e) {
            // Handle any exceptions during XML transformation or file creation
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("<h1>Error: Unable to load the leaves list.</h1>");
        }
    }
}
