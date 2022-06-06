package fa.academy.entity;

import fa.academy.utils.common.TablePrintable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Certification implements TablePrintable {

    private static List<String> columns = new ArrayList<>(
        Arrays.asList("CERF-ID", "CERF-Name", "CERF-Rank", "CERF-Date")
    );

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

    @Override
    public List<String> getColumns() {
        return columns;
    }

    @Override
    public List<ArrayList<String>> getRecordData() {
        List<ArrayList<String>> recordList = new ArrayList<>();
        ArrayList<String> record = new ArrayList<>();
        record.add(CertificationId);
        record.add(CertificationName);
        record.add(CertificationRank);
        record.add(CertificationDate.toString());
        recordList.add(record);
        return recordList;
    }
}
