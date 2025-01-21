package services;

import dto.LeaveListXML;
import dto.LeaveXML;
import entities.Leave;
import jakarta.transaction.Transactional;
import repositories.repositoryinterfaces.LeaveRepository;
import services.serviceinterfaces.LeaveService;
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
import utils.leaveXMLUtils.XMLWriter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
public class LeaveServiceImpl implements LeaveService {

    private final LeaveRepository leaveRepository;

    private static final File XML_FILE = XMLFileLoader.getXMLFile("leaves/leaves.xml");

    @Inject
    public LeaveServiceImpl(LeaveRepository leaveRepository) {
        this.leaveRepository = leaveRepository;
    }

    @Override
    @Transactional
    public Leave save(Leave leave) {
        Leave savedLeave = leaveRepository.save(leave);

        try {
            // Parse the XML file
            LeaveListXML leaveList = XMLParser.parseXML(XML_FILE, LeaveListXML.class);

            // Convert leave to XML format and add it to the list
            LeaveXML leaveXML = toXML(savedLeave);
            leaveList.getLeaves().add(leaveXML);

            // Write updated list back to the XML file
            XMLWriter.writeXML(leaveList, XML_FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return savedLeave;
    }

    @Override
    @Transactional
    public Leave update(Leave leave) {
        Leave updatedLeave = leaveRepository.update(leave);

        try {
            // Parse the XML file
            LeaveListXML leaveList = XMLParser.parseXML(XML_FILE, LeaveListXML.class);

            // Find and update the leave in the XML list
            for (LeaveXML leaveXML : leaveList.getLeaves()) {
                if (leaveXML.getRequestId().equals(leave.getRequestId())) {
                    leaveXML.setStartDate(leave.getStartDate());
                    leaveXML.setEndDate(leave.getEndDate());
                    leaveXML.setReason(leave.getReason());
                    leaveXML.setStatus(leave.getStatus().name());
                    leaveXML.setEmployeeId(leave.getEmployee().getId());
                    break;
                }
            }

            // Write updated list back to the XML file
            XMLWriter.writeXML(leaveList, XML_FILE);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return updatedLeave;
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        leaveRepository.deleteById(id);

        try {
            // Parse the XML file
            LeaveListXML leaveList = XMLParser.parseXML(XML_FILE, LeaveListXML.class);

            // Remove the leave from the XML list
            boolean removed = leaveList.getLeaves().removeIf(leave -> leave.getRequestId().equals(id));
            if (removed) {
                // Write updated list back to the XML file
                XMLWriter.writeXML(leaveList, XML_FILE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<Leave> findById(UUID id) {
        return leaveRepository.findById(id);
    }

    @Override
    public List<Leave> findByEmployeeId(UUID employeeId) {
        return leaveRepository.findByEmployeeId(employeeId); // Delegate to the repository
    }

    @Override
    public List<Leave> findAll() {
        return leaveRepository.findAll();
    }

    private LeaveXML toXML(Leave leave) {
        LeaveXML xml = new LeaveXML();
        xml.setRequestId(leave.getRequestId());
        xml.setStartDate(leave.getStartDate());
        xml.setEndDate(leave.getEndDate());
        xml.setReason(leave.getReason());
        xml.setStatus(leave.getStatus().name());
        xml.setEmployeeId(leave.getEmployee().getId());
        return xml;
    }
}
