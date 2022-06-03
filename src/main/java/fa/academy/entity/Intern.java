package fa.academy.entity;

import fa.academy.utils.Enum.CandidateType;
import java.time.LocalDate;
import java.util.ArrayList;

public class Intern extends Candidate {

    private String major;
    private int semester;
    private String university;

    public Intern() {
        super();
    }

    public Intern(Candidate candidate) {
        this.setCandidateId(candidate.getCandidateId());
        this.setFullname(candidate.getFullname());
        this.setBirthday(candidate.getBirthday());
        this.setPhone(candidate.getPhone());
        this.setEmail(candidate.getEmail());
        this.setCandidateType(candidate.getCandidateType());
        this.setCertificationList(candidate.getCertificationList());
    }

    public Intern(
        String candidateId,
        String fullname,
        LocalDate birthday,
        String phone,
        String email,
        CandidateType candidateType,
        ArrayList<Certification> certificationList,
        String major,
        int semester,
        String university
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
        this.major = major;
        this.semester = semester;
        this.university = university;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
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
        sb.append(", Major=");
        sb.append(major);
        sb.append(", Semester");
        sb.append(semester);
        sb.append(", University");
        sb.append(university);
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
