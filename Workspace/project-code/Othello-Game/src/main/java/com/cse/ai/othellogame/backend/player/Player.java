package com.cse.ai.othellogame.backend.player;

import com.cse.ai.othellogame.backend.game.Board;
import com.cse.ai.othellogame.backend.game.DISK;


/**
 * An abstract class representing players in a game, adhering to the principles of polymorphism.
 * <p>
 * Each player is tasked with selecting a move from the available suggestions marked on the board with the letter
 * DISK.HINT, and returns the position they choose.
 * </p>
 */
public abstract class Player {
    /**
     * The color assigned to the player. DISK.BLACK represents black players, and DISK.WHITE represents white players.
     */
    private final DISK color;

    /**
     * A reference to the game's board.
     */
    private final Board board;

    /**
     * Initializes a new instance of the AbstractPlayer class with the specified color and game board.
     *
     * @param board The color assigned to the player. DISK.BLACK for black players, DISK.WHITE for white players.
     * @param color A reference to the game's board.
     */
    public Player(Board board, DISK color) {
        this.board = board;
        this.color = color;
    }

    /**
     * Abstract method representing the player's move selection process.
     *
     * @return The position chosen by the player for their move. (Range: [o,63])
     * @Note Position = row * 8 + column
     */
    public abstract int makeMove();
}
