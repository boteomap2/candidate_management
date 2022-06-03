package fa.academy.entity;

import fa.academy.utils.Enum.CandidateType;
import java.time.LocalDate;
import java.util.ArrayList;

public class Experience extends Candidate {

    private int expInYear;
    private String proSkill;

    public Experience() {
        super();
    }

    public Experience(Candidate candidate) {
        this.setCandidateId(candidate.getCandidateId());
        this.setFullname(candidate.getFullname());
        this.setBirthday(candidate.getBirthday());
        this.setPhone(candidate.getPhone());
        this.setEmail(candidate.getEmail());
        this.setCandidateType(candidate.getCandidateType());
        this.setCertificationList(candidate.getCertificationList());
    }

    public Experience(
        String candidateId,
        String fullname,
        LocalDate birthday,
        String phone,
        String email,
        CandidateType candidateType,
        ArrayList<Certification> certificationList,
        int expInYear,
        String proSkill
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
        this.expInYear = expInYear;
        this.proSkill = proSkill;
    }

    public int getExpInYear() {
        return expInYear;
    }

    public void setExpInYear(int expInYear) {
        this.expInYear = expInYear;
    }

    public String getProSkill() {
        return proSkill;
    }

    public void setProSkill(String proSkill) {
        this.proSkill = proSkill;
    }

    @Override
    public String toString() {
        return (
            "Experience [expInYear=" +
            expInYear +
            ", proSkill=" +
            proSkill +
            "]"
        );
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
        sb.append(", ExpInYear=");
        sb.append(expInYear);
        sb.append(", ProSkill=");
        sb.append(proSkill);
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
