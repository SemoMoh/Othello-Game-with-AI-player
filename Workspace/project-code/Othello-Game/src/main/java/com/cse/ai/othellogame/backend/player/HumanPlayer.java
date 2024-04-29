package com.cse.ai.othellogame.backend.player;


import com.cse.ai.othellogame.backend.game.Board;

/**
 * A class representing a human player in the game, inheriting from {@link #Player}.
 * <p>
 * This class allows a human player to interact with the game by selecting moves through user input.
 * </p>
 */
public class HumanPlayer extends Player{

    /**
     * Initializes a new instance of the AbstractPlayer class with the specified color and game board.
     *
     * @param board The color assigned to the player. DISK.BLACK for black players, DISK.WHITE for white players.
     * @param color A reference to the game's board.
     */
    public HumanPlayer(Board board, DISK color) {
        super(board, color);
    }

    /**
     * Obtain a move from the human player via user input and return the position they choose.
     * <p>
     * Note:
     * <ul>
     *   <li>TODO: figure out how it will take the inputs</li>
     *   <li>Hints are shown to the user by the GUI directly so no need to do that here.</li>
     * </ul>
     * </p>
     *
     * @return The position chosen by the player for their move. (Range: [o,63])
     * @Note Position = row * 8 + column
     */
    @Override
    public int makeMove() {
        return 0;
    }
}
