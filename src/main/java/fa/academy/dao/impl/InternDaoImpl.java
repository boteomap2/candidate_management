package fa.academy.dao.impl;

import static fa.academy.config.DatabaseConfig.getConnection;

import fa.academy.dao.Dao;
import fa.academy.entity.Candidate;
import fa.academy.entity.Intern;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.*;

public class InternDaoImpl implements Dao<Intern> {

    private static Logger logger = LogManager.getLogger(
        InternDaoImpl.class.getName()
    );

    private static final String FIND_BY_ID =
        "SELECT * FROM Intern WHERE InternID = ?";
    private static final String FIND_ALL = "SELECT * FROM Intern";

    private static final String UPDATE_BY_ID =
        "UPDATE Intern SET University = ?, Major = ?, Semester = ? WHERE InternID = ?";

    private static final String INSERT =
        "INSERT INTO Intern VALUES (?, ?, ?, ?)";

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
            logger.info("Query data id " + id + " in Intern table");
            return intern;
        } catch (Exception e) {
            logger.error(e.getMessage());
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
            logger.info(
                "Query data id " +
                intern.getCandidateId() +
                " in Experience table"
            );
            return intern;
        } catch (Exception e) {
            logger.error(e.getMessage());
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
            logger.info("Query all data in Intern table");
            return internList;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean save(Intern intern) {
        if (!cDaoImpl.save(intern)) return false;
        try (PreparedStatement pst = getConnection().prepareStatement(INSERT)) {
            pst.setString(1, intern.getCandidateId());
            pst.setString(2, intern.getUniversity());
            pst.setString(3, intern.getMajor());
            pst.setInt(4, intern.getSemester());

            int count = pst.executeUpdate();

            if (count < 1) {
                System.out.println(
                    "Something errors, cann't insert into Intern Table"
                );
                return false;
            }
            logger.info("Inserted " + count + " row into Intern Table");
            System.out.println("Inserted " + count + " row into Intern Table");
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public void saveBatch(List<Intern> internList) {
        List<Candidate> candidateList = (List<Candidate>) (
            (List<?>) internList
        );
        cDaoImpl.saveBatch(candidateList);
        try (PreparedStatement pst = getConnection().prepareStatement(INSERT)) {
            getConnection().setAutoCommit(false);
            for (Intern intern : internList) {
                pst.setString(1, intern.getCandidateId());
                pst.setString(2, intern.getUniversity());
                pst.setString(3, intern.getMajor());
                pst.setInt(4, intern.getSemester());
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
                    "Something errors, cann't update Intern Table"
                );
                return false;
            }
            logger.info("Updated " + count + " row into Intern Table");
            System.out.println("Updated " + count + " row into Intern Table");
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
