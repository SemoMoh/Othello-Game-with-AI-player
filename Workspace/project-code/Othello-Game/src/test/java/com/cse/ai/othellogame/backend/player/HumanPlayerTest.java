package com.cse.ai.othellogame.backend.player;

import com.cse.ai.othellogame.backend.game.Board;
import com.cse.ai.othellogame.backend.game.DISK;
import com.cse.ai.othellogame.gui.gamescreen.GameScreen;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class HumanPlayerTest {

    @Test
    void makeMove_ValidInput_ReturnsExpectedPosition() {
        // Create a new board
        Board board = new Board();

        // Create a new GameScreen
        GameScreen gameScreen = new GameScreen();

        // Set up a position for the player's move
        int expectedPosition = 42; // Example position

        // Set the expected input on the GameScreen
        gameScreen.setInput(expectedPosition);

        // Create a new HumanPlayer instance with the board and GameScreen
        HumanPlayer humanPlayer = new HumanPlayer(board, DISK.BLACK);
        humanPlayer.setGameGUI(gameScreen);

        // Call the makeMove() method
        int actualPosition = humanPlayer.makeMove();

        // Assert that the actual position matches the expected position
        assertEquals(expectedPosition, actualPosition);
    }
}