package com.unifylog.gameoflife;

/**
 * Created with IntelliJ IDEA.
 * User: andres
 * Date: 5/29/2014
 * Time: 12:12 PM
 *
 * You shall not count thy self
 */
public class GameOfLife {

    int[][] board;

    public GameOfLife(int[][] board) {
        if (board == null) {
            throw new IllegalArgumentException("Board can't be null");
        } else if (board.length == 0) {
            throw new IllegalArgumentException("Board can't be of size 0");
        }
        //check for a rectangle or a square??
        int width = 0;
        for (int[] row : board) {
            if (row == null || row.length == 0) {
                throw new IllegalArgumentException("Board can't have null rows");
            }
            if (width == 0) {
                width = row.length;
            } else if (width != row.length) {
                throw new IllegalArgumentException("Board must be a rectangle");
            }
        }
        //validate cells
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int cell = board[i][j];
                if (!(cell == 0 || cell == 1)) {
                    throw new IllegalArgumentException("Invalid cell value:" + cell);
                }
            }
        }

        this.board = board;
    }

    public GameOfLife(int boardSize) {
        throw new IllegalArgumentException("Not implemented yet");
        //TODO: Generate a random initial state
    }

    /**
     * Creates a new generation of cells based on board conditions
     * finally replaces board wih the new generation
     */
    public void evolve() {
        int[][] newgen = new int[board.length][];

        for (int i = 0; i < newgen.length; i++) {
            newgen[i] = new int[board[i].length];
        }
        for (int i = 0; i < newgen.length; i++) {
            for (int j = 0; j < newgen[i].length; j++) {
                int neighbourCount = getNeighbourCount(i, j);
                if (board[i][j] == 1) {//live cell
                    if (neighbourCount < 2) {//under-population
                        newgen[i][j] = 0;
                    } else if (neighbourCount == 2 || neighbourCount == 3) {//survival
                        newgen[i][j] = 1;
                    } else if (neighbourCount > 3) {//overcrowding
                        newgen[i][j] = 0;
                    }
                } else {
                    if (neighbourCount == 3) {//reproduction
                        newgen[i][j] = 1;
                    } else {
                        newgen[i][j] = 0;
                    }
                }
                //Left here intentionally
                //System.out.print("x[" + i + "]y[" +j + "] count[" + neighbourCount);
                //System.out.print(boardToString(newgen));
            }

        }


        board = newgen;
    }

    /**
     * Neighbour  counting algorithm, could be modified to exclude diagonal neighbours
     * or to include neighbours n-cells apart
     * @param i row
     * @param j column
     * @return the count
     */
    protected int getNeighbourCount(int i, int j) {
        int count = 0;
        for (int x = i - 1; x <= i + 1; x++) {
            for (int y = j - 1; y <= j + 1; y++) {
                count += valAt(x, y);
            }
        }
        //You shall not count thyself
        return count-valAt(i,j);
    }

    /**
     * Assumes that anything outside the board is 'dead'
     * @param x row
     * @param y column
     * @return alive or dead
     */
    private int valAt(int x, int y) {
        if (x < 0 || x >= board.length) {
            return 0;
        }
        if (y < 0 || y >= board[0].length) {
            return 0;
        }
        return board[x][y];
    }


    @Override
    public String toString() {

        return boardToString(board);
    }

    /**
     * Convenience method to debug
     * @param toPrint board
     * @return a string to output to sysout
     */
    private String boardToString(int[][] toPrint) {
        StringBuilder b = new StringBuilder();
        b.append("--\n");
        for (int[] row : toPrint) {
            for (int cell : row) {
                b.append(cell).append(" ");
            }
            b.append("\n");
        }
        return b.toString();
    }

    /**
     * Makes it easier to unit test
     * @param o other GoL
     * @return true or false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        //TODO: check for equal sizes
        GameOfLife other = (GameOfLife) o;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[i][j] != other.board[i][j]) {
                    return false;
                }
            }

        }

        return true;
    }

}