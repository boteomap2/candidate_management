package fa.academy.dao.impl;

import static fa.academy.config.DatabaseConfig.getConnection;

import fa.academy.dao.Dao;
import fa.academy.entity.Candidate;
import fa.academy.entity.Experience;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ExperienceDaoImpl implements Dao<Experience> {

    private static final String FIND_BY_ID =
        "SELECT * FROM Experience WHERE ExperienceID = ?";

    private static final String FIND_ALL = "SELECT * FROM Experience";

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

            return experience;
        } catch (Exception e) {
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

            return experience;
        } catch (Exception e) {
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
                Candidate candidate = CandidateDaoImpl.getInstance().find(id);
                Experience experience = new Experience(candidate);
                experience.setExpInYear(rs.getInt("ExpInYear"));
                experience.setProSkill(rs.getString("ProSkill"));

                experienceList.add(experience);
            }

            return experienceList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Experience t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Experience t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(String id) {
        CandidateDaoImpl.getInstance().delete(id);
    }
}
