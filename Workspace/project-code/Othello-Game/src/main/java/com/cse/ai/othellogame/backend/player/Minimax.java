package com.cse.ai.othellogame.backend.player;

import com.cse.ai.othellogame.backend.game.Board;
import com.cse.ai.othellogame.backend.game.DISK;

import java.awt.*;

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
                newNode.updateBoard(player,move.x,move.y);
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


    /** This function implements the Minimax algorithm with Alfa Beta pruning.
      Base Case: If the depth is 0 (reached the search limit) or the game is finished, it evaluates the current state using the evalDiscDiff function and returns the score.
      Maximizing Player:
     *       Initializes the score to the minimum possible integer value (assuming the player wants to maximize their score).
     *       Iterates through all possible moves for the current player using the getAllPossibleMoves function.
     *       For each move:
     *       Creates a new board representing the game state after the move using the getNewBoardAfterMove function.
     *       Recursively calls MMAB on the new board with the opponent's turn (!max) and decreased depth (depth-1).
     *       Updates the score if the returned child score is greater than the current score.
     *       if a child score is greater than the current alpha, it updates alpha but doesn't explore further child nodes if alpha is greater than beta since they cannot possibly yield a better score.
     *** Minimizing Player: Similar to the maximizing player but tries to minimize the score (assuming the opponent wants to minimize the maximizing player's score).
     * if a child score is less than the current beta, it updates beta but doesn't explore further child nodes if alpha is greater than beta since they cannot possibly yield a worse score for the minimizing player.
     *      Returns the final score for the current node.
     *
     * @param node     representation of the game state ( a 2D array representing the board)
     * @param player   The current player
     * @param depth    The remaining depth in the search tree.
     * @param max      A boolean indicating whether it's the maximizing player's turn (True) or the minimizing player's turn (False).
     * @param alpha    The current minimum score for the maximizing player along the current path.
     * @param beta     The current maximum score for the minimizing player along the current path.
     */

    public static int MMAB(Board node,DISK player,int depth,boolean max,int alpha,int beta){
        System.out.println("Nodes Explored : " + nodesExplored);
        nodesExplored++;
        System.out.println("at depth "+ depth+" the board is: ////////////////////////////");
        System.out.println(node);
        //if terminal reached or depth limit reached evaluate
        if(depth == 0 || node.gameEnded()){
            //BoardPrinter bpe = new BoardPrinter(node,"Depth : " + depth);
            return evalDiscDiff(node,player);
        }
        DISK oplayer = (player == DISK.BLACK) ? DISK.WHITE : DISK.BLACK;
        //if no moves available then forfeit turn
//        if((max && !minimax.hasAnyMoves(node,player))  (!max && !minimax.hasAnyMoves(node,oplayer))){
//            //System.out.println("Forfeit State Reached !");
//            return MMAB(node,player,depth-1,!max,alpha,beta);
//        }
        int score;
        if(max){
            //maximizing
            score = Integer.MIN_VALUE;
            for(Point move : node.getAllPossibleMoves()){ //my turn
                Board newNode = new Board();
                try {
                    newNode = (Board) node.clone();
                }catch (CloneNotSupportedException e)
                {
                    e.printStackTrace();
                }
                //create new node
                newNode.updateBoard(player,move.x, move.y);
                //recursive call
                int childScore = MMAB(newNode,player,depth-1,false,alpha,beta);
                if(childScore > score) score = childScore;
                //update alpha
                if(score > alpha) alpha = score;
                if(beta <= alpha) {
                    System.out.println("---------------------------------------cut off----------------------------------------------");
                    break;
                } //Beta Cutoff
            }
        }else{
            //minimizing
            score = Integer.MAX_VALUE;
            for(Point move : node.getAllPossibleMoves()){ //opponent turn
                Board newNode = new Board();
                try {
                    newNode = (Board) node.clone();
                }catch (CloneNotSupportedException e)
                {
                    e.printStackTrace();
                }
                //create new node
                newNode.updateBoard(oplayer, move.x, move.y);
                //recursive call
                int childScore = MMAB(newNode,player,depth-1,true,alpha,beta);
                if(childScore < score) score = childScore;
                //update beta
                if(score < beta) beta = score;
                if(beta <= alpha) {
                    System.out.println("alpha: "+ alpha+"beta: "+ beta);
                    System.out.println("---------------------------------------cut off----------------------------------------------");
                    break;
                } //Alpha Cutoff
            }
        }
        System.out.println("the score is: "+score+"\tat node "+nodesExplored);
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
