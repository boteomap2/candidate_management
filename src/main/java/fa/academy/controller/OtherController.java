package fa.academy.controller;

import fa.academy.dao.impl.CandidateDaoImpl;
import fa.academy.entity.Candidate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class OtherController {

    public void getCandidateCount() {
        Candidate.candidateCount = CandidateDaoImpl.getInstance().getCount();
        System.out.println(
            "Number of candidate in DB: " + Candidate.candidateCount
        );
    }

    public void stringBuilderExcercise() {
        System.out.println("Print all candidate name:");
        List<Candidate> candidateList = CandidateDaoImpl
            .getInstance()
            .findAll();
        StringBuilder sb = new StringBuilder();
        candidateList.forEach(candidate -> {
            sb.append(candidate.getFullname());
            sb.append(", ");
        });
        sb.setLength(sb.length() - 2);
        System.out.println(sb.toString());
    }

    public void collectionsSortExcercise() {
        List<Candidate> candidateList = CandidateDaoImpl
            .getInstance()
            .findAll();
        Collections.sort(
            candidateList,
            Comparator
                .comparing(Candidate::getCandidateType)
                .thenComparing(Candidate::getBirthday)
        );
        candidateList.forEach(candidate ->
            System.out.println(candidate.showInfo())
        );
    }
}
