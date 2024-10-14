package entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "recruiters")
public class Recruiter extends User {
    @OneToMany(mappedBy = "recruiter", cascade = CascadeType.ALL)
    private List<JobOffer> jobOffers;
    public Recruiter() {

    }

    public Recruiter(UUID id, String username, String password, String address) {
        super(id, username, password, address);
    }

    public Recruiter(String address, String password, String username) {
        super(address, password, username);
    }
    public List<JobOffer> getJobOffers() {
        return jobOffers;
    }

    public void setJobOffers(List<JobOffer> jobOffers) {
        this.jobOffers = jobOffers;
    }
}
