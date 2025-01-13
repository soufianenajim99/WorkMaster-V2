package dto;

import jakarta.xml.bind.annotation.*;
import java.util.List;

@XmlRootElement(name = "Condidatures")
@XmlAccessorType(XmlAccessType.FIELD)
public class CondidatureListXML {
    @XmlElement(name = "Condidature")
    private List<CondidatureXML> condidatures;

    public List<CondidatureXML> getCondidatures() {
        return condidatures;
    }

    public void setCondidatures(List<CondidatureXML> condidatures) {
        this.condidatures = condidatures;
    }
}

