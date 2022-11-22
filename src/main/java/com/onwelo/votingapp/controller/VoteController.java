package com.onwelo.votingapp.controller;

import com.onwelo.votingapp.service.VoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vote")
@RequiredArgsConstructor
public class VoteController {

    private final VoteService voteService;

    @PutMapping("/{voterId}/{candidateId}")
    public ResponseEntity<Void> vote(@PathVariable int voterId, @PathVariable int candidateId) {
        voteService.vote(voterId, candidateId);
        return ResponseEntity.noContent().build();
    }
}
