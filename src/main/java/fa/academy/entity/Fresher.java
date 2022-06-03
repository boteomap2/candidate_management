package fa.academy.entity;

import fa.academy.utils.Enum.CandidateType;
import java.time.LocalDate;
import java.util.ArrayList;

public class Fresher extends Candidate {

    private LocalDate graduationDate;
    private String graduationRank;
    private String education;

    public Fresher() {
        super();
    }

    public Fresher(Candidate candidate) {
        this.setCandidateId(candidate.getCandidateId());
        this.setFullname(candidate.getFullname());
        this.setBirthday(candidate.getBirthday());
        this.setPhone(candidate.getPhone());
        this.setEmail(candidate.getEmail());
        this.setCandidateType(candidate.getCandidateType());
        this.setCertificationList(candidate.getCertificationList());
    }

    public Fresher(
        String candidateId,
        String fullname,
        LocalDate birthday,
        String phone,
        String email,
        CandidateType candidateType,
        ArrayList<Certification> certificationList,
        LocalDate graduationDate,
        String graduationRank,
        String education
    ) {
        super(
            candidateId,
            fullname,
            birthday,
            phone,
            email,
            candidateType,
            certificationList
        );
        this.graduationDate = graduationDate;
        this.graduationRank = graduationRank;
        this.education = education;
    }

    public LocalDate getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(LocalDate graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getGraduationRank() {
        return graduationRank;
    }

    public void setGraduationRank(String graduationRank) {
        this.graduationRank = graduationRank;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Override
    public String showInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Experience [Id=");
        sb.append(getCandidateId());
        sb.append(", FullName=");
        sb.append(getFullname());
        sb.append(", Birthday=");
        sb.append(getBirthday());
        sb.append(", Phone=");
        sb.append(getPhone());
        sb.append(", Email=");
        sb.append(getEmail());
        sb.append(", CandidateType=");
        sb.append(getCandidateId());
        sb.append(", GraduationDate=");
        sb.append(graduationDate);
        sb.append(", GraduationRank");
        sb.append(graduationRank);
        sb.append(", Educaiton");
        sb.append(education);
        sb.append("]");
        if (certificationList != null) {
            for (Certification certification : certificationList) {
                sb.append(certification.toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
