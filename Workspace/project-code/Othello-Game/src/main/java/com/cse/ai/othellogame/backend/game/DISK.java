package com.cse.ai.othellogame.backend.game;

/**
 * Enumeration representing the states of cells on the Othello game board.
 * <p>
 * The {@code DISK} enum defines different states of cells on the Othello game board,
 * including player discs (BLACK, WHITE), empty cells (EMPTY), and hints for valid moves (HINT).
 * Additionally, it includes states for GUI representation of discs with hints (BLACK_HINT, WHITE_HINT).
 * </p>
 */
public enum DISK {
    /**
     * Represents a black disc placed by one of the players.
     */
    BLACK,
    /**
     * Represents a white disc placed by one of the players.
     */
    WHITE,
    /**
     * Represents an empty cell on the game board.
     */
    EMPTY,
    /**
     * Represents a hint for a valid move for the current player.
     */
    HINT,
    /**
     * Represents a black disc with a hint for GUI representation.
     * Used by the graphical user interface.
     */
    BLACK_HINT,
    /**
     * Represents a white disc with a hint for GUI representation.
     * Used by the graphical user interface.
     */
    WHITE_HINT;
}
