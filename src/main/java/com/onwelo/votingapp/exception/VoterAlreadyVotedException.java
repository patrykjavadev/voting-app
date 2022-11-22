package com.onwelo.votingapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
public class VoterAlreadyVotedException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Voter already voted";
    }
}
