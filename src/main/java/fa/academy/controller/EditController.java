package fa.academy.controller;

import static fa.academy.utils.GlobalScanner.consoleScanner;

import fa.academy.dao.impl.CertificationDaoImpl;
import fa.academy.dao.impl.ExperienceDaoImpl;
import fa.academy.dao.impl.FresherDaoImpl;
import fa.academy.dao.impl.InternDaoImpl;
import fa.academy.entity.Candidate;
import fa.academy.entity.Certification;
import fa.academy.entity.Experience;
import fa.academy.entity.Fresher;
import fa.academy.entity.Intern;
import fa.academy.utils.Validator;
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
        "ExpInYear",
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

    public EditController(FindController findController) {
        this.findController = findController;
    }

    public void editCandidate() {
        Candidate candidate = findController.findCandidate();
        if (candidate == null) return;

        System.out.println(
            "\tNote #1: Some columns can't be changed such as ID and Candidate Type"
        );
        System.out.println(
            "\tNote #2: Press enter to skip the column you don't want to update"
        );
        System.out.println(
            "\tNote #3: Date format can be 'dd/MM/yyyy' or 'yyyy-MM-dd'"
        );
        System.out.println(
            "\tNote #4: If you want to update CERF, go to its menu outside"
        );
        System.out.println("\tNote #5: Enter 'X' or 'x' to stop exit");

        for (String header : this.candidateHeader) {
            while (true) {
                System.out.print(header + ": ");
                boolean isNextHeader = false;
                String input = consoleScanner.nextLine();

                if (input.equals("X") || input.equals("x")) {
                    return;
                }

                if (input.isEmpty()) break;

                try {
                    switch (header) {
                        case "FullName":
                            candidate.setFullname(
                                Validator.validateCandidateName(input)
                            );
                            isNextHeader = true;
                            break;
                        case "Birthday":
                            candidate.setBirthday(
                                Validator.validateDate(input)
                            );
                            isNextHeader = true;
                            break;
                        case "Phone":
                            candidate.setPhone(
                                Validator.validateCandidatePhone(input)
                            );
                            isNextHeader = true;
                            break;
                        case "Email":
                            candidate.setEmail(
                                Validator.validateCandidateEmail(input)
                            );
                            isNextHeader = true;
                            break;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                if (isNextHeader) break;
            }
        }

        if (candidate instanceof Experience) {
            Experience experience = (Experience) candidate;
            for (String header : this.experienceHeader) {
                while (true) {
                    System.out.print(header + ": ");
                    Boolean isNextHeader = false;
                    String input = consoleScanner.nextLine();

                    if (input.equals("X") || input.equals("x")) {
                        return;
                    }

                    if (input.isEmpty()) break;

                    try {
                        switch (header) {
                            case "ExpInYear":
                                experience.setExpInYear(
                                    Validator.validateNumber(input)
                                );
                                isNextHeader = true;
                                break;
                            case "ProSkill":
                                experience.setProSkill(input);
                                isNextHeader = true;
                                break;
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    if (isNextHeader) break;
                }
            }
            ExperienceDaoImpl.getInstance().update(experience);
        } else if (candidate instanceof Fresher) {
            Fresher fresher = (Fresher) candidate;
            for (String header : this.freserHeader) {
                while (true) {
                    System.out.print(header + ": ");
                    Boolean isNextHeader = false;
                    String input = consoleScanner.nextLine();

                    if (input.equals("X") || input.equals("x")) {
                        return;
                    }

                    if (input.isEmpty()) break;

                    try {
                        switch (header) {
                            case "Education":
                                fresher.setEducation(input);
                                isNextHeader = true;
                                break;
                            case "GraduationRank":
                                fresher.setGraduationRank(input);
                                isNextHeader = true;
                                break;
                            case "GraduationDate":
                                fresher.setGraduationDate(
                                    Validator.validateDate(input)
                                );
                                isNextHeader = true;
                                break;
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    if (isNextHeader) break;
                }
            }
            FresherDaoImpl.getInstance().update(fresher);
        } else if (candidate instanceof Intern) {
            Intern intern = (Intern) candidate;
            for (String header : this.internHeader) {
                while (true) {
                    Boolean isNextHeader = false;
                    System.out.print(header + ": ");
                    String input = consoleScanner.nextLine();

                    if (input.equals("X") || input.equals("x")) {
                        return;
                    }

                    if (input.isEmpty()) break;

                    try {
                        switch (header) {
                            case "University":
                                intern.setUniversity(input);
                                isNextHeader = true;
                                break;
                            case "Major":
                                intern.setMajor(input);
                                isNextHeader = true;
                                break;
                            case "Semester":
                                intern.setSemester(
                                    Validator.validateNumber(input)
                                );
                                isNextHeader = true;
                                break;
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }

                    if (isNextHeader) break;
                }
            }
            InternDaoImpl.getInstance().update(intern);
        }
    }

    public void editCertification() {
        Certification certification = findController.findCerf();
        if (certification == null) return;
        System.out.println("\tNote #1: Column ID can't be changed");
        System.out.println(
            "\tNote #2: Press enter to skip the column you don't want to update"
        );
        System.out.println(
            "\tNote #3: Date format can be 'dd/MM/yyyy' or 'yyyy-MM-dd'"
        );
        System.out.println("\tNote #4: Enter 'X' or 'x' to stop exit");

        for (String header : this.cerfHeader) {
            while (true) {
                System.out.print(header + ": ");
                boolean isNextHeader = false;
                String input = consoleScanner.nextLine();

                if (input.equals("X") || input.equals("x")) {
                    return;
                }

                if (input.isEmpty()) break;

                try {
                    switch (header) {
                        case "CERF-Name":
                            certification.setCertificationName(input);
                            isNextHeader = true;
                            break;
                        case "CERF-Rank":
                            certification.setCertificationRank(input);
                            isNextHeader = true;
                            break;
                        case "CERF-Date":
                            certification.setCertificationDate(
                                Validator.validateDate(input)
                            );
                            isNextHeader = true;
                            break;
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                if (isNextHeader) break;
            }
        }

        CertificationDaoImpl.getInstance().update(certification);
    }
}
