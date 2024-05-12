package com.cse.ai.othellogame.backend.player;

import com.cse.ai.othellogame.backend.game.DISK;

public class Heuristic {
    private static boolean isOutOfBounds(int row, int col) {

        return row < 0 || row >= 8 || col < 0 || col >= 8;

    }

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

    private static double heuristic_frontier_disks(DISK[][] coboard, DISK cocurrentPlayer){
        double value=0;

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(coboard[i][j]==cocurrentPlayer){

                    int[][] directions = {
                            {-1, -1}, {-1, 0}, {-1, 1},
                            {0, -1},           {0, 1},
                            {1, -1},  {1, 0},  {1, 1}
                    };


                    for (int[] direction : directions) {
                        int dx = direction[0];
                        int dy = direction[1];

                        int newRow = i + dx;
                        int newCol = j + dy;

                        if(coboard[newRow][newCol]!=cocurrentPlayer){
                            value++;
                            break;
                        }


                    }

                }
            }
        }

        return value;

    }
    private static double heuristic_stability(DISK [][] coboard,DISK cocurrentPlayer){
        double value=0;
        DISK opponent = (cocurrentPlayer == DISK.WHITE) ? DISK.BLACK : DISK.WHITE;
        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){
                if(coboard[i][j]==cocurrentPlayer){
                    boolean k=false;
                    int[][] directions = {
                            {-1, -1}, {-1, 0}, {-1, 1},
                            {0, -1},           {0, 1},
                            {1, -1},  {1, 0},  {1, 1}
                    };

                    for (int[] direction : directions) {
                        int dx = direction[0];
                        int dy = direction[1];

                        int newRow = i + dx;
                        int newCol = j + dy;
                        while (!isOutOfBounds(newRow,newCol)){
                            if(coboard[newRow][newCol]!=cocurrentPlayer&&coboard[newRow][newCol]!=opponent){
                                k=true;
                                break;
                            }
                        }
                        if (k==true){
                            break;
                        }
                    }
                    if (k==false){
                        value++;
                    }

                }
            }
        }



        return value;

    }


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

    public static double calculateHeuristic(DISK[][]coboard,DISK cocurrentPlayer){
        double value =0;
        value=value+heuristic_coin_parity(coboard,cocurrentPlayer);
        value=value+heuristic_mobility(coboard,cocurrentPlayer);
//        value=value+heuristic_frontier_disks(coboard,cocurrentPlayer);
//        value=value+heuristic_stability(coboard,cocurrentPlayer);
        value=value+heuristic_corner_occupancy(coboard,cocurrentPlayer);
        return value;
    }
}
