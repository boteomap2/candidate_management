package fa.academy.utils;

import fa.academy.entity.Candidate;
import fa.academy.entity.Certification;
import fa.academy.entity.Experience;
import fa.academy.entity.Fresher;
import fa.academy.entity.Intern;
import fa.academy.utils.common.TableList;
import java.util.ArrayList;

public class TablePrinter<T extends Candidate> {

    private T single;
    private ArrayList<T> list;
    private TableList tableList;

    public TablePrinter() {}

    public TablePrinter(ArrayList<T> list) {
        if (list != null) {
            this.list = list;
            this.single = list.get(0);
        }
    }

    public TablePrinter(T single) {
        this.single = single;
        this.list = new ArrayList<>();
        this.list.add(single);
    }

    private void createTableHeader() {
        if (this.single instanceof Experience) {
            this.tableList =
                new TableList(
                    "Id",
                    "FullName",
                    "Birthday",
                    "Phone",
                    "Email",
                    "CandidateType",
                    "expInYear",
                    "ProSkill",
                    "CERF-ID",
                    "CERF-Name",
                    "CERF-Rank",
                    "CERF-Date"
                )
                    .withUnicode(true);
        } else if (this.single instanceof Fresher) {
            this.tableList =
                new TableList(
                    "Id",
                    "FullName",
                    "Birthday",
                    "Phone",
                    "Email",
                    "CandidateType",
                    "Education",
                    "GraduationRank",
                    "GraduationDate",
                    "CERF-ID",
                    "CERF-Name",
                    "CERF-Rank",
                    "CERF-Date"
                )
                    .withUnicode(true);
        } else if (this.single instanceof Intern) {
            this.tableList =
                new TableList(
                    "Id",
                    "FullName",
                    "Birthday",
                    "Phone",
                    "Email",
                    "CandidateType",
                    "University",
                    "Major",
                    "Semester",
                    "CERF-ID",
                    "CERF-Name",
                    "CERF-Rank",
                    "CERF-Date"
                )
                    .withUnicode(true);
        }
    }

    private void addInfo() {
        if (this.single instanceof Experience) {
            @SuppressWarnings("unchecked")
            ArrayList<Experience> experienceList = (ArrayList<Experience>) (
                (ArrayList<?>) list
            );

            for (Experience experience : experienceList) {
                ArrayList<Certification> cerfList = experience.getCertificationList();
                if (cerfList != null) {
                    for (int i = 0; i < cerfList.size(); i++) {
                        if (i == 0) {
                            this.tableList.addRow(
                                    experience.getCandidateId(),
                                    experience.getFullname(),
                                    experience.getBirthday().toString(),
                                    experience.getPhone(),
                                    experience.getEmail(),
                                    experience.getCandidateType().toString(),
                                    Integer.toString(experience.getExpInYear()),
                                    experience.getProSkill(),
                                    cerfList.get(i).getCertificationId(),
                                    cerfList.get(i).getCertificationName(),
                                    cerfList.get(i).getCertificationRank(),
                                    cerfList
                                        .get(i)
                                        .getCertificationDate()
                                        .toString()
                                );
                        } else this.tableList.addRow(
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                cerfList.get(i).getCertificationId(),
                                cerfList.get(i).getCertificationName(),
                                cerfList.get(i).getCertificationRank(),
                                cerfList
                                    .get(i)
                                    .getCertificationDate()
                                    .toString()
                            );
                    }
                } else {
                    this.tableList.addRow(
                            experience.getCandidateId(),
                            experience.getFullname(),
                            experience.getBirthday().toString(),
                            experience.getPhone(),
                            experience.getEmail(),
                            experience.getCandidateType().toString(),
                            Integer.toString(experience.getExpInYear()),
                            experience.getProSkill(),
                            null,
                            null,
                            null,
                            null
                        );
                }
            }
        } else if (this.single instanceof Fresher) {
            @SuppressWarnings("unchecked")
            ArrayList<Fresher> fresherList = (ArrayList<Fresher>) (
                (ArrayList<?>) list
            );
            for (Fresher fresher : fresherList) {
                ArrayList<Certification> cerfList = fresher.getCertificationList();
                if (cerfList != null) {
                    for (int i = 0; i < cerfList.size(); i++) {
                        if (i == 0) {
                            this.tableList.addRow(
                                    fresher.getCandidateId(),
                                    fresher.getFullname(),
                                    fresher.getBirthday().toString(),
                                    fresher.getPhone(),
                                    fresher.getEmail(),
                                    fresher.getCandidateType().toString(),
                                    fresher.getEducation(),
                                    fresher.getGraduationRank(),
                                    fresher.getGraduationDate().toString(),
                                    cerfList.get(i).getCertificationId(),
                                    cerfList.get(i).getCertificationName(),
                                    cerfList.get(i).getCertificationRank(),
                                    cerfList
                                        .get(i)
                                        .getCertificationDate()
                                        .toString()
                                );
                        } else this.tableList.addRow(
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                cerfList.get(i).getCertificationId(),
                                cerfList.get(i).getCertificationName(),
                                cerfList.get(i).getCertificationRank(),
                                cerfList
                                    .get(i)
                                    .getCertificationDate()
                                    .toString()
                            );
                    }
                } else {
                    this.tableList.addRow(
                            fresher.getCandidateId(),
                            fresher.getFullname(),
                            fresher.getBirthday().toString(),
                            fresher.getPhone(),
                            fresher.getEmail(),
                            fresher.getCandidateType().toString(),
                            fresher.getEducation(),
                            fresher.getGraduationRank(),
                            fresher.getGraduationDate().toString(),
                            null,
                            null,
                            null,
                            null
                        );
                }
            }
        } else if (this.single instanceof Intern) {
            @SuppressWarnings("unchecked")
            ArrayList<Intern> internList = (ArrayList<Intern>) (
                (ArrayList<?>) list
            );
            for (Intern intern : internList) {
                ArrayList<Certification> cerfList = intern.getCertificationList();
                if (cerfList != null) {
                    for (int i = 0; i < cerfList.size(); i++) {
                        if (i == 0) {
                            this.tableList.addRow(
                                    intern.getCandidateId(),
                                    intern.getFullname(),
                                    intern.getBirthday().toString(),
                                    intern.getPhone(),
                                    intern.getEmail(),
                                    intern.getCandidateType().toString(),
                                    intern.getUniversity(),
                                    intern.getMajor(),
                                    Integer.toString(intern.getSemester()),
                                    cerfList.get(i).getCertificationId(),
                                    cerfList.get(i).getCertificationName(),
                                    cerfList.get(i).getCertificationRank(),
                                    cerfList
                                        .get(i)
                                        .getCertificationDate()
                                        .toString()
                                );
                        } else this.tableList.addRow(
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                null,
                                cerfList.get(i).getCertificationId(),
                                cerfList.get(i).getCertificationName(),
                                cerfList.get(i).getCertificationRank(),
                                cerfList
                                    .get(i)
                                    .getCertificationDate()
                                    .toString()
                            );
                    }
                } else {
                    this.tableList.addRow(
                            intern.getCandidateId(),
                            intern.getFullname(),
                            intern.getBirthday().toString(),
                            intern.getPhone(),
                            intern.getEmail(),
                            intern.getCandidateType().toString(),
                            intern.getUniversity(),
                            intern.getMajor(),
                            Integer.toString(intern.getSemester()),
                            null,
                            null,
                            null,
                            null
                        );
                }
            }
        }
    }

    public void printToTable() {
        createTableHeader();
        if (this.tableList == null) return;
        addInfo();
        this.tableList.print();
    }
}
