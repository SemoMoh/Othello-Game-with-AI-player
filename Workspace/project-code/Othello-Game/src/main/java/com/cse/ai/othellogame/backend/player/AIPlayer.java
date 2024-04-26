package com.cse.ai.othellogame.backend.player;

import com.cse.ai.othellogame.backend.game.Board;

/**
 * A class representing an AI player in the game, inheriting from {@link #Player}.
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
     * @param color      The color assigned to the player. 'B' for black players, 'W' for white players.
     * @param difficulty The difficulty level of the AI player. Ranges from 0 to 2, with 2 being the hardest.
     */
    public AIPlayer(Board board, char color, int difficulty) {
        super(board, color);
        this.difficulty = difficulty;
    }

    /**
     * Generates a move for the AI player based on the current state of the board.
     * <p>
     * Warning:
     * The chosen position must be a position of a hint position ('H') in the board.
     * </p>
     *
     * @return An integer representing the position chosen by the AI player. (Range: [0, 63])
     * @Note Position = row * 8 + column
     */
    @Override
    public int makeMove() {
        return 0;
    }

    /**
     * Calculates the heuristic value for the current board state.
     * <p>
     * Note:
     * This method is used internally by the AIPlayer class to evaluate board positions.
     * </p>
     *
     * @return None, for now.
     */
    public void calculateHeuristic(){
        // TODO: implement this method

        // TODO: You can add more data fields to the class to save your values, or you can
        //       return the heuristic value of each position. Organize your work with the developer of the {@link #algorithmEval}
        //       method.

        // TODO: Update the documentation of the method when you determine the return type.
    }

    /**
     * Executes the AI algorithm to determine the best move.
     * <p>
     * Note:
     * This method is used internally by the AIPlayer class to implement the AI logic.
     * </p>
     *
     * @return None, for now.
     */
    public void algorithmEval(){
        // TODO: implement this method

        // TODO: You can collaborate with the developer of the {@link #calculateHeuristic} method to utilize
        //       heuristic values in your algorithm. Additionally, consider adding more data fields to the class or a
        //       new class to assist in the decision-making process.

        // TODO: Update the documentation of the method when you determine the return type.
    }
}
