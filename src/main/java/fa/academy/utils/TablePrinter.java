package fa.academy.utils;

import fa.academy.entity.Candidate;
import fa.academy.utils.common.TableList;
import fa.academy.utils.common.TablePrintable;
import java.util.ArrayList;
import java.util.List;

public class TablePrinter {

    private List<TablePrintable> candidateList = new ArrayList<>();
    private TablePrintable candidate;

    public TablePrinter(List<? extends Candidate> candidateList) {
        this.candidateList.addAll(candidateList);
    }

    public TablePrinter(TablePrintable candidate) {
        this.candidate = candidate;
        this.candidateList.add(this.candidate);
    }

    public void print() {
        TableList tb = new TableList(
            candidateList.get(0).getColumns().toArray(new String[0])
        )
            .withUnicode(true);

        for (TablePrintable candidate : candidateList) {
            for (List<String> row : candidate.getRecordData()) {
                tb.addRow(row.toArray(String[]::new));
            }
        }
        tb.print();
    }
}
