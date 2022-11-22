package com.onwelo.votingapp.repository;

import com.onwelo.votingapp.model.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {
}
