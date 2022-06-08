package fa.academy.dao.impl;

import static fa.academy.config.DatabaseConfig.getConnection;

import fa.academy.dao.Dao;
import fa.academy.entity.Candidate;
import fa.academy.entity.Experience;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.*;

public class ExperienceDaoImpl implements Dao<Experience> {

    private static Logger logger = LogManager.getLogger(
        ExperienceDaoImpl.class.getName()
    );

    private static final String FIND_BY_ID =
        "SELECT * FROM Experience WHERE ExperienceID = ?";

    private static final String FIND_ALL = "SELECT * FROM Experience";

    private static final String UPDATE_BY_ID =
        "UPDATE Experience SET ExpInYear = ?, ProSkill = ? WHERE ExperienceID = ?";

    private static final String INSERT =
        "INSERT INTO Experience VALUES (?, ?, ?)";

    private static final ExperienceDaoImpl E_DAO_IMPL = new ExperienceDaoImpl();
    private CandidateDaoImpl cDaoImpl = CandidateDaoImpl.getInstance();

    private ExperienceDaoImpl() {
        super();
    }

    public static ExperienceDaoImpl getInstance() {
        return E_DAO_IMPL;
    }

    @Override
    public Experience find(String id) {
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
            Experience experience = new Experience(candidate);
            experience.setExpInYear(rs.getInt("ExpInYear"));
            experience.setProSkill(rs.getString("ProSkill"));
            rs.close();
            logger.info("Query data id " + id + " in Experience table");
            return experience;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public Experience find(Candidate candidate) {
        try (
            PreparedStatement pst = getConnection()
                .prepareStatement(FIND_BY_ID);
        ) {
            pst.setString(1, candidate.getCandidateId());
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                return null;
            }
            Experience experience = new Experience(candidate);
            experience.setExpInYear(rs.getInt("ExpInYear"));
            experience.setProSkill(rs.getString("ProSkill"));
            rs.close();
            logger.info(
                "Query data id " +
                experience.getCandidateId() +
                " in Experience table"
            );
            return experience;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Experience> findAll() {
        try (
            PreparedStatement pst = getConnection().prepareStatement(FIND_ALL);
            ResultSet rs = pst.executeQuery();
        ) {
            if (!rs.isBeforeFirst()) {
                return null;
            }

            ArrayList<Experience> experienceList = new ArrayList<>();

            while (rs.next()) {
                String id = rs.getString("ExperienceID");
                Candidate candidate = cDaoImpl.find(id);
                Experience experience = new Experience(candidate);
                experience.setExpInYear(rs.getInt("ExpInYear"));
                experience.setProSkill(rs.getString("ProSkill"));

                experienceList.add(experience);
            }
            logger.info("Query all data in Experience table");
            return experienceList;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(Experience experience) {
        if (!cDaoImpl.save(experience)) return false;
        try (PreparedStatement pst = getConnection().prepareStatement(INSERT)) {
            pst.setString(1, experience.getCandidateId());
            pst.setInt(2, experience.getExpInYear());
            pst.setString(3, experience.getProSkill());

            int count = pst.executeUpdate();

            if (count < 1) {
                System.out.println(
                    "Something errors, cann't insert into Experience Table"
                );
                return false;
            }
            logger.info("Inserted " + count + " row into Experience Table");
            System.out.println(
                "Inserted " + count + " row into Experience Table"
            );
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public void saveBatch(List<Experience> experienceList) {
        List<Candidate> candidateList = (List<Candidate>) (
            (List<?>) experienceList
        );
        cDaoImpl.saveBatch(candidateList);
        try (PreparedStatement pst = getConnection().prepareStatement(INSERT)) {
            getConnection().setAutoCommit(false);
            for (Experience experience : experienceList) {
                pst.setString(1, experience.getCandidateId());
                pst.setInt(2, experience.getExpInYear());
                pst.setString(3, experience.getProSkill());
                pst.addBatch();
            }

            int count[] = pst.executeBatch();

            if (count.length < 1) {
                System.out.println(
                    "Something errors, cann't insert into Experience Table"
                );
                return;
            }
            getConnection().commit();
            logger.info(
                "Inserted " + count.length + " row into Experience Table"
            );
            System.out.println(
                "Inserted " + count.length + " row into Experience Table"
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
    public boolean update(Experience experience) {
        if (!cDaoImpl.update(experience)) return false;
        try (
            PreparedStatement pst = getConnection()
                .prepareStatement(UPDATE_BY_ID)
        ) {
            pst.setInt(1, experience.getExpInYear());
            pst.setString(2, experience.getProSkill());
            pst.setString(3, experience.getCandidateId());

            int count = pst.executeUpdate();

            if (count < 1) {
                System.out.println(
                    "Something errors, cann't update Experience Table"
                );
                return false;
            }
            logger.info("Updated " + count + " row into Experience Table");
            System.out.println(
                "Updated " + count + " row into Experience Table"
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
        CandidateDaoImpl.getInstance().delete(id);
    }
}
