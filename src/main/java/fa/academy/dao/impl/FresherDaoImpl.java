package fa.academy.dao.impl;

import static fa.academy.config.DatabaseConfig.getConnection;

import fa.academy.dao.Dao;
import fa.academy.entity.Candidate;
import fa.academy.entity.Fresher;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.*;

public class FresherDaoImpl implements Dao<Fresher> {

    private static Logger logger = LogManager.getLogger(
        FresherDaoImpl.class.getName()
    );
    private static final String FIND_BY_ID =
        "SELECT * FROM Fresher WHERE FresherID = ?";
    private static final String FIND_ALL = "SELECT * FROM Fresher";

    private static final String UPDATE_BY_ID =
        "UPDATE Fresher SET Education = ?, GraduateRank = ?, GraduateDate = ? WHERE FresherID = ?";

    private static final String INSERT =
        "INSERT INTO Fresher VALUES (?, ?, ?, ?)";
    private CandidateDaoImpl cDaoImpl = CandidateDaoImpl.getInstance();
    private static final FresherDaoImpl F_DAO_IMPL = new FresherDaoImpl();

    private FresherDaoImpl() {
        super();
    }

    public static FresherDaoImpl getInstance() {
        return F_DAO_IMPL;
    }

    @Override
    public Fresher find(String id) {
        try (
            PreparedStatement pst = getConnection()
                .prepareStatement(FIND_BY_ID);
        ) {
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                return null;
            }

            Candidate candidate = cDaoImpl.find(id);
            Fresher fresher = new Fresher(candidate);
            fresher.setGraduationDate(rs.getDate("GraduateDate").toLocalDate());
            fresher.setGraduationRank(rs.getString("GraduateRank"));
            fresher.setEducation(rs.getString("Education"));
            rs.close();
            logger.info("Query data id " + id + " in Fresher table");
            return fresher;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    public Fresher find(Candidate candidate) {
        try (
            PreparedStatement pst = getConnection()
                .prepareStatement(FIND_BY_ID);
        ) {
            pst.setString(1, candidate.getCandidateId());
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                return null;
            }

            Fresher fresher = new Fresher(candidate);
            fresher.setGraduationDate(rs.getDate("GraduateDate").toLocalDate());
            fresher.setGraduationRank(rs.getString("GraduateRank"));
            fresher.setEducation(rs.getString("Education"));
            rs.close();
            logger.info(
                "Query data id " +
                fresher.getCandidateId() +
                " in Experience table"
            );
            return fresher;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<Fresher> findAll() {
        try (
            PreparedStatement pst = getConnection().prepareStatement(FIND_ALL);
            ResultSet rs = pst.executeQuery();
        ) {
            if (!rs.isBeforeFirst()) {
                return null;
            }

            ArrayList<Fresher> fresherList = new ArrayList<>();

            while (rs.next()) {
                String id = rs.getString("FresherID");
                Candidate candidate = cDaoImpl.find(id);
                Fresher fresher = new Fresher(candidate);
                fresher.setEducation(rs.getString("Education"));
                fresher.setGraduationRank(rs.getString("GraduateRank"));
                fresher.setGraduationDate(
                    rs.getDate("GraduateDate").toLocalDate()
                );

                fresherList.add(fresher);
            }
            logger.info("Query all data in Fresher table");
            return fresherList;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(Fresher fresher) {
        if (!cDaoImpl.save(fresher)) return false;
        try (PreparedStatement pst = getConnection().prepareStatement(INSERT)) {
            pst.setString(1, fresher.getCandidateId());
            pst.setString(2, fresher.getEducation());
            pst.setString(3, fresher.getGraduationRank());
            pst.setDate(4, Date.valueOf(fresher.getGraduationDate()));

            int count = pst.executeUpdate();

            if (count < 1) {
                System.out.println(
                    "Something errors, cann't insert into Fresher Table"
                );
                return false;
            }
            logger.info("Inserted " + count + " row into Fresher Table");
            System.out.println("Inserted " + count + " row into Fresher Table");
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public void saveBatch(List<Fresher> fresherList) {
        List<Candidate> candidateList = (List<Candidate>) (
            (List<?>) fresherList
        );
        cDaoImpl.saveBatch(candidateList);
        try (PreparedStatement pst = getConnection().prepareStatement(INSERT)) {
            getConnection().setAutoCommit(false);
            for (Fresher fresher : fresherList) {
                pst.setString(1, fresher.getCandidateId());
                pst.setString(2, fresher.getEducation());
                pst.setString(3, fresher.getGraduationRank());
                pst.setDate(4, Date.valueOf(fresher.getGraduationDate()));
                pst.addBatch();
            }

            int count[] = pst.executeBatch();

            if (count.length < 1) {
                System.out.println(
                    "Something errors, cann't insert into Fresher Table"
                );
                return;
            }
            getConnection().commit();
            logger.info("Inserted " + count.length + " row into Fresher Table");
            System.out.println(
                "Inserted " + count.length + " row into Fresher Table"
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
    public boolean update(Fresher fresher) {
        if (!cDaoImpl.update(fresher)) return false;
        try (
            PreparedStatement pst = getConnection()
                .prepareStatement(UPDATE_BY_ID)
        ) {
            pst.setString(1, fresher.getEducation());
            pst.setString(2, fresher.getGraduationRank());
            pst.setDate(3, Date.valueOf(fresher.getGraduationDate()));
            pst.setString(4, fresher.getCandidateId());

            int count = pst.executeUpdate();

            if (count < 1) {
                System.out.println(
                    "Something errors, cann't update Fresher Table"
                );
                return false;
            }
            logger.info("Updated " + count + " row into Fresher Table");
            System.out.println("Updated " + count + " row into Fresher Table");
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void delete(String id) {
        CandidateDaoImpl.getInstance().delete(id);
    }
}
