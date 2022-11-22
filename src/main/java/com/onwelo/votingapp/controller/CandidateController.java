package com.onwelo.votingapp.controller;

import com.onwelo.votingapp.model.Candidate;
import com.onwelo.votingapp.model.createCommand.CreateCandidateCommand;
import com.onwelo.votingapp.service.CandidateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping
    ResponseEntity<Candidate> addCandidate(@RequestBody CreateCandidateCommand createCandidateCommand) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(candidateService.addCandidate(createCandidateCommand));
    }
}
