package fa.academy.controller;

import static fa.academy.utils.GlobalScanner.consoleScanner;

import fa.academy.dao.impl.CandidateDaoImpl;
import fa.academy.dao.impl.CertificationDaoImpl;
import fa.academy.entity.Candidate;
import fa.academy.entity.Certification;

public class DeleteController {

    private FindController findController;

    public DeleteController(FindController findController) {
        this.findController = findController;
    }

    public void deleteCandidate() {
        Candidate candidate = findController.findCandidate();
        if (candidate == null) return;

        System.out.println("Enter 'Y' to confirm, 'N' to cancel: ");

        while (true) {
            String input = consoleScanner.nextLine();

            if (input.equals("N") || input.equals("n")) {
                return;
            } else if (input.equals("Y") || input.equals("y")) {
                CandidateDaoImpl
                    .getInstance()
                    .delete(candidate.getCandidateId());
                break;
            } else System.out.print("Invalid Input, enter again: ");
        }
    }

    public void deleteCertification() {
        Certification certification = findController.findCerf();
        if (certification == null) return;

        System.out.println("Enter 'Y' to confirm, 'N' to cancel: ");

        while (true) {
            String input = consoleScanner.nextLine();

            if (input.equals("N") || input.equals("n")) {
                return;
            } else if (input.equals("Y") || input.equals("y")) {
                CertificationDaoImpl
                    .getInstance()
                    .delete(certification.getCertificationId());
                break;
            } else System.out.print("Invalid Input, enter again: ");
        }
    }

    public void deleteCertificationByCid() {
        String id = findController.findCerfbyCandidateId();
        if (id == null) return;
        System.out.println("Enter 'Y' to confirm, 'N' to cancel: ");

        while (true) {
            String input = consoleScanner.nextLine();

            if (input.equals("N") || input.equals("n")) {
                return;
            } else if (input.equals("Y") || input.equals("y")) {
                CertificationDaoImpl.getInstance().deleteByCandidateId(id);
                break;
            } else System.out.print("Invalid Input, enter again: ");
        }
    }
}
