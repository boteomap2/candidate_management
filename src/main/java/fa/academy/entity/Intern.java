package fa.academy.entity;

import fa.academy.utils.Enum.CandidateType;
import fa.academy.utils.common.TablePrintable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Intern extends Candidate {

    private static List<String> columns = new ArrayList<>(
        Arrays.asList(
            "Id",
            "FullName",
            "Birthday",
            "Phone",
            "Email",
            "CandidateType",
            "University",
            "Major",
            "Semester",
            "CERF-ID",
            "CERF-Name",
            "CERF-Rank",
            "CERF-Date"
        )
    );
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
                record.add(this.university);
                record.add(this.major);
                record.add(Integer.toString(this.semester));
            }
            if (certificationList == null) break;
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
