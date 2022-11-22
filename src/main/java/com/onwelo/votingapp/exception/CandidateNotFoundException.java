package com.onwelo.votingapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CandidateNotFoundException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Candidate with given id doesn't exists";
    }
}
