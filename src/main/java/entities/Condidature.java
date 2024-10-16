package entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import enums.Status;
import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "condidature")
public class Condidature {
    @Id
    @Column(name = "id", columnDefinition = "uuid")
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status ;

    // Relation to JobOffer
    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private JobOffer jobOffer;

    public Condidature() {
    }

    public Condidature(String name, String email, Status status, JobOffer jobOffer) {
        this.name = name;
        this.email = email;
        this.status = status;
        this.jobOffer = jobOffer;
    }

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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public JobOffer getJobOffer() {
        return jobOffer;
    }

    public void setJobOffer(JobOffer jobOffer) {
        this.jobOffer = jobOffer;
    }
}
