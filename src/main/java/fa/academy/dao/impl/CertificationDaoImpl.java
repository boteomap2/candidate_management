package fa.academy.dao.impl;

import static fa.academy.config.DatabaseConfig.getConnection;

import fa.academy.dao.Dao;
import fa.academy.entity.Certification;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CertificationDaoImpl implements Dao<Certification> {

    private static final String FIND_BY_ID =
        "SELECT * FROM Certification WHERE CertificationID = ?";
    private static final String FIND_ALL = "SELECT * FROM Certification";

    private static final String UPDATE_BY_ID =
        "UPDATE Certification SET CertificationName = ?, CertificationRank = ?, CertificationDate = ? WHERE CertificationID = ?";

    private static final String DELETE_BY_ID =
        "DELETE FROM Certification WHERE CertificationID = ?";

    private static final String FIND_BY_CID =
        "SELECT * FROM Certification WHERE CandidateID = ?";

    private static final String DELETE_BY_CID =
        "DELETE FROM Certification WHERE CandidateID = ?";

    private static final String GET_LAST_ID =
        "SELECT TOP(1) CertificationID FROM Certification ORDER BY CertificationID DESC";

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
    public boolean save(Certification t) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean update(Certification cerf) {
        try (
            PreparedStatement pst = getConnection()
                .prepareStatement(UPDATE_BY_ID)
        ) {
            pst.setString(1, cerf.getCertificationName());
            pst.setString(2, cerf.getCertificationRank());
            pst.setDate(3, Date.valueOf(cerf.getCertificationDate()));
            pst.setString(4, cerf.getCertificationId());

            int count = pst.executeUpdate();

            if (count < 1) {
                System.out.println(
                    "Something errors, cann't insert into Certification Table"
                );
                return false;
            }
            System.out.println(
                "Inserted " + count + " row into Certification Table"
            );
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void delete(String id) {
        try (
            PreparedStatement pst = getConnection()
                .prepareStatement(DELETE_BY_ID)
        ) {
            pst.setString(1, id);

            int count = pst.executeUpdate();

            if (count < 1) {
                System.out.println("CERF-ID is not found");
                return;
            }
            System.out.println(
                "Deleted " + count + " row in Certification Table"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteByCandidateId(String cId) {
        try (
            PreparedStatement pst = getConnection()
                .prepareStatement(DELETE_BY_CID)
        ) {
            pst.setString(1, cId);

            int count = pst.executeUpdate();

            if (count < 1) {
                System.out.println("CERF-ID is not found");
                return;
            }
            System.out.println(
                "Deleted " + count + " row in Certification Table"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getLastId() {
        try (
            PreparedStatement pst = getConnection()
                .prepareStatement(GET_LAST_ID);
            ResultSet rs = pst.executeQuery();
        ) {
            if (!rs.next()) {
                return "CERF000";
            }

            return rs.getString("CertificationID");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
