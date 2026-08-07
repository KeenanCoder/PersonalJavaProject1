package com.PersonalJavaProject.megatictactoe.controller;

import com.PersonalJavaProject.megatictactoe.model.LeaderboardEntry;
import com.PersonalJavaProject.megatictactoe.model.LeaderboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaderboard")
@CrossOrigin(origins = "*")
public class LeaderboardController {

    @Autowired
    private LeaderboardRepository leaderboardRepository;

    // Submit or update a score
    @PostMapping
    public LeaderboardEntry submitScore(@RequestBody LeaderboardEntry entry){
        return leaderboardRepository.save(entry);
    }

    // Get top scores for a given difficulty (e.g. /api/leaderboard?difficulty=hard)
    @GetMapping
    public List<LeaderboardEntry> getLeaderboard(@RequestParam String difficulty){
        return leaderboardRepository.findByDifficultyOrderByWinsDesc(difficulty);
    }
}