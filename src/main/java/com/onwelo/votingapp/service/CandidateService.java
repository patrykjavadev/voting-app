package com.onwelo.votingapp.service;

import com.onwelo.votingapp.model.Candidate;
import com.onwelo.votingapp.model.createCommand.CreateCandidateCommand;
import com.onwelo.votingapp.repository.CandidateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CandidateService {

    private final CandidateRepository candidateRepository;

    public Candidate addCandidate(CreateCandidateCommand createCandidateCommand) {
        Candidate candidate = Candidate.builder()
                .name(createCandidateCommand.getName())
                .votes(0)
                .build();
        return candidateRepository.saveAndFlush(candidate);
    }
}
