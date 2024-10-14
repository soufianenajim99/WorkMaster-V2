package entities;
import enums.Status;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "job_offer")
public class JobOffer {
    @Id
    @Column(name = "jobid", columnDefinition = "uuid")
    private UUID jobId;

    @Column(name = "jobtitle", nullable = false)
    private String jobTitle;

    @Column(name = "jobdescription", nullable = false)
    private String jobDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @Column(name = "posteddate", nullable = false)
    private Date postedDate;

    @Column(name = "validuntil", nullable = false)
    private Date validUntil;

    // Relation to Condidature
    @OneToMany(mappedBy = "jobOffer", cascade = CascadeType.ALL)
    private List<Condidature> condidatures;

    @ManyToOne
    @JoinColumn(name = "recruiter_id", nullable = false)
    private Recruiter recruiter;
    // Getters and Setters
    public UUID getJobId() {
        return jobId;
    }

    public void setJobId(UUID jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(Date postedDate) {
        this.postedDate = postedDate;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public List<Condidature> getCondidatures() {
        return condidatures;
    }

    public void setCondidatures(List<Condidature> condidatures) {
        this.condidatures = condidatures;
    }

    public void setRecruiter(Recruiter recruiter) {
        this.recruiter = recruiter;
    }
}
