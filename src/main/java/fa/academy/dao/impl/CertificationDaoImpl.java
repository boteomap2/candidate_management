package fa.academy.dao.impl;

import static fa.academy.config.DatabaseConfig.getConnection;

import fa.academy.dao.Dao;
import fa.academy.entity.Certification;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.*;

public class CertificationDaoImpl implements Dao<Certification> {

    private static Logger logger = LogManager.getLogger(
        CandidateDaoImpl.class.getName()
    );

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

    private static final String INSERT =
        "INSERT INTO Certification VALUES (?, ?, ?, ?, ?)";
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
            logger.info("Query data id " + id + " in Certification table");
            return certification;
        } catch (Exception e) {
            logger.error(e.getMessage());
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
            logger.info("Query all data in Certification table");
            return certificationList;
        } catch (Exception e) {
            logger.error(e.getMessage());
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

            logger.info(
                "Query data with CID " + cId + " in Certification table"
            );
            return certificationList;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Deprecated
    @Override
    public boolean save(Certification t) {
        return false;
    }

    public boolean save(Certification cert, String cId) {
        try (PreparedStatement pst = getConnection().prepareStatement(INSERT)) {
            pst.setString(1, cert.getCertificationId());
            pst.setString(2, cert.getCertificationName());
            pst.setString(3, cert.getCertificationRank());
            pst.setDate(4, Date.valueOf(cert.getCertificationDate()));
            pst.setString(5, cId);

            int count = pst.executeUpdate();

            if (count < 1) {
                System.out.println(
                    "Something errors, cann't insert into Certification Table"
                );
                return false;
            }
            logger.info("Inserted " + count + " row into Certification Table");
            System.out.println(
                "Inserted " + count + " row into Certification Table"
            );
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void saveBatch(List<Certification> certList, String cId) {
        try (PreparedStatement pst = getConnection().prepareStatement(INSERT)) {
            getConnection().setAutoCommit(false);
            for (Certification cert : certList) {
                pst.setString(1, cert.getCertificationId());
                pst.setString(2, cert.getCertificationName());
                pst.setString(3, cert.getCertificationRank());
                pst.setDate(4, Date.valueOf(cert.getCertificationDate()));
                pst.setString(5, cId);
                pst.addBatch();
            }

            int count[] = pst.executeBatch();

            if (count.length < 1) {
                System.out.println(
                    "Something errors, cann't insert into Certification Table"
                );
                return;
            }
            logger.info(
                "Inserted " + count.length + " row into Certification Table"
            );
            System.out.println(
                "Inserted " + count.length + " row into Certification Table"
            );
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            try {
                getConnection().rollback();
            } catch (SQLException e1) {
                logger.error(e.getMessage());
                e1.printStackTrace();
            }
        } finally {
            try {
                getConnection().setAutoCommit(true);
            } catch (SQLException e) {
                logger.error(e.getMessage());
                e.printStackTrace();
            }
        }
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
            logger.info("Inserted " + count + " row into Certification Table");
            System.out.println(
                "Inserted " + count + " row into Certification Table"
            );
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
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
            logger.info("Deleted " + count + " row in Certification Table");
            System.out.println(
                "Deleted " + count + " row in Certification Table"
            );
        } catch (Exception e) {
            logger.error(e.getMessage());
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
            logger.info("Deleted " + count + " row in Certification Table");
            System.out.println(
                "Deleted " + count + " row in Certification Table"
            );
        } catch (Exception e) {
            logger.error(e.getMessage());
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
            logger.info(
                "Get last CertificationID: " + rs.getString("CertificationID")
            );
            return rs.getString("CertificationID");
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateWithResultSet(Certification cert) {
        try (
            PreparedStatement pst = getConnection()
                .prepareStatement(
                    FIND_BY_ID,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
                )
        ) {
            pst.setString(1, cert.getCertificationId());
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                return false;
            }
            rs.updateString("CertificationName", cert.getCertificationRank());
            rs.updateString("CertificationRank", cert.getCertificationRank());
            rs.updateDate(
                "CertificationDate",
                Date.valueOf(cert.getCertificationDate())
            );
            rs.updateRow();
            logger.info("Update certification with RS");
            System.out.println("Updated successfully");
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean saveWithResultSet(Certification cert, String cId) {
        try (
            PreparedStatement pst = getConnection()
                .prepareStatement(
                    FIND_ALL,
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
                );
            ResultSet rs = pst.executeQuery();
        ) {
            rs.moveToInsertRow();
            rs.updateString("CertificationID", cert.getCertificationId());
            rs.updateString("CertificationName", cert.getCertificationName());
            rs.updateString("CertificationRank", cert.getCertificationRank());
            rs.updateDate(
                "CertificationDate",
                Date.valueOf(cert.getCertificationDate())
            );
            rs.updateString("CandidateID", cId);
            rs.insertRow();
            logger.info("Insert certification with RS");
            System.out.println("Insert successfully");
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
