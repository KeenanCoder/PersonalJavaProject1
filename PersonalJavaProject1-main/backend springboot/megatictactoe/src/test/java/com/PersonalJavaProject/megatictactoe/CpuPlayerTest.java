package com.PersonalJavaProject.megatictactoe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CpuPlayerTest {
    @Test
    void easyMakesRandomMove(){
        CpuPlayerTest cpuPlayerTest = new CpuPlayerTest();

        cpuPlayerTest.getComputerMove();
    }

    @Test
    void hardBlocksWinningMove(){}

    @Test
    void impossibleAlwaysChoosesWinningMove(){}
}
