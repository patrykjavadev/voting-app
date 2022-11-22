package com.onwelo.votingapp.controller;

import com.onwelo.votingapp.model.Voter;
import com.onwelo.votingapp.model.createCommand.CreateVoterCommand;
import com.onwelo.votingapp.service.VoterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/voters")
@RequiredArgsConstructor
public class VoterController {

    private final VoterService voterService;

    @PostMapping
    public ResponseEntity<Voter> addVoter(@RequestBody CreateVoterCommand createVoterCommand) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(voterService.addVoter(createVoterCommand));
    }

    @GetMapping
    public ResponseEntity<List<Voter>> getVoters() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(voterService.getVoters());
    }

}
