package fa.academy.entity;

import fa.academy.utils.Enum.CandidateType;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fresher extends Candidate {

    private static List<String> columns = new ArrayList<>(
        Arrays.asList(
            "Id",
            "FullName",
            "Birthday",
            "Phone",
            "Email",
            "CandidateType",
            "Education",
            "GraduationRank",
            "GraduationDate",
            "CERF-ID",
            "CERF-Name",
            "CERF-Rank",
            "CERF-Date"
        )
    );

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

    @Override
    public List<String> getColumns() {
        return columns;
    }

    @Override
    public List<ArrayList<String>> getRecordData() {
        List<ArrayList<String>> recordList = new ArrayList<>();
        ArrayList<String> record;
        int i = 0;

        do {
            record = new ArrayList<>();
            if (i != 0) {
                record.addAll(
                    Arrays.asList(
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                    )
                );
            } else {
                record.add(getCandidateId());
                record.add(getFullname());
                record.add(getBirthday().toString());
                record.add(getPhone());
                record.add(getEmail());
                record.add(getCandidateType().toString());
                record.add(this.education);
                record.add(this.graduationRank);
                record.add(this.graduationDate.toString());
            }
            if (
                certificationList == null || certificationList.size() == 0
            ) break;
            record.add(certificationList.get(i).getCertificationId());
            record.add(certificationList.get(i).getCertificationName());
            record.add(certificationList.get(i).getCertificationRank());
            record.add(
                certificationList.get(i).getCertificationDate().toString()
            );

            recordList.add(record);
            if (++i == certificationList.size()) return recordList;
        } while (true);
        record.addAll(Arrays.asList(null, null, null, null));
        recordList.add(record);
        return recordList;
    }
}
