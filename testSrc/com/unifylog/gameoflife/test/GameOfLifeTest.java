package com.unifylog.gameoflife.test;

import com.unifylog.gameoflife.GameOfLife;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameOfLifeTest {

    /**
     * Basic test of validation
     * @throws Exception
     */
    @Test
    public void testNullConstructor() throws Exception {

        try {
            GameOfLife gameOfLife = new GameOfLife(null);
        } catch (IllegalArgumentException iae) {
            return;
        }
        fail("Expected fail on null argument");
    }

    /**
     * Test the exercise provided
     * @throws Exception
     */
    @Test
    public void testEvolveExercise4() throws Exception {
        int[][] board = new int[][]{
                new int[]{0, 1, 0, 0, 0},
                new int[]{1, 0, 0, 1, 1},
                new int[]{1, 1, 0, 0, 1},
                new int[]{0, 1, 0, 0, 0},
                new int[]{1, 0, 0, 0, 1},

        };
        int[][] expectedBoard = new int[][]{
                new int[]{0, 0, 0, 0, 0},
                new int[]{1, 0, 1, 1, 1},
                new int[]{1, 1, 1, 1, 1},
                new int[]{0, 1, 0, 0, 0},
                new int[]{0, 0, 0, 0, 0},

        };

        GameOfLife gameOfLife = new GameOfLife(board);
        gameOfLife.evolve();
        GameOfLife expectedGameOfLife = new GameOfLife(expectedBoard);
        assertEquals(expectedGameOfLife,gameOfLife);
    }

    //TODO: Many more unit tests


}