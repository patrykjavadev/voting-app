package com.onwelo.votingapp.service;

import com.onwelo.votingapp.exception.CandidateNotFoundException;
import com.onwelo.votingapp.exception.VoterAlreadyVotedException;
import com.onwelo.votingapp.exception.VoterNotFoundException;
import com.onwelo.votingapp.model.Candidate;
import com.onwelo.votingapp.model.Voter;
import com.onwelo.votingapp.repository.CandidateRepository;
import com.onwelo.votingapp.repository.VoterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class VoteService {

    private final VoterRepository voterRepository;
    private final CandidateRepository candidateRepository;

    @Transactional
    public void vote(int voterId, int candidateId) {
        Voter voter = voterRepository.findById(voterId).orElseThrow(VoterNotFoundException::new);

        if (voter.isHasVoted())
            throw new VoterAlreadyVotedException();

        voter.setHasVoted(true);
        voterRepository.saveAndFlush(voter);
        Candidate candidate = candidateRepository.findById(candidateId).orElseThrow(CandidateNotFoundException::new);
        candidate.addVote();
        candidateRepository.saveAndFlush(candidate);
    }
}
