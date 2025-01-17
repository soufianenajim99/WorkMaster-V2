package dto;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import utils.UUIDAdapter;

import java.util.UUID;
@XmlAccessorType(XmlAccessType.FIELD)
public class CondidatureXML {
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(UUIDAdapter.class)
    private UUID id;

    @XmlElement(name = "Name")
    private String name;

    @XmlElement(name = "Email")
    private String email;

    @XmlElement(name = "Status")
    private String status;

    @XmlElement(name = "JobOfferId")
    private String jobOfferId;

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJobOfferId() {
        return jobOfferId;
    }

    public void setJobOfferId(String jobOfferId) {
        this.jobOfferId = jobOfferId;
    }
}

