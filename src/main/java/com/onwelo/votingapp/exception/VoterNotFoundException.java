package com.onwelo.votingapp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class VoterNotFoundException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Voter with given id doesn't exists";
    }
}
