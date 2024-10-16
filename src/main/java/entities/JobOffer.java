package entities;
import enums.Status;
import jakarta.persistence.*;

import java.time.LocalDate;
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
    private LocalDate postedDate;

    @Column(name = "validuntil", nullable = false)
    private LocalDate validUntil;

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

    public LocalDate getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(LocalDate postedDate) {
        this.postedDate = postedDate;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDate validUntil) {
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
