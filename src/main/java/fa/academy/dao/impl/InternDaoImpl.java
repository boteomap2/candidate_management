package fa.academy.dao.impl;

import static fa.academy.config.DatabaseConfig.getConnection;

import fa.academy.dao.Dao;
import fa.academy.entity.Candidate;
import fa.academy.entity.Intern;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class InternDaoImpl implements Dao<Intern> {

    private static final String FIND_BY_ID =
        "SELECT * FROM Intern WHERE InternID = ?";
    private static final String FIND_ALL = "SELECT * FROM Intern";

    private static final String UPDATE_BY_ID =
        "UPDATE Intern SET University = ?, Major = ?, Semester = ? WHERE InternID = ?";

    private CandidateDaoImpl cDaoImpl = CandidateDaoImpl.getInstance();
    private static final InternDaoImpl I_DAO_IMPL = new InternDaoImpl();

    private InternDaoImpl() {
        super();
    }

    public static InternDaoImpl getInstance() {
        return I_DAO_IMPL;
    }

    @Override
    public Intern find(String id) {
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
            Intern intern = new Intern(candidate);
            intern.setUniversity(rs.getString("University"));
            intern.setMajor(rs.getString("Major"));
            intern.setSemester(rs.getInt("Semester"));
            rs.close();

            return intern;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Intern find(Candidate candidate) {
        try (
            PreparedStatement pst = getConnection()
                .prepareStatement(FIND_BY_ID);
        ) {
            pst.setString(1, candidate.getCandidateId());
            ResultSet rs = pst.executeQuery();

            if (!rs.next()) {
                return null;
            }

            Intern intern = new Intern(candidate);
            intern.setUniversity(rs.getString("University"));
            intern.setMajor(rs.getString("Major"));
            intern.setSemester(rs.getInt("Semester"));
            rs.close();

            return intern;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Intern> findAll() {
        try (
            PreparedStatement pst = getConnection().prepareStatement(FIND_ALL);
            ResultSet rs = pst.executeQuery();
        ) {
            if (!rs.isBeforeFirst()) {
                return null;
            }

            ArrayList<Intern> internList = new ArrayList<>();

            while (rs.next()) {
                String id = rs.getString("InternID");
                Candidate candidate = cDaoImpl.find(id);
                Intern intern = new Intern(candidate);
                intern.setUniversity(rs.getString("University"));
                intern.setMajor(rs.getString("Major"));
                intern.setSemester(rs.getInt("Semester"));

                internList.add(intern);
            }

            return internList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(Intern t) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean update(Intern intern) {
        if (!cDaoImpl.update(intern)) return false;
        try (
            PreparedStatement pst = getConnection()
                .prepareStatement(UPDATE_BY_ID)
        ) {
            pst.setString(1, intern.getUniversity());
            pst.setString(2, intern.getMajor());
            pst.setInt(3, intern.getSemester());
            pst.setString(4, intern.getCandidateId());

            int count = pst.executeUpdate();

            if (count < 1) {
                System.out.println(
                    "Something errors, cann't insert into Intern Table"
                );
                return false;
            }
            System.out.println("Inserted " + count + " row into Intern Table");
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
