package fa.academy.utils;

import fa.academy.dao.impl.CandidateDaoImpl;
import fa.academy.dao.impl.CertificationDaoImpl;

public class IdGenerator {

    public static String generateCddId() {
        String idString = CandidateDaoImpl.getInstance().getLastId();
        if (idString == null) return null;
        int idNumber = Integer.parseInt(idString.substring(3)) + 1;
        return String.format("CDD%03d", idNumber);
    }

    public static String generateCerfId() {
        String idString = CertificationDaoImpl.getInstance().getLastId();
        if (idString == null) return null;
        int idNumber = Integer.parseInt(idString.substring(4)) + 1;
        return String.format("CERF%03d", idNumber);
    }
}
