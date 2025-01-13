package dto;
import jakarta.xml.bind.annotation.*;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import utils.UUIDAdapter;

import java.util.List;
import java.util.UUID;

@XmlAccessorType(XmlAccessType.FIELD)
public class JobOfferXML {
    @XmlAttribute(name = "id")
    @XmlJavaTypeAdapter(UUIDAdapter.class)
    private UUID id;

    @XmlElement(name = "Titre")
    private String title;

    @XmlElement(name = "Description")
    private String description;

    @XmlElement(name = "Statut")
    private String status;

    @XmlElement(name = "DatePublication")
    private String publicationDate;

    @XmlElement(name = "DateExpiration")
    private String expirationDate;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}
