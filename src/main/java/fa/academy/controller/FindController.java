package fa.academy.controller;

import static fa.academy.utils.GlobalScanner.consoleScanner;

import fa.academy.dao.impl.CandidateDaoImpl;
import fa.academy.dao.impl.CertificationDaoImpl;
import fa.academy.dao.impl.ExperienceDaoImpl;
import fa.academy.dao.impl.FresherDaoImpl;
import fa.academy.dao.impl.InternDaoImpl;
import fa.academy.entity.Candidate;
import fa.academy.entity.Certification;
import fa.academy.entity.Experience;
import fa.academy.entity.Fresher;
import fa.academy.entity.Intern;
import fa.academy.utils.Enum.CandidateType;
import fa.academy.utils.TablePrinter;
import fa.academy.utils.Validator;
import java.util.List;

public class FindController {

    private List<Experience> experienceList;
    private List<Fresher> fresherList;
    private List<Intern> internList;
    private List<Certification> cerfList;

    public Candidate findCandidate() {
        System.out.println("Enter 'X' or 'x' to back to Main menu.");
        System.out.print("Enter valid ID, Example 'CDD001': ");
        String id = null;
        while (true) {
            String input = consoleScanner.nextLine();

            if (input.equals("X") || input.equals("x")) {
                return null;
            }

            try {
                id = Validator.validateCandidateId(input);
                break;
            } catch (Exception e) {
                System.out.print("Invalid ID, enter again: ");
            }
        }
        Candidate candidate = CandidateDaoImpl.getInstance().find(id);

        if (candidate == null) {
            System.out.println("Id is not found");
            return null;
        }

        CandidateType candidateType = candidate.getCandidateType();

        if (candidateType == CandidateType.EXPERIENCE) {
            Experience experience = ExperienceDaoImpl
                .getInstance()
                .find(candidate);

            TablePrinter tablePrinter = new TablePrinter(experience);
            tablePrinter.print();
            return experience;
        } else if (candidateType == CandidateType.FRESHER) {
            Fresher fresher = FresherDaoImpl.getInstance().find(candidate);
            TablePrinter tablePrinter = new TablePrinter(fresher);
            tablePrinter.print();
            return fresher;
        } else {
            Intern intern = InternDaoImpl.getInstance().find(candidate);
            TablePrinter tablePrinter = new TablePrinter(intern);
            tablePrinter.print();
            return intern;
        }
    }

    public void findAllCandidate() {
        this.experienceList = ExperienceDaoImpl.getInstance().findAll();
        this.fresherList = FresherDaoImpl.getInstance().findAll();
        this.internList = InternDaoImpl.getInstance().findAll();

        System.out.println("\nEXPERIENCE");
        if (this.experienceList == null) {
            System.out.println("EXPERIENCE table is empty.");
        } else {
            TablePrinter ePrinter = new TablePrinter(this.experienceList);
            ePrinter.print();
        }

        System.out.println("\nFRESHER");
        if (this.fresherList == null) {
            System.out.println("FRESHER table is empty.");
        } else {
            TablePrinter fPrinter = new TablePrinter(this.fresherList);
            fPrinter.print();
        }

        System.out.println("\nINTERN");
        if (this.internList == null) {
            System.out.println("INTERN table is empty.");
        } else {
            TablePrinter iPrinter = new TablePrinter(this.internList);
            iPrinter.print();
        }
    }

    public Certification findCerf() {
        System.out.println("Enter 'X' or 'x' to back to Main menu.");
        System.out.print("Enter valid ID, Example 'CERF001': ");
        String id = null;
        while (true) {
            String input = consoleScanner.nextLine();

            if (input.equals("X") || input.equals("x")) {
                return null;
            }

            try {
                id = Validator.validateCerfId(input);
                break;
            } catch (Exception e) {
                System.out.print("Invalid ID, enter again: ");
            }
        }

        Certification certification = CertificationDaoImpl
            .getInstance()
            .find(id);

        if (certification == null) {
            System.out.println("Id is not found");
            return null;
        }

        TablePrinter tablePrinter = new TablePrinter(certification);
        tablePrinter.print();
        return certification;
    }

    public String findCerfbyCandidateId() {
        System.out.println("Enter 'X' or 'x' to back to Main menu.");
        System.out.print("Enter valid ID, Example 'CDD001': ");
        String id = null;
        while (true) {
            String input = consoleScanner.nextLine();

            if (input.equals("X") || input.equals("x")) {
                return null;
            }

            try {
                id = Validator.validateCandidateId(input);
                break;
            } catch (Exception e) {
                System.out.print("Invalid ID, enter again: ");
            }
        }
        this.cerfList =
            CertificationDaoImpl.getInstance().findByCandidateId(id);
        if (this.cerfList == null) {
            System.out.println(
                "Id is not found or this candidate doesn't have any CERF"
            );
            return null;
        }
        TablePrinter tablePrinter = new TablePrinter(this.cerfList);
        tablePrinter.print();
        return id;
    }
}
