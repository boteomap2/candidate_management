package fa.academy.dao.impl;

import static fa.academy.config.DatabaseConfig.getConnection;

import fa.academy.dao.Dao;
import fa.academy.entity.Candidate;
import fa.academy.entity.Fresher;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FresherDaoImpl implements Dao<Fresher> {

    private static final String FIND_BY_ID =
        "SELECT * FROM Fresher WHERE FresherID = ?";
    private static final String FIND_ALL = "SELECT * FROM Fresher";

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

            return fresher;
        } catch (Exception e) {
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

            return fresher;
        } catch (Exception e) {
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
                Candidate candidate = CandidateDaoImpl.getInstance().find(id);
                Fresher fresher = new Fresher(candidate);
                fresher.setEducation(rs.getString("Education"));
                fresher.setGraduationRank(rs.getString("GraduateRank"));
                fresher.setGraduationDate(
                    rs.getDate("GraduateDate").toLocalDate()
                );

                fresherList.add(fresher);
            }

            return fresherList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Fresher t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Fresher t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(String id) {
        CandidateDaoImpl.getInstance().delete(id);
    }
}
