package com.onwelo.votingapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onwelo.votingapp.model.Candidate;
import com.onwelo.votingapp.model.Voter;
import com.onwelo.votingapp.model.createCommand.CreateCandidateCommand;
import com.onwelo.votingapp.model.createCommand.CreateVoterCommand;
import com.onwelo.votingapp.repository.CandidateRepository;
import com.onwelo.votingapp.repository.VoterRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class VoteControllerIntegrationTest {

    private final CreateCandidateCommand createCandidateCommand = new CreateCandidateCommand("Jan");
    private final CreateVoterCommand createVoterCommand = new CreateVoterCommand("Adam");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private VoterRepository voterRepository;

    @BeforeEach
    public void clean() {
        candidateRepository.deleteAll();
        voterRepository.deleteAll();
    }

    @Test
    void shouldAddVote() throws Exception {
        addCandidate();

        List<Candidate> candidates = candidateRepository.findAll();
        Assertions.assertEquals(1, candidates.size());
        Candidate candidate = candidates.get(0);
        Assertions.assertEquals(createCandidateCommand.getName(), candidate.getName());
        Assertions.assertEquals(0, candidate.getVotes());

        addVoter();

        List<Voter> voters = voterRepository.findAll();
        Assertions.assertEquals(1, voters.size());
        Voter voter = voters.get(0);
        Assertions.assertEquals(createVoterCommand.getName(), voter.getName());
        Assertions.assertFalse(voter.isHasVoted());

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/vote/" + voter.getId() + "/" + candidate.getId()))
                .andDo(print()).andExpect(status().isNoContent());

        List<Candidate> updatedCandidates = candidateRepository.findAll();
        Candidate updatedCandidate = updatedCandidates.get(0);

        Assertions.assertEquals(1, updatedCandidate.getVotes());

        List<Voter> updatedVoters = voterRepository.findAll();
        Voter updatedVoter = updatedVoters.get(0);
        Assertions.assertTrue(updatedVoter.isHasVoted());
    }

    void addCandidate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/candidates")
                        .content(mapper.writeValueAsString(createCandidateCommand))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(createCandidateCommand.getName()));
    }

    void addVoter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/voters")
                        .content(mapper.writeValueAsString(createVoterCommand))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(createVoterCommand.getName()));
    }
}