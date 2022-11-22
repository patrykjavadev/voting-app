package com.onwelo.votingapp.service;

import com.onwelo.votingapp.model.Voter;
import com.onwelo.votingapp.model.createCommand.CreateVoterCommand;
import com.onwelo.votingapp.repository.VoterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VoterService {

    private final VoterRepository voterRepository;

    public Voter addVoter(CreateVoterCommand createVoterCommand) {
        Voter voter = Voter.builder()
                .name(createVoterCommand.getName())
                .hasVoted(false)
                .build();
        return voterRepository.saveAndFlush(voter);
    }

    public List<Voter> getVoters() {
        return voterRepository.findAll();
    }
}
