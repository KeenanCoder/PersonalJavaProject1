package com.PersonalJavaProject.megatictactoe.model;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LeaderboardRepository extends JpaRepository<LeaderboardEntry, Long> {
    List<LeaderboardEntry> findByDifficultyOrderByWinsDesc(String difficulty);
}