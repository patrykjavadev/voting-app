package com.onwelo.votingapp.exception.customMessage;

import com.onwelo.votingapp.exception.CandidateNotFoundException;
import com.onwelo.votingapp.exception.VoterAlreadyVotedException;
import com.onwelo.votingapp.exception.VoterNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CandidateNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    String handleCandidateNotFoundException(Exception ex) {
        return "message: " + ex.getMessage();
    }

    @ExceptionHandler(VoterNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    String handleVoterNotFoundException(Exception ex) {
        return "message: " + ex.getMessage();
    }

    @ExceptionHandler(VoterAlreadyVotedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    String handleVoterAlreadyVotedException(Exception ex) {
        return "message: " + ex.getMessage();
    }
}
