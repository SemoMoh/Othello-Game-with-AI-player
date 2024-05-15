package com.cse.ai.othellogame.backend.player;

import com.cse.ai.othellogame.backend.game.DISK;

public class Heuristic {
    /**
     * Checks if the given row and column indices are out of bounds of an 8x8 board.
     *
     * @param row The row index.
     * @param col The column index.
     * @return true if the indices are out of bounds, false otherwise.
     */
    private static boolean isOutOfBounds(int row, int col) {

        return row < 0 || row >= 8 || col < 0 || col >= 8;

    }
    /**
     * Checks if a move at the specified row and column indices is valid for the current player.
     *
     * @param coboard         The board to be evaluated.
     * @param row             The row index.
     * @param col             The column index.
     * @param cocurrentPlayer The color of the current player.
     * @return true if the move is valid for the current player, false otherwise.
     */
    private static boolean isValidMove(DISK[][] coboard, int row, int col, DISK cocurrentPlayer) {
        if (coboard[row][col] != DISK.EMPTY) {

            return false; // Cell is not empty
        }


        DISK opponent = (cocurrentPlayer == DISK.WHITE) ? DISK.BLACK : DISK.WHITE;

        // Check for valid moves in all eight directions
        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1},           {0, 1},
                {1, -1},  {1, 0},  {1, 1}
        };

        for (int[] direction : directions) {
            int dx = direction[0];
            int dy = direction[1];

            int newRow = row + dx;
            int newCol = col + dy;

            if (isOutOfBounds(newRow, newCol) || coboard[newRow][newCol] !=opponent ) {

                continue;
                // Skip if the adjacent cell does not belong to the opponent
            }

            newRow += dx;
            newCol += dy;

            while (!isOutOfBounds(newRow, newCol)) {
                if (coboard[newRow][newCol] == cocurrentPlayer) {

                    return true;
                    // Valid move found
                } else if (coboard[newRow][newCol] != cocurrentPlayer&&coboard[newRow][newCol] != opponent) {

                    break; // Encounter an empty cell, move is not valid
                }

                newRow += dx;
                newCol += dy;
            }
        }

        return false;
        // No valid moves found
    }
    /**
     * count number of player's disks on board.
     * @param coboard         The board to be evaluated.
     * @param cocurrentPlayer The color of the current player.
     * @return number of player's disks on board as score.
     */

    public static double heuristic_coin_parity(DISK [][] coboard,DISK cocurrentPlayer){
        double value=0;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(coboard[i][j]==cocurrentPlayer){
                    value++;
                }
            }
        }


        return value;

    }
    /**
     * count number of player's possible moves.
     * @param coboard         The board to be evaluated.
     * @param cocurrentPlayer The color of the current player.
     * @return number of player's possible moves as score.
     */

    private static double heuristic_mobility(DISK [][] coboard,DISK cocurrentPlayer){
        double value=0;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(isValidMove(coboard,i,j,cocurrentPlayer)){
                    value++;
                }
            }
        }


        return value;

    }


    /**
     * count number corners occupied by player's disks.
     * @param coboard         The board to be evaluated.
     * @param cocurrentPlayer The color of the current player.
     * @return number of corners occupied as score.
     */
    private static double heuristic_corner_occupancy(DISK [][] coboard,DISK cocurrentPlayer){
        double value=0;

        if (coboard[0][0]==cocurrentPlayer){
            value++;
        }if (coboard[0][7]==cocurrentPlayer){

            value++;
        } if (coboard[7][0]==cocurrentPlayer){

            value++;
        } if (coboard[7][7]==cocurrentPlayer){

            value++;
        }

        return value;

    }
    /**
     * combine corner occupancy, mobility and coin parity scores.
     * @param coboard         The board to be evaluated.
     * @param cocurrentPlayer The color of the current player.
     * @return combined score.
     */
    public static double calculateHeuristic(DISK[][]coboard,DISK cocurrentPlayer){
        double value =0;
        value=value+heuristic_coin_parity(coboard,cocurrentPlayer);
        value=value+heuristic_mobility(coboard,cocurrentPlayer);
        value=value+heuristic_corner_occupancy(coboard,cocurrentPlayer);
        return value;
    }
}
