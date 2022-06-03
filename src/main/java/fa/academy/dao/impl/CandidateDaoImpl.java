package fa.academy.dao.impl;

import static fa.academy.config.DatabaseConfig.getConnection;

import fa.academy.dao.Dao;
import fa.academy.entity.Candidate;
import fa.academy.entity.Certification;
import fa.academy.utils.Enum.CandidateType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CandidateDaoImpl implements Dao<Candidate> {

    private static final String FIND_BY_ID =
        "SELECT * FROM Candidate WHERE CandidateID = ?";

    private static final String FIND_ALL = "SELECT * FROM Candidate";

    private static final String DELETE_BY_ID =
        "DELETE FROM Candidate WHERE CandidateID = ?";

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
            return candidate;
        } catch (Exception e) {
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

            return candidateList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void save(Candidate t) {
        // TODO Auto-generated method stub

    }

    @Override
    public void update(Candidate t) {}

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
            System.out.println("The candidate with ID: " + id + " is deleted");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
