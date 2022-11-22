package com.onwelo.votingapp.model.createCommand;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class CreateCandidateCommand {
    private String name;
}
