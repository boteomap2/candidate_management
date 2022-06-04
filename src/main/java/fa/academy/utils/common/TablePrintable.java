package fa.academy.utils.common;

import java.util.ArrayList;
import java.util.List;

public interface TablePrintable {
    public List<String> getColumns();

    public List<ArrayList<String>> getRecordData();
}
