package fa.academy.entity;

import fa.academy.utils.Enum.CandidateType;
import fa.academy.utils.common.TablePrintable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Candidate implements TablePrintable {

    public static int candidateCount = 0;

    private String candidateId;
    private String fullname;
    private LocalDate birthday;
    private String phone;
    private String email;
    private CandidateType candidateType;
    ArrayList<Certification> certificationList;

    public Candidate() {
        super();
    }

    public Candidate(
        String candidateId,
        String fullname,
        LocalDate birthday,
        String phone,
        String email,
        CandidateType candidateType,
        ArrayList<Certification> certificationList
    ) {
        this.candidateId = candidateId;
        this.fullname = fullname;
        this.birthday = birthday;
        this.phone = phone;
        this.email = email;
        this.candidateType = candidateType;
        this.certificationList = certificationList;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public CandidateType getCandidateType() {
        return candidateType;
    }

    public void setCandidateType(CandidateType candidateType) {
        this.candidateType = candidateType;
    }

    public ArrayList<Certification> getCertificationList() {
        return certificationList;
    }

    public void setCertificationList(
        ArrayList<Certification> certificationList
    ) {
        this.certificationList = certificationList;
    }

    public String showInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("Experience [Id=");
        sb.append(candidateId);
        sb.append(", FullName=");
        sb.append(fullname);
        sb.append(", Birthday=");
        sb.append(birthday);
        sb.append(", Phone=");
        sb.append(phone);
        sb.append(", Email=");
        sb.append(email);
        sb.append(", CandidateType=");
        sb.append(candidateType);
        sb.append("]\n");

        if (certificationList != null) {
            for (Certification certification : certificationList) {
                sb.append(certification.toString());
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    @Override
    public List<String> getColumns() {
        /* Do no thing */
        return null;
    }

    @Override
    public List<ArrayList<String>> getRecordData() {
        /* Do no thing */
        return null;
    }
}
