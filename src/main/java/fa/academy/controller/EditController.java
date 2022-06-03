package fa.academy.controller;

import static fa.academy.utils.GlobalScanner.consoleScanner;

import fa.academy.entity.Candidate;
import fa.academy.entity.Experience;
import fa.academy.entity.Fresher;
import java.util.Arrays;
import java.util.List;

public class EditController {

    private List<String> candidateHeader = Arrays.asList(
        "FullName",
        "Birthday",
        "Phone",
        "Email"
    );

    private List<String> experienceHeader = Arrays.asList(
        "expInYear",
        "ProSkill"
    );

    private List<String> freserHeader = Arrays.asList(
        "Education",
        "GraduationRank",
        "GraduationDate"
    );

    private List<String> internHeader = Arrays.asList(
        "University",
        "Major",
        "Semester"
    );

    private List<String> cerfHeader = Arrays.asList(
        "CERF-Name",
        "CERF-Rank",
        "CERF-Date"
    );

    private FindController findController;

    public EditController() {
        super();
    }

    public EditController(FindController findController) {
        this.findController = findController;
    }

    public void editCandidate() {
        Candidate candidate = findController.findCandidate();
        System.out.println(
            "Press enter to skip field you don't want to update"
        );
        for (String header : candidateHeader) {
            System.out.print(header + ": ");
            String input = consoleScanner.nextLine();
            if (input.isEmpty()) continue;

            switch (header) {
                case "FullName":
                    break;
                case "Birthday":
                    break;
                case "Phone":
                    break;
                case "Email":
                    break;
            }
        }
        if (candidate == null) return;

        if (candidate instanceof Experience) {} else if (
            candidate instanceof Fresher
        ) {} else {}
    }
}
