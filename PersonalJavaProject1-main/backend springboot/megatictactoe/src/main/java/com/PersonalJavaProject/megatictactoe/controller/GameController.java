package com.PersonalJavaProject.megatictactoe.controller;

import com.PersonalJavaProject.megatictactoe.model.MainBoard;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")

public class GameController {
    
    private MainBoard game = new MainBoard();

    @PostMapping("/move")
    public MoveResponse makeMove(@RequestBody MoveRequest req){
        boolean success = game.makeMove(req.mainRow, req.mainCol, req.miniRow, req.miniCol,
            req.player
        );
        return new MoveResponse(success, game.getMainBoardWin(), game.isGameOver());
    }

    @PostMapping("/new-game")
    public String newGame(){
        game = new MainBoard();
        return "New Game Started";
    }
}

class MoveRequest{
    public int mainRow, mainCol, miniRow, miniCol;
    public char player;
}

class MoveResponse{
    public boolean success;
    public char winner;
    public boolean gameOver;

    public MoveResponse(boolean success, char winner, boolean gameOver){
        this.success = success;
        this.winner = winner;
        this.gameOver = gameOver;
    }
}
