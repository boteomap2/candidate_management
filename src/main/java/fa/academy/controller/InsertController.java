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
import fa.academy.utils.Enum.CandidateType;
import fa.academy.utils.IdGenerator;
import fa.academy.utils.Validator;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class InsertController {

    private List<String> candidateHeader = Arrays.asList(
        "FullName",
        "Birthday",
        "Phone",
        "Email",
        "CandidateType"
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

    public InsertController(FindController findController) {
        this.findController = findController;
    }

    public void insertCandidate() {
        System.out.println(
            "\tNote #1: Press enter to skip the column you don't want to update"
        );
        System.out.println(
            "\tNote #2: Date format can be 'dd/MM/yyyy' or 'yyyy-MM-dd'"
        );
        System.out.println(
            "\tNote #3: Candidate Type : 0 - Experience, 1 - Fresher, 2 - Intern"
        );
        System.out.println("\tNote #4: Enter 'X' or 'x' to stop exit");
        Candidate candidate = new Candidate();
        String id = IdGenerator.generateCddId();
        System.out.println("CandidateID (auto generate): " + id);
        candidate.setCandidateId(id);
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
                        case "CandidateType":
                            candidate.setCandidateType(
                                Validator.validateCandidateType(input)
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

        if (candidate.getCandidateType() == CandidateType.EXPERIENCE) {
            Experience experience = new Experience(candidate);
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
            ExperienceDaoImpl.getInstance().save(experience);
        } else if (candidate.getCandidateType() == CandidateType.FRESHER) {
            Fresher fresher = new Fresher(candidate);
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
            FresherDaoImpl.getInstance().save(fresher);
        } else if (candidate.getCandidateType() == CandidateType.INTERN) {
            Intern intern = new Intern(candidate);
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
            InternDaoImpl.getInstance().save(intern);
        }
    }

    public void insertCertification() {
        System.out.println("Enter the CID you want to add Certification.");
        Candidate candidate = this.findController.findCandidate();
        if (candidate == null) return;
        Certification certification = new Certification();
        System.out.println(
            "\tNote #1: Press enter to skip the column you don't want to update"
        );
        System.out.println(
            "\tNote #2: Date format can be 'dd/MM/yyyy' or 'yyyy-MM-dd'"
        );
        System.out.println("\tNote #3: Enter 'X' or 'x' to stop exit");

        String CertId = IdGenerator.generateCerfId();
        certification.setCertificationId(CertId);
        System.out.println("CERT-ID: " + CertId);
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
        CertificationDaoImpl
            .getInstance()
            .save(certification, candidate.getCandidateId());
    }

    public void insertDataFromFile() {
        System.out.println("Enter 'X' or 'x' to back to Main menu.");
        System.out.print("Enter link to CSV file: ");
        File file;
        while (true) {
            String input = consoleScanner.nextLine();
            if (input.equals("X") || input.equals("x")) {
                return;
            }
            file = new File(input);
            if (!file.exists()) {
                System.out.println("File is not found.");
                continue;
            }
            String fileName = file.getName();
            int dotIndex = fileName.lastIndexOf('.');
            String fileType = (dotIndex == -1)
                ? ""
                : fileName.substring(dotIndex + 1);
            if (!fileType.equals("csv")) {
                System.out.println("File type is not CSV.");
            }
            break;
        }

        List<Experience> experienceList = new ArrayList<>();
        List<Fresher> fresherList = new ArrayList<>();
        List<Intern> internList = new ArrayList<>();
        try (Scanner filScanner = new Scanner(file)) {
            while (filScanner.hasNextLine()) {
                String line = filScanner.nextLine();

                if (line.isEmpty()) {
                    continue;
                }
                Candidate candidate = new Candidate();
                String[] cols = line.split(",");
                try {
                    candidate.setCandidateId(
                        Validator.validateCandidateId(cols[0])
                    );
                    candidate.setFullname(
                        Validator.validateCandidateName(cols[1])
                    );
                    candidate.setBirthday(Validator.validateDate(cols[2]));
                    candidate.setPhone(
                        Validator.validateCandidatePhone(cols[3])
                    );
                    candidate.setEmail(
                        Validator.validateCandidateEmail(cols[4])
                    );
                    candidate.setCandidateType(
                        Validator.validateCandidateType(cols[5])
                    );

                    if (
                        candidate.getCandidateType() == CandidateType.EXPERIENCE
                    ) {
                        Experience experience = new Experience(candidate);
                        experience.setExpInYear(
                            Validator.validateNumber(cols[6])
                        );
                        experience.setProSkill(cols[7]);
                        experienceList.add(experience);
                        candidate = experience;
                    } else if (
                        candidate.getCandidateType() == CandidateType.FRESHER
                    ) {
                        Fresher fresher = new Fresher(candidate);
                        fresher.setEducation(cols[6]);
                        fresher.setGraduationRank((cols[7]));
                        fresher.setGraduationDate(
                            Validator.validateDate(cols[8])
                        );
                        fresherList.add(fresher);
                        candidate = fresher;
                    } else {
                        Intern intern = new Intern(candidate);
                        intern.setUniversity(cols[6]);
                        intern.setMajor(cols[7]);
                        intern.setSemester(Validator.validateNumber(cols[8]));
                        internList.add(intern);
                        candidate = intern;
                    }

                    if (cols[cols.length - 1].isEmpty()) {
                        candidate.setCertificationList(null);
                        continue;
                    }

                    ArrayList<Certification> certList = new ArrayList<>();
                    String[] certLines = cols[cols.length - 1].split(";");
                    for (String certLine : certLines) {
                        String[] fields = certLine.split("-");
                        Certification cert = new Certification();
                        try {
                            cert.setCertificationId(
                                Validator.validateCerfId(fields[0])
                            );
                            cert.setCertificationName(fields[1]);
                            cert.setCertificationRank(fields[2]);
                            cert.setCertificationDate(
                                Validator.validateDate(fields[3])
                            );
                            certList.add(cert);
                        } catch (Exception e) {
                            // e.printStackTrace();
                            continue;
                        }
                    }
                    candidate.setCertificationList(certList);
                } catch (Exception e) {
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (experienceList.size() > 0) {
            ExperienceDaoImpl.getInstance().saveBatch(experienceList);
            System.out.println("========================");
        }

        if (fresherList.size() > 0) {
            FresherDaoImpl.getInstance().saveBatch(fresherList);
            System.out.println("========================");
        }

        if (internList.size() > 0) {
            InternDaoImpl.getInstance().saveBatch(internList);
            System.out.println("========================");
        }
    }

    public void insertCertificationWithRS() {
        System.out.println("Enter the CID you want to add Certification.");
        Candidate candidate = this.findController.findCandidate();
        if (candidate == null) return;
        Certification certification = new Certification();
        System.out.println(
            "\tNote #1: Press enter to skip the column you don't want to update"
        );
        System.out.println(
            "\tNote #2: Date format can be 'dd/MM/yyyy' or 'yyyy-MM-dd'"
        );
        System.out.println("\tNote #3: Enter 'X' or 'x' to stop exit");

        String CertId = IdGenerator.generateCerfId();
        certification.setCertificationId(CertId);
        System.out.println("CERT-ID: " + CertId);
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
        CertificationDaoImpl
            .getInstance()
            .saveWithResultSet(certification, candidate.getCandidateId());
    }
}
