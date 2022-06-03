package fa.academy.dao.impl;

import static fa.academy.config.DatabaseConfig.getConnection;

import fa.academy.dao.Dao;
import fa.academy.entity.Certification;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CertificationDaoImpl implements Dao<Certification> {

    private static final String FIND_BY_ID =
        "SELECT * FROM Certification WHERE CertificationID = ?";
    private static final String FIND_ALL = "SELECT * FROM Certification";

    private static final String FIND_BY_CID =
        "SELECT * FROM Certification WHERE CandidateID = ?";

    private static final CertificationDaoImpl CERT_DAO_IMPL = new CertificationDaoImpl();

    private CertificationDaoImpl() {
        super();
    }

    public static CertificationDaoImpl getInstance() {
        return CERT_DAO_IMPL;
    }

    @Override
    public Certification find(String id) {
        try (
            PreparedStatement pst = getConnection()
                .prepareStatement(FIND_BY_ID);
        ) {
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                return null;
            }

            Certification certification = new Certification();
            certification.setCertificationId(rs.getString("CertificationID"));
            certification.setCertificationName(
                rs.getString("CertificationName")
            );
            certification.setCertificationRank(
                rs.getString("CertificationRank")
            );
            certification.setCertificationDate(
                rs.getDate("CertificationDate").toLocalDate()
            );
            rs.close();
            return certification;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<Certification> findAll() {
        try (
            PreparedStatement pst = getConnection().prepareStatement(FIND_ALL);
            ResultSet rs = pst.executeQuery();
        ) {
            if (!rs.isBeforeFirst()) {
                return null;
            }

            ArrayList<Certification> certificationList = new ArrayList<>();

            while (rs.next()) {
                Certification certification = new Certification();
                certification.setCertificationId(
                    rs.getString("CertificationID")
                );
                certification.setCertificationName(
                    rs.getString("CertificationName")
                );
                certification.setCertificationRank(
                    rs.getString("CertificationRank")
                );
                certification.setCertificationDate(
                    rs.getDate("CertificationDate").toLocalDate()
                );

                certificationList.add(certification);
            }

            return certificationList;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Certification> findByCandidateId(String cId) {
        try (
            PreparedStatement pst = getConnection()
                .prepareStatement(FIND_BY_CID);
        ) {
            pst.setString(1, cId);
            ResultSet rs = pst.executeQuery();

            if (!rs.isBeforeFirst()) {
                return null;
            }

            ArrayList<Certification> certificationList = new ArrayList<>();

            while (rs.next()) {
                Certification certification = new Certification();
                certification.setCertificationId(
                    rs.getString("CertificationID")
                );
                certification.setCertificationName(
                    rs.getString("CertificationName")
                );
                certification.setCertificationRank(
                    rs.getString("CertificationRank")
                );
                certification.setCertificationDate(
                    rs.getDate("CertificationDate").toLocalDate()
                );

                certificationList.add(certification);
            }

            return certificationList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Certification t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Certification t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(String t) {
        // TODO Auto-generated method stub

    }
}
