package com.cse.ai.othellogame.backend.player;

import com.cse.ai.othellogame.backend.game.Board;
import com.cse.ai.othellogame.backend.game.DISK;

import java.awt.*;

/**
 * A class representing an AI player in the game.
 * <p>
 * This class implements an AI player capable of making moves based on a specified difficulty level.
 */
public class AIPlayer extends Player{
    /**
     * The difficulty level of the AI player.
     */
    private final int difficulty;

    /**
     * Initializes a new instance of the AIPlayer class with the specified color, game board, and difficulty level.
     *
     * @param board      A reference to the game's board.
     * @param color      The color assigned to the player. DISK.BLACK for black players, DISK.WHITE for white players.
     * @param difficulty The difficulty level of the AI player. Ranges from zero to two, with 2 being the hardest.
     */
    public AIPlayer(Board board, DISK color, int difficulty) {
        super(board, color);
        this.difficulty = difficulty;
    }

    /**
     * Generates a move for the AI player based on the current state of the board.
     * <p>
     * Warning:
     * The chosen position must be a position of a hint position (DISK.HINT) in the board.
     * </p>
     *
     * @return An integer representing the position chosen by the AI player. (Range: [0, 63])
     */
    @Override
    public int makeMove() {
        return algorithmEval();
    }


    /**
     * Executes the AI algorithm to determine the best move.
     * <p>
     *     <b>Steps</b>
     *     <ol>
     *         <li>initialize best score and best move</li>
     *         <li>iterates on every possible move from this board</li>
     *         <li>recursively call Algorithm on each board updating child score and comparing to best score existing</li>
     *         <li>if child score is higher than best score update best score and best move</li>
     *     </ol>
     * Note:
     * This method is used internally by the AIPlayer class to implement the AI logic.
     *
     *
     * @return None, for now.
     */
    public int algorithmEval(){
        //System.out.println(getBoard());
        double bestScore = Integer.MIN_VALUE;
        Point bestMove = null;
        //System.out.println("number of possible moves is : " + getBoard().getAllPossibleMoves().size());
        for(Point move : getBoard().getAllPossibleMoves()){
            //create a new node
            Board newNode;
            try {
                newNode = (Board) getBoard().clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
            newNode.updateBoard(getColor(),move.x,move.y);
            //recursive call
//            int childScore = Minimax.MM(newNode,getColor(),difficulty-1,false);
            double childScore = Minimax.MMAB(newNode,getColor(),difficulty-1,false,Integer.MIN_VALUE,Integer.MAX_VALUE);
            if(childScore > bestScore) {
                bestScore = childScore;
                bestMove = move;
            }
        }
        //System.out.println("best move is : " + (bestMove.x*8+ bestMove.y));
        assert bestMove != null;
        return bestMove.x*8+ bestMove.y;
    }
    public static void main(String[] args){
        Board board = new Board();
        AIPlayer ai = new AIPlayer(board,DISK.BLACK,2);
        ai.makeMove();
        //System.out.println("done");
    }
}
