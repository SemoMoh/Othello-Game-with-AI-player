package com.cse.ai.othellogame.backend.player;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

import com.cse.ai.othellogame.backend.game.Board;
import com.cse.ai.othellogame.backend.game.DISK;

class AIPlayerTest {

    @Test
    void makeMove_EmptyBoard() {
        // Create a real instance of the Board class
        Board board = new Board();

        // Create an instance of AIPlayer with the real board instance
        AIPlayer aiPlayer = new AIPlayer(board, DISK.BLACK, 2); // Assuming difficulty level 2 for the test

        // Call makeMove method
        int move = aiPlayer.makeMove();

        // Assert the expected move
        assertTrue(move >= 0 && move < 64); // Ensure the move is within the board bounds
    }

    @Test
    void makeMove_AllDisksFilled_OneHintPosition() {
        // Create a real instance of the Board class
        Board board = new Board();

        // Set up the board with all disks filled except for one hint position
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (row == 0 && col == 0) {
                    // Skip the hint position
                    continue;
                }
                board.updateBoard(DISK.BLACK, row, col);
            }
        }

        // Set a hint position on the board
        int hintPosition = 18; // Set the hint position to the top-left corner
        board.updateBoard(DISK.HINT, hintPosition / 8, hintPosition % 8); // Convert position to row, column

        // Create an instance of AIPlayer with the real board instance
        AIPlayer aiPlayer = new AIPlayer(board, DISK.BLACK, 2); // Assuming difficulty level 2 for the test

        // Call makeMove method
        int move = aiPlayer.makeMove();

        // Assert the expected move
        assertEquals(hintPosition, move); // Ensure that the AI selects the hint position
    }

    @Test
    void calculateHeuristic() {
    }

    @Test
    void algorithmEval() {
        // Create a real instance of the Board class
        Board board = new Board();

        // Set up the initial state of the board as needed for the test
        // For example, you can set some disks on the board manually
        board.updateBoard(DISK.WHITE, 3, 3);
        board.updateBoard(DISK.WHITE, 4, 4);
        board.updateBoard(DISK.BLACK, 3, 4);
        board.updateBoard(DISK.BLACK, 4, 3);

        // Create an instance of AIPlayer with the real board instance
        AIPlayer aiPlayer = new AIPlayer(board, DISK.BLACK, 2); // Assuming difficulty level 2 for the test

        // Call algorithmEval method
        int bestMove = aiPlayer.algorithmEval();

        // Assert the expected best move
        // Since it depends on the current state of the board and your algorithm,
        // you need to determine the expected best move based on your logic
        // For example, you can assert that the best move is within a certain range of coordinates
        assertTrue(bestMove >= 0 && bestMove < 64);
    }
}