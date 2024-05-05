package com.cse.ai.othellogame.backend.player;

import com.cse.ai.othellogame.backend.game.Board;
import com.cse.ai.othellogame.backend.game.DISK;

import java.awt.*;
import java.util.ArrayList;

public class Minimax {
    static int nodesExplored;
    /**
     *This function implements the Minimax algorithm .

     * Base Case: If the depth is 0 (reached the search limit) or the game is finished, it evaluates the current state using the evalDiscDiff function and returns the score.
     * Maximizing Player:
     ** Initializes the score to the minimum possible integer value (assuming the player wants to maximize their score).
     ** Iterates through all possible moves for the current player using the getAllPossibleMoves function.
     ** For each move:
     ** Creates a new board representing the game state after the move using the getNewBoardAfterMove function.
     ** Recursively calls MM on the new board with the opponent's turn (!max) and decreased depth (depth-1).
     ** Updates the score if the returned child score is greater than the current score.
     ** Minimizing Player: Similar to the maximizing player but tries to minimize the score (assuming the opponent wants to minimize the maximizing player's score).
     Returns the final score for the current node.
     * @param node     representation of the game state ( a 2D array representing the board)
     * @param player   The current player
     * @param depth    The remaining depth in the search tree.
     * @param max      A boolean indicating whether it's the maximizing player's turn (True) or the minimizing player's turn (False).
     */

    //returns minimax value for a given node (without A/B pruning)
    public static int MM(Board node, DISK player, int depth, boolean max){
        nodesExplored++;
        System.out.println("Nodes Explored : " + nodesExplored);

        System.out.println("at depth "+ depth+" the board is: ////////////////////////////");
        System.out.println(node);
        //if terminal reached or depth limit reached evaluate
        if(depth == 0 || node.gameEnded()){
//            heuristic calculation
            System.out.println("value of heuristic is : " + evalDiscDiff(node,player)+"***************************************************************");
            return evalDiscDiff(node,player);
        }
        DISK oplayer = (player == DISK.BLACK) ? DISK.WHITE : DISK.BLACK;
        //if no moves available then forfeit turn
//        if((max && !hasAnyMoves(node,player)) || (!max && !hasAnyMoves(node,oplayer))){
//            System.out.println("Forfeit State Reached !");
//            return MM(node,player,depth-1,!max);
//        }
        int score;
        if(max){
            //maximizing
            score = Integer.MIN_VALUE;
//            System.out.println("number of possible moves is : " + newNode.getAllPossibleMoves());
            for(Point move : node.getAllPossibleMoves())
            { //my turn
                Board newNode = new Board();
                try {
                    newNode = (Board) node.clone();
                }catch (CloneNotSupportedException e)
                {
                    e.printStackTrace();
                }
                //create new node
//                System.out.println("node before update : **************************************************");
                newNode.updateBoard(oplayer,move.x,move.y);
//                System.out.println("node after update : **************************************************");

                //recursive call
                int childScore = MM(newNode,player,depth-1,false);
                if(childScore > score) score = childScore;
            }
        }
        else{
            //minimizing

            score = Integer.MAX_VALUE;
//            System.out.println("number of possible moves is : " + newNode.getAllPossibleMoves());
            for(Point move : node.getAllPossibleMoves()){ //opponent turn
                //create new node
//                System.out.println("node before update : **************************************************");
                Board newNode = new Board();
                try{
                    newNode = (Board) node.clone();
                }catch (CloneNotSupportedException e){
                    e.printStackTrace();
                }
                newNode.updateBoard(oplayer,move.x,move.y);
//                System.out.println("node after update : **************************************************");
                //recursive call
                int childScore = MM(newNode,player,depth-1,true);
                if(childScore < score) score = childScore;
            }
        }
//        System.out.println("the score is: "+score+"at node "+AIPlayer.nodesExplored);
        return score;
    }


    public static int evalDiscDiff(Board board , DISK player){
        DISK oplayer = (player==DISK.BLACK) ? DISK.WHITE : DISK.BLACK;

        int mySC = board.getScore(player);
        int opSC = board.getScore(oplayer);
//        System.out.println("the value of node heuristic is "+ 100 * (mySC - opSC) / (mySC + opSC)+"**************************");

        return 100 * (mySC - opSC) / (mySC + opSC);
    }



}
