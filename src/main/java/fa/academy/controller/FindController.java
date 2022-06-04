package fa.academy.controller;

import static fa.academy.utils.GlobalScanner.consoleScanner;

import fa.academy.dao.impl.CandidateDaoImpl;
import fa.academy.dao.impl.ExperienceDaoImpl;
import fa.academy.dao.impl.FresherDaoImpl;
import fa.academy.dao.impl.InternDaoImpl;
import fa.academy.entity.Candidate;
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

    public Candidate findCandidate() {
        System.out.println("Enter 'X' or 'x' to back to Main menu.");
        System.out.print("Enter valid ID, Example 'CDD001': ");
        String id = null;
        while (true) {
            String input = consoleScanner.nextLine();

            if (input.equals("X") || input.equals("x")) {
                return null;
            }

            if (Validator.validateCandidateId(input)) {
                id = input;
                break;
            }

            System.out.print("Invalid ID, enter again: ");
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

        TablePrinter ePrinter = new TablePrinter(this.experienceList);
        TablePrinter fPrinter = new TablePrinter(this.fresherList);
        TablePrinter iPrinter = new TablePrinter(this.internList);
        System.out.println("\nEXPERIENCE");
        if (this.experienceList == null) {
            System.out.println("EXPERIENCE table is emply.");
        } else ePrinter.print();

        System.out.println("\nFRESHER");
        if (this.fresherList == null) {
            System.out.println("FRESHER table is emply.");
        } else fPrinter.print();

        System.out.println("\nINTERN");
        if (this.internList == null) {
            System.out.println("INTERN table is emply.");
        } else iPrinter.print();
    }
}
