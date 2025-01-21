package services;

import dto.EmployeeListXML;
import dto.EmployeeXML;
import entities.Employee;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import repositories.repositoryinterfaces.EmployeeRepository;
import services.serviceinterfaces.EmployeeService;

import java.io.File;
import java.util.List;
import jakarta.inject.Inject;
import utils.XMLFileLoader;
import utils.XMLParser;
import utils.employeeXMLUtils.XMLWriter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@ApplicationScoped
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    private static final File XML_FILE = XMLFileLoader.getXMLFile("employees/employees.xml");

    @Inject
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee save(Employee employee) {
        Employee savedEmployee = employeeRepository.save(employee);

        try {
            // Parse the XML file
            EmployeeListXML employeeList = XMLParser.parseXML(XML_FILE, EmployeeListXML.class);

            // Convert employee to XML format and add it to the list
            EmployeeXML employeeXML = toXML(savedEmployee);
            employeeList.getEmployees().add(employeeXML);

            // Write updated list back to the XML file
            XMLWriter.writeXML(employeeList, XML_FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return savedEmployee;
    }

    @Override
    public Employee update(Employee employee) {
        Employee updatedEmployee = employeeRepository.update(employee);

        try {
            EmployeeListXML employeeList = XMLParser.parseXML(XML_FILE, EmployeeListXML.class);

            for (EmployeeXML employeeXML : employeeList.getEmployees()) {
                if (employeeXML.getId().equals(employee.getId())) {
                    employeeXML.setName(employee.getUsername());
                    employeeXML.setAddress(employee.getAddress());
                    employeeXML.setChildrenNumber(employee.getChildren_number());
                    employeeXML.setLeaveBalance(employee.getLeaveBalance());
                    employeeXML.setPerformanceRating(employee.getPerformanceRating());
                    employeeXML.setHireDate(employee.getHireDate());
                    employeeXML.setSocialSecurityNumber(employee.getSocialSecurityNumber());
                    employeeXML.setDateOfBirth(employee.getDateOfBirth());
                    employeeXML.setSalary(employee.getSalary());
                    employeeXML.setDepartment(employee.getDepartment().getName());
                    break;
                }
            }

            XMLWriter.writeXML(employeeList, XML_FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return updatedEmployee;
    }

    @Override
    public void deleteById(UUID id) {
        employeeRepository.deleteById(id);

        try {
            EmployeeListXML employeeList = XMLParser.parseXML(XML_FILE, EmployeeListXML.class);

            boolean removed = employeeList.getEmployees().removeIf(employee -> employee.getId().equals(id));
            if (removed) {
                XMLWriter.writeXML(employeeList, XML_FILE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Employee> findById(UUID id) {
        return employeeRepository.findById(id);
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    private EmployeeXML toXML(Employee employee) {
        EmployeeXML xml = new EmployeeXML();
        xml.setId(employee.getId());
        xml.setName(employee.getUsername());
        xml.setAddress(employee.getAddress());
        xml.setChildrenNumber(employee.getChildren_number());
        xml.setLeaveBalance(employee.getLeaveBalance());
        xml.setPerformanceRating(employee.getPerformanceRating());
        xml.setHireDate(employee.getHireDate());
        xml.setSocialSecurityNumber(employee.getSocialSecurityNumber());
        xml.setDateOfBirth(employee.getDateOfBirth());
        xml.setSalary(employee.getSalary());
        xml.setDepartment(employee.getDepartment().getName());
        return xml;
    }
}

