package fa.academy.dao.impl;

import static fa.academy.config.DatabaseConfig.getConnection;

import fa.academy.dao.Dao;
import fa.academy.entity.Candidate;
import fa.academy.entity.Fresher;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class FresherDaoImpl implements Dao<Fresher> {

    private static final String FIND_BY_ID =
        "SELECT * FROM Fresher WHERE FresherID = ?";
    private static final String FIND_ALL = "SELECT * FROM Fresher";

    private static final String UPDATE_BY_ID =
        "UPDATE Fresher SET Education = ?, GraduateRank = ?, GraduateDate = ? WHERE FresherID = ?";

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
                Candidate candidate = cDaoImpl.find(id);
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
    public boolean save(Fresher t) {
        // TODO Auto-generated method stub
        return false;
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
                    "Something errors, cann't insert into Fresher Table"
                );
                return false;
            }
            System.out.println("Inserted " + count + " row into Fresher Table");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void delete(String id) {
        CandidateDaoImpl.getInstance().delete(id);
    }
}
