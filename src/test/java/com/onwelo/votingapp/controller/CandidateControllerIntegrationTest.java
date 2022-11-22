package com.onwelo.votingapp.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.onwelo.votingapp.model.Candidate;
import com.onwelo.votingapp.model.createCommand.CreateCandidateCommand;
import com.onwelo.votingapp.repository.CandidateRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class CandidateControllerIntegrationTest {

    private final CreateCandidateCommand createCandidateCommand = new CreateCandidateCommand("Jan");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private CandidateRepository candidateRepository;

    @BeforeEach
    public void clean() {
        candidateRepository.deleteAll();
    }

    @Test
    void shouldAddCandidate() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/candidates")
                        .content(mapper.writeValueAsString(createCandidateCommand))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(createCandidateCommand.getName()));

        List<Candidate> candidates = candidateRepository.findAll();
        Assertions.assertEquals(1, candidates.size());
        Candidate candidate = candidates.get(0);
        Assertions.assertEquals(createCandidateCommand.getName(), candidate.getName());
        Assertions.assertEquals(0, candidate.getVotes());
    }
}