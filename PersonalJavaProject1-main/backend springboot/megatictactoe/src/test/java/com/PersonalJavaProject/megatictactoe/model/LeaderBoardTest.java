package com.PersonalJavaProject.megatictactoe.model;

import com.PersonalJavaProject.megatictactoe.model.LeaderboardEntry;
import com.PersonalJavaProject.megatictactoe.model.LeaderboardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional // rolls back changes after each test, so we don't pollute the real database
class LeaderBoardTest {

    @Autowired
    private LeaderboardRepository leaderboardRepository;

    @Test
    void shouldSaveAndRetrieveEntry(){
        LeaderboardEntry entry = new LeaderboardEntry("Santi", "hard", 12);

        leaderboardRepository.save(entry);

        List<LeaderboardEntry> results = leaderboardRepository.findByDifficultyOrderByWinsDesc("hard");

        assertFalse(results.isEmpty());
        assertEquals("Santi", results.get(0).getPlayerName());
        assertEquals(12, results.get(0).getWins());
    }

    @Test
    void shouldOrderByWinsDescending(){
        leaderboardRepository.save(new LeaderboardEntry("PlayerLow", "impossible", 5));
        leaderboardRepository.save(new LeaderboardEntry("PlayerHigh", "impossible", 50));

        List<LeaderboardEntry> results = leaderboardRepository.findByDifficultyOrderByWinsDesc("impossible");

        assertEquals("PlayerHigh", results.get(0).getPlayerName());
        assertEquals("PlayerLow", results.get(1).getPlayerName());
    }
}