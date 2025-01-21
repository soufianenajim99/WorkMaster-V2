package dto;
import jakarta.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@XmlRootElement(name = "Leaves")
@XmlAccessorType(XmlAccessType.FIELD)
public class LeaveListXML {

    @XmlElement(name = "Leave")
    private List<LeaveXML> leaves = new ArrayList<>();

    // Getters and Setters
    public List<LeaveXML> getLeaves() {
        return leaves;
    }

    public void setLeaves(List<LeaveXML> leaves) {
        this.leaves = leaves;
    }
}
