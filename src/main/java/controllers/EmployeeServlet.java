package controllers;

import entities.Admin;
import entities.Employee;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import repositories.AdminRepositoryImpl;
import repositories.EmployeeRepositoryImpl;
import repositories.repositoryinterfaces.AdminRepository;
import repositories.repositoryinterfaces.EmployeeRepository;
import services.AdminServiceImpl;
import services.EmployeeServiceImpl;
import services.serviceinterfaces.AdminService;
import services.serviceinterfaces.EmployeeService;
import utils.XMLFileLoader;
import utils.XSLTransformer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/manageEmployees")
public class EmployeeServlet extends HttpServlet {

//    @Inject
//    private AdminService employeeService;
    EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
    EmployeeService employeeService = new EmployeeServiceImpl(employeeRepository);





    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            File xmlFile = XMLFileLoader.getXMLFile("employees/employees.xml");
            File xslFile = XMLFileLoader.getXMLFile("employees/employees.xsl");


            String htmlOutput = XSLTransformer.transformXML(xmlFile, xslFile);
            response.setContentType("text/html");
            response.getWriter().write(htmlOutput);

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

