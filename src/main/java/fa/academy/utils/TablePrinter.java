package fa.academy.utils;

import fa.academy.entity.Candidate;
import fa.academy.entity.Certification;
import fa.academy.utils.common.TableList;
import fa.academy.utils.common.TablePrintable;
import java.util.ArrayList;
import java.util.List;

public class TablePrinter {

    private Certification certification;
    private List<TablePrintable> list = new ArrayList<>();
    private TablePrintable candidate;

    public TablePrinter(Certification certification) {
        this.certification = certification;
        this.list.add(this.certification);
    }

    @SuppressWarnings("unchecked")
    public TablePrinter(List<?> candidateList) {
        this.list.addAll((List<TablePrintable>) candidateList);
    }

    public TablePrinter(TablePrintable candidate) {
        this.candidate = candidate;
        this.list.add(this.candidate);
    }

    public void print() {
        TableList tb = new TableList(
            list.get(0).getColumns().toArray(new String[0])
        )
            .withUnicode(true);

        for (TablePrintable candidate : list) {
            for (List<String> row : candidate.getRecordData()) {
                tb.addRow(row.toArray(String[]::new));
            }
        }
        tb.print();
    }
}
