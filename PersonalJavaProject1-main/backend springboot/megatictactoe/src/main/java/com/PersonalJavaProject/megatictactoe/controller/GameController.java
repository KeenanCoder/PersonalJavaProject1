package com.PersonalJavaProject.megatictactoe.controller;

//imports the springboot framework
import com.PersonalJavaProject.megatictactoe.model.MainBoard;
import com.PersonalJavaProject.megatictactoe.model.CpuPlayer;
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

        int[] cpuMove = null;
        if(success && req.difficulty != null && !game.isGameOver()){
            char cpuPlayer = (req.player == 'X') ? 'O' : 'X';
            cpuMove = CpuPlayer.getComputerMove(game, cpuPlayer, req.difficulty);
            if(cpuMove != null){
                game.makeMove(cpuMove[0], cpuMove[1], cpuMove[2], cpuMove[3], cpuPlayer);
            }
        }

        return new MoveResponse(success, game.getMainBoardWin(), game.isGameOver(),
        game.getActiveRow(), game.getActiveCol(), game.getMiniBoardWinners(), cpuMove);
    }

    @PostMapping("/new-game")
    public String newGame(){
        game = new MainBoard();
        return "New Game Started";
    }
}