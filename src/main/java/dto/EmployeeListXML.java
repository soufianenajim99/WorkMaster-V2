package dto;
import jakarta.xml.bind.annotation.*;
import java.util.List;
@XmlRootElement(name = "Employees")
@XmlAccessorType(XmlAccessType.FIELD)
public class EmployeeListXML {

    @XmlElement(name = "Employee")
    private List<EmployeeXML> employees;

    public List<EmployeeXML> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeXML> employees) {
        this.employees = employees;
    }
}
