package com.PersonalJavaProject.megatictactoe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.PersonalJavaProject.megatictactoe.controller.GameController;
import com.PersonalJavaProject.megatictactoe.controller.MoveRequest;
import com.PersonalJavaProject.megatictactoe.controller.MoveResponse;

public class GameControllerTest {

    //GAME STARTUP****************************************
    @Test
    void newGameReturnsConfirmationMessage(){
        GameController controller = new GameController();

        String result = controller.newGame();

        assertEquals("New Game Started", result);
    }

    //MOVE SETS*******************************************
    @Test
    void moveEndpointRejectsInvalidMove(){
        GameController controller = new GameController();

        MoveRequest req = new MoveRequest();
        req.mainRow = 0;
        req.mainCol = 0;
        req.miniRow = 0;
        req.miniCol = 0;
        req.player = 'X';

        // First move is legal, should succeed
        controller.makeMove(req);

        // Trying to play the SAME cell again should be rejected
        MoveResponse secondAttempt = controller.makeMove(req);

        assertFalse(secondAttempt.success);
    }

    @Test
    void validMoveUpdatesBoard(){
        GameController controller = new GameController();

        MoveRequest req = new MoveRequest();
        req.mainRow = 0;
        req.mainCol = 0;
        req.miniRow = 1;
        req.miniCol = 1;
        req.player = 'X';

        MoveResponse response = controller.makeMove(req);

        assertTrue(response.success);
        assertFalse(response.gameOver);
        assertEquals(' ', response.winner);
    }
}