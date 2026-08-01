package com.PersonalJavaProject.megatictactoe;

import org.junit.jupiter.api.Test;

public class MainBoardTest {
    @Test
    void shouldUpdateActiveBoard(){
        MainBoard mainboard = new MainBoardTest();

        mainboard.getActiveCol();
        mainboard.getActiveRow();
    }

    @Test
    void shouldDetectMegaBoardWinner(){
        MainBoard mainboard = new MainBoardTest();

        mainboard.getMainBoardWin();
    }

    @Test
    void shouldPreventMoveOnWonBoard(){
        MainBoard mainboard = new MainBoardTest();

        mainboard.miniBoardAvailability();
    }
}
