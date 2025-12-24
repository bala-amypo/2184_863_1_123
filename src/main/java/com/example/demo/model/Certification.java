package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "certifications")
public class Certification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String certificationName;

    private String issuingAuthority;

    private LocalDate validTill;

    private Boolean active = true;

    public Certification() {}

    public Certification(String certificationName, String issuingAuthority, LocalDate validTill) {
        this.certificationName = certificationName;
        this.issuingAuthority = issuingAuthority;
        this.validTill = validTill;
        this.active = true;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCertificationName() { return certificationName; }
    public void setCertificationName(String certificationName) { this.certificationName = certificationName; }

    public String getIssuingAuthority() { return issuingAuthority; }
    public void setIssuingAuthority(String issuingAuthority) { this.issuingAuthority = issuingAuthority; }

    public LocalDate getValidTill() { return validTill; }
    public void setValidTill(LocalDate validTill) { this.validTill = validTill; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
