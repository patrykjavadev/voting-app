package com.onwelo.votingapp.repository;

import com.onwelo.votingapp.model.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepository extends JpaRepository<Voter, Integer> {
}
