package com.onwelo.votingapp.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "voters")
public class Voter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private boolean hasVoted;

    public void setHasVoted(boolean hasVoted) {
        this.hasVoted = hasVoted;
    }
}
