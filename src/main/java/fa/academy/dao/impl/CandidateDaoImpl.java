package fa.academy.dao.impl;

import static fa.academy.config.DatabaseConfig.getConnection;

import fa.academy.dao.Dao;
import fa.academy.entity.Candidate;
import fa.academy.entity.Certification;
import fa.academy.utils.Enum.CandidateType;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.*;

public class CandidateDaoImpl implements Dao<Candidate> {

    private static Logger logger = LogManager.getLogger(
        CandidateDaoImpl.class.getName()
    );

    private static final String FIND_BY_ID =
        "SELECT * FROM Candidate WHERE CandidateID = ?";

    private static final String FIND_ALL = "SELECT * FROM Candidate";

    private static final String DELETE_BY_ID =
        "DELETE FROM Candidate WHERE CandidateID = ?";

    private static final String UPDATE_BY_ID =
        "UPDATE Candidate SET FullName = ?, Birthday = ?, Phone = ?, Email = ? WHERE CandidateID = ?";

    private static final String GET_LAST_ID =
        "SELECT TOP(1) CandidateID FROM Candidate ORDER BY CandidateID DESC";

    private static final String INSERT =
        "INSERT INTO Candidate VALUES (?, ?, ?, ?, ?, ?)";

    private static final String COUNT =
        "SELECT COUNT(CandidateID) as [Count] FROM Candidate";

    private static final CandidateDaoImpl C_DAO_IMPL = new CandidateDaoImpl();

    private CandidateDaoImpl() {
        super();
    }

    public static CandidateDaoImpl getInstance() {
        return C_DAO_IMPL;
    }

    @Override
    public Candidate find(String id) {
        try (
            PreparedStatement pst = getConnection()
                .prepareStatement(FIND_BY_ID);
        ) {
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                return null;
            }

            Candidate candidate = new Candidate();
            candidate.setCandidateId(id);
            candidate.setFullname(rs.getString("FullName"));
            candidate.setBirthday(rs.getDate("BirthDay").toLocalDate());
            candidate.setPhone(rs.getString("Phone"));
            candidate.setEmail(rs.getString("Email"));
            candidate.setCandidateType(
                CandidateType.values()[rs.getInt("CandidateType")]
            );

            ArrayList<Certification> certificationList = CertificationDaoImpl
                .getInstance()
                .findByCandidateId(id);
            candidate.setCertificationList(certificationList);

            rs.close();
            logger.info("Query data id " + id + " in Candidate table");
            return candidate;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Candidate> findAll() {
        try (
            PreparedStatement pst = getConnection().prepareStatement(FIND_ALL);
            ResultSet rs = pst.executeQuery();
        ) {
            if (!rs.isBeforeFirst()) {
                return null;
            }

            ArrayList<Candidate> candidateList = new ArrayList<>();

            while (rs.next()) {
                Candidate candidate = new Candidate();
                candidate.setCandidateId(rs.getString("CandidateID"));
                candidate.setFullname(rs.getString("FullName"));
                candidate.setBirthday(rs.getDate("BirthDay").toLocalDate());
                candidate.setPhone(rs.getString("Phone"));
                candidate.setEmail(rs.getString("Email"));
                candidate.setCandidateType(
                    CandidateType.values()[rs.getInt("CandidateType")]
                );
                ArrayList<Certification> certificationList = CertificationDaoImpl
                    .getInstance()
                    .findByCandidateId(candidate.getCandidateId());
                candidate.setCertificationList(certificationList);

                candidateList.add(candidate);
            }
            logger.info("Query all data in Candidate table");
            return candidateList;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(Candidate candidate) {
        try (
            PreparedStatement pst = getConnection().prepareStatement(INSERT);
        ) {
            pst.setString(1, candidate.getCandidateId());
            pst.setString(2, candidate.getFullname());
            pst.setDate(3, Date.valueOf(candidate.getBirthday()));
            pst.setString(4, candidate.getPhone());
            pst.setString(5, candidate.getEmail());
            pst.setInt(6, candidate.getCandidateType().getNumber());

            int count = pst.executeUpdate();

            if (count < 1) throw new Exception(
                "Error on Insert new row Candidate Table"
            );
            logger.info("Inserted " + count + " row to Candidate table");
            System.out.println("Inserted " + count + " row to Candidate table");
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void saveBatch(List<Candidate> candidateList) {
        try (
            PreparedStatement pst = getConnection().prepareStatement(INSERT);
        ) {
            getConnection().setAutoCommit(false);
            Map<String, List<Certification>> map = new HashMap<>();
            for (Candidate candidate : candidateList) {
                if (candidate.getCertificationList().size() != 0) {
                    map.put(
                        candidate.getCandidateId(),
                        candidate.getCertificationList()
                    );
                }
                pst.setString(1, candidate.getCandidateId());
                pst.setString(2, candidate.getFullname());
                pst.setDate(3, Date.valueOf(candidate.getBirthday()));
                pst.setString(4, candidate.getPhone());
                pst.setString(5, candidate.getEmail());
                pst.setInt(6, candidate.getCandidateType().getNumber());

                pst.addBatch();
            }

            int count[] = pst.executeBatch();

            if (count.length < 1) throw new Exception(
                "Error on Insert new row Candidate Table"
            );
            logger.info("Inserted " + count.length + " row to Candidate table");
            System.out.println(
                "Inserted " + count.length + " row to Candidate table"
            );

            for (String id : map.keySet()) {
                List<Certification> certList = map.get(id);
                CertificationDaoImpl.getInstance().saveBatch(certList, id);
            }
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
    public boolean update(Candidate candidate) {
        try (
            PreparedStatement pst = getConnection()
                .prepareStatement(UPDATE_BY_ID)
        ) {
            pst.setString(1, candidate.getFullname());
            pst.setDate(2, Date.valueOf(candidate.getBirthday()));
            pst.setString(3, candidate.getPhone());
            pst.setString(4, candidate.getEmail());
            pst.setString(5, candidate.getCandidateId());

            int count = pst.executeUpdate();

            if (count < 1) {
                System.out.println(
                    "Something errors, cann't update Candidate Table"
                );
                return false;
            }
            logger.info("Updated " + count + " row into Candidate Table");
            System.out.println(
                "Updated " + count + " row into Candidate Table"
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
                .prepareStatement(DELETE_BY_ID);
        ) {
            pst.setString(1, id);
            int count = pst.executeUpdate();

            if (count < 1) {
                System.out.println("Id is not found, no row is deleted.");
                return;
            }
            logger.info("The candidate with ID: " + id + " is deleted");
            System.out.println("The candidate with ID: " + id + " is deleted");
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
                logger.info("Get last candidate ID: CDD000");
                return "CDD000";
            }
            logger.info(
                "Get last candidate ID: " + rs.getString("CandidateID")
            );
            return rs.getString("CandidateID");
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public int getCount() {
        try (
            PreparedStatement pst = getConnection().prepareStatement(COUNT);
            ResultSet rs = pst.executeQuery();
        ) {
            if (!rs.next()) {
                return 0;
            }
            logger.info("Get candidate count: " + rs.getInt("Count"));
            return rs.getInt("Count");
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }
}
