package fa.academy.entity;

import java.time.LocalDate;

public class Certification {

    private String CertificationId;
    private String CertificationName;
    private String CertificationRank;
    private LocalDate CertificationDate;

    public Certification() {
        super();
    }

    public Certification(
        String certificationId,
        String certificationName,
        String certificationRank,
        LocalDate certificationDate
    ) {
        CertificationId = certificationId;
        CertificationName = certificationName;
        CertificationRank = certificationRank;
        CertificationDate = certificationDate;
    }

    public String getCertificationId() {
        return CertificationId;
    }

    public void setCertificationId(String certificationId) {
        CertificationId = certificationId;
    }

    public String getCertificationName() {
        return CertificationName;
    }

    public void setCertificationName(String certificationName) {
        CertificationName = certificationName;
    }

    public String getCertificationRank() {
        return CertificationRank;
    }

    public void setCertificationRank(String certificationRank) {
        CertificationRank = certificationRank;
    }

    public LocalDate getCertificationDate() {
        return CertificationDate;
    }

    public void setCertificationDate(LocalDate certificationDate) {
        CertificationDate = certificationDate;
    }

    @Override
    public String toString() {
        return (
            "Certification [CertificationId=" +
            CertificationId +
            ", CertificationName=" +
            CertificationName +
            ", CertificationRank=" +
            CertificationRank +
            ", CertificationDate=" +
            CertificationDate +
            "]"
        );
    }
}
