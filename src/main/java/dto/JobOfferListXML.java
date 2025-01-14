package dto;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "OffresEmploi")
@XmlAccessorType(XmlAccessType.FIELD)
public class JobOfferListXML {
    @XmlElement(name = "Offre")
    private List<JobOfferXML> offers;

    public List<JobOfferXML> getOffers() {
        return offers;
    }

    public void setOffers(List<JobOfferXML> offers) {
        this.offers = offers;
    }
}
