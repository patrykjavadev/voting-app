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
class VoterControllerIntegrationTest {

    private final CreateVoterCommand createVoterCommand = new CreateVoterCommand("Adam");

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private VoterRepository voterRepository;

    @BeforeEach
    public void clean() {
        voterRepository.deleteAll();
    }

    @Test
    void shouldAddVoter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/voters")
                        .content(mapper.writeValueAsString(createVoterCommand))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(createVoterCommand.getName()));

        List<Voter> voters = voterRepository.findAll();
        Assertions.assertEquals(1, voters.size());
        Voter voter = voters.get(0);
        Assertions.assertEquals(createVoterCommand.getName(), voter.getName());
        Assertions.assertFalse(voter.isHasVoted());
    }

    @Test
    void shouldGetAllVoters() throws Exception {
        shouldAddVoter();
        CreateVoterCommand createVoterCommand1 = new CreateVoterCommand("Jarek");


        mockMvc.perform(MockMvcRequestBuilders
                        .post("/voters")
                        .content(mapper.writeValueAsString(createVoterCommand1))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value(createVoterCommand1.getName()));

        List<Voter> voters = voterRepository.findAll();
        Assertions.assertEquals(2, voters.size());
        Voter voter = voters.get(0);
        Voter voter1 = voters.get(1);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/voters"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(voter.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value(createVoterCommand.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(voter1.getId()))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name").value(createVoterCommand1.getName()));

    }
}