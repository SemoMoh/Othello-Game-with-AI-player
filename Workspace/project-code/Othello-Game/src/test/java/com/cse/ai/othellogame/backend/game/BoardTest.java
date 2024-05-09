package com.cse.ai.othellogame.backend.game;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    @Test
    void generateNewHintsEmptyBoard() {
        // Test generating hints on an empty board

        // Create a new board
        Board board = new Board();

        // Call the method to generate hints for black player
        board.generateNewHints(DISK.BLACK);

        // Get all possible moves after generating hints
        ArrayList<Point> possibleMoves = board.getAllPossibleMoves();

        // Assert that the hints are generated
        assertFalse(board.isAreThereAnyHints(), "Hints should be generated");

        // Assert that the number of hints is as expected
        int expectedNumberOfHints = 4;
        assertEquals(expectedNumberOfHints, possibleMoves.size(), "Number of hints generated should match expected value");

        // Assert that all hints are valid (i.e., they are empty positions on the board)
        for (Point move : possibleMoves) {
            assertEquals(DISK.HINT, board.getPos(move.x, move.y), "All generated hints should be valid positions");
        }
    }

    @Test
    void generateNewHintsWithSpecificBoard_1() {
        // Test generating hints on a board with a specific configuration

        // Create a new board
        Board board = new Board();

        // Set the board with the given configuration
        board.setPos(DISK.BLACK, 2, 3);
        board.setPos(DISK.WHITE, 2, 4);
        board.setPos(DISK.WHITE, 3, 2);
        board.setPos(DISK.BLACK, 3, 3);
        board.setPos(DISK.WHITE, 3, 4);
        board.setPos(DISK.WHITE, 3, 5);
        board.setPos(DISK.BLACK, 4, 3);
        board.setPos(DISK.WHITE, 4, 4);
        board.setPos(DISK.BLACK, 4, 5);
        board.setPos(DISK.BLACK, 5, 3);
        board.setPos(DISK.WHITE, 5, 4);
        board.setPos(DISK.WHITE, 6, 5);

        // Call the method to generate hints for black player
        board.generateNewHints(DISK.BLACK);

        // Get all possible moves after generating hints
        ArrayList<Point> possibleMoves = board.getAllPossibleMoves();

        // Assert that the hints are generated
        assertTrue(board.isAreThereAnyHints(), "Hints should be generated");

        // Assert that the number of hints is as expected
        int expectedNumberOfHints = 10;
        assertEquals(expectedNumberOfHints, possibleMoves.size(), "Number of hints generated should match expected value");

        // Assert that all generated hints are valid positions (i.e., they are empty positions on the board)
        for (Point move : possibleMoves) {
            assertEquals(DISK.HINT, board.getPos(move.x, move.y), "All generated hints should be valid positions");
        }
    }

    @Test
    void generateNewHintsWithSpecificBoard_2() {
        // Test generating hints on a board with a specific configuration

        // Set up the board with a specific configuration
        Board board = new Board();
        board.setPos(DISK.WHITE, 0, 0);
        board.setPos(DISK.WHITE, 0, 1);
        board.setPos(DISK.WHITE, 0, 2);
        board.setPos(DISK.WHITE, 0, 3);
        board.setPos(DISK.WHITE, 0, 4);
        board.setPos(DISK.WHITE, 0, 5);
        board.setPos(DISK.WHITE, 0, 6);
        board.setPos(DISK.BLACK, 1, 3);
        board.setPos(DISK.WHITE, 1, 4);
        board.setPos(DISK.WHITE, 2, 0);
        board.setPos(DISK.WHITE, 2, 1);
        board.setPos(DISK.WHITE, 2, 2);
        board.setPos(DISK.WHITE, 2, 3);
        board.setPos(DISK.WHITE, 2, 4);
        board.setPos(DISK.WHITE, 2, 5);
        board.setPos(DISK.WHITE, 2, 6);
        board.setPos(DISK.WHITE, 3, 0);
        board.setPos(DISK.WHITE, 3, 1);
        board.setPos(DISK.WHITE, 3, 2);
        board.setPos(DISK.WHITE, 3, 3);
        board.setPos(DISK.BLACK, 3, 4);
        board.setPos(DISK.WHITE, 4, 0);
        board.setPos(DISK.WHITE, 4, 1);
        board.setPos(DISK.BLACK, 4, 2);
        board.setPos(DISK.BLACK, 4, 3);
        board.setPos(DISK.BLACK, 4, 4);
        board.setPos(DISK.BLACK, 4, 5);
        board.setPos(DISK.BLACK, 4, 7);
        board.setPos(DISK.WHITE, 5, 0);
        board.setPos(DISK.WHITE, 5, 1);
        board.setPos(DISK.WHITE, 5, 2);
        board.setPos(DISK.WHITE, 5, 3);
        board.setPos(DISK.BLACK, 5, 4);
        board.setPos(DISK.WHITE, 5, 5);
        board.setPos(DISK.BLACK, 5, 6);
        board.setPos(DISK.BLACK, 5, 7);
        board.setPos(DISK.WHITE, 6, 2);
        board.setPos(DISK.WHITE, 6, 3);
        board.setPos(DISK.WHITE, 6, 5);
        board.setPos(DISK.BLACK, 6, 7);
        board.setPos(DISK.WHITE, 7, 1);
        board.setPos(DISK.WHITE, 7, 2);
        board.setPos(DISK.WHITE, 7, 3);
        board.setPos(DISK.WHITE, 7, 6);

        // Call the method to generate hints for black player
        board.generateNewHints(DISK.BLACK);

        // Get all possible moves after generating hints
        ArrayList<Point> possibleMoves = board.getAllPossibleMoves();

        // Assert that the hints are generated
        assertTrue(board.isAreThereAnyHints(), "Hints should be generated");

        // Assert that the number of hints is as expected
        int expectedNumberOfHints = 12;
        assertEquals(expectedNumberOfHints, possibleMoves.size(), "Number of hints generated should match expected value");

        // Assert that all generated hints are valid positions (i.e., they are empty positions on the board)
        for (Point move : possibleMoves) {
            assertEquals(DISK.HINT, board.getPos(move.x, move.y), "All generated hints should be valid positions");
        }
    }

    @Test
    void generateNewHintsWithSpecificBoard_3() {
        // Test generating hints on a board with a specific configuration

        // Create a new board
        Board board = new Board();

        // Set the board with the given configuration
        board.setPos(DISK.BLACK, 1, 5);
        board.setPos(DISK.BLACK, 2, 1);
        board.setPos(DISK.BLACK, 2, 3);
        board.setPos(DISK.BLACK, 2, 4);
        board.setPos(DISK.BLACK, 3, 1);
        board.setPos(DISK.BLACK, 3, 2);
        board.setPos(DISK.BLACK, 3, 3);
        board.setPos(DISK.WHITE, 3, 4);
        board.setPos(DISK.BLACK, 4, 1);
        board.setPos(DISK.BLACK, 4, 3);
        board.setPos(DISK.WHITE, 4, 4);
        board.setPos(DISK.WHITE, 5, 2);
        board.setPos(DISK.WHITE, 5, 3);
        board.setPos(DISK.WHITE, 5, 4);

        // Call the method to generate hints for black player
        board.generateNewHints(DISK.BLACK);

        // Get all possible moves after generating hints
        ArrayList<Point> possibleMoves = board.getAllPossibleMoves();

        // Assert that the hints are generated
        assertTrue(board.isAreThereAnyHints(), "Hints should be generated");

        // Assert that the number of hints is as expected
        int expectedNumberOfHints = 8;
        assertEquals(expectedNumberOfHints, possibleMoves.size(), "Number of hints generated should match expected value");

        // Assert that all generated hints are valid positions (i.e., they are empty positions on the board)
        for (Point move : possibleMoves) {
            assertEquals(DISK.HINT, board.getPos(move.x, move.y), "All generated hints should be valid positions");
        }
    }

    @Test
    void generateNewHintsNoPossibleMoves() {
        // Test generating hints when there are no possible moves

        // Create a new board
        Board board = new Board();

        // Set the board with the given complex configuration
        board.setPos(DISK.BLACK, 0, 0);
        board.setPos(DISK.BLACK, 0, 1);
        board.setPos(DISK.BLACK, 0, 2);
        board.setPos(DISK.BLACK, 0, 3);
        board.setPos(DISK.BLACK, 0, 4);
        board.setPos(DISK.BLACK, 0, 5);
        board.setPos(DISK.BLACK, 0, 6);
        board.setPos(DISK.BLACK, 0, 7);
        board.setPos(DISK.BLACK, 1, 1);
        board.setPos(DISK.BLACK, 1, 2);
        board.setPos(DISK.BLACK, 1, 3);
        board.setPos(DISK.BLACK, 1, 7);
        board.setPos(DISK.BLACK, 2, 0);
        board.setPos(DISK.BLACK, 2, 1);
        board.setPos(DISK.BLACK, 2, 2);
        board.setPos(DISK.BLACK, 2, 3);
        board.setPos(DISK.BLACK, 2, 4);
        board.setPos(DISK.BLACK, 2, 5);
        board.setPos(DISK.BLACK, 2, 6);
        board.setPos(DISK.BLACK, 2, 7);
        board.setPos(DISK.BLACK, 3, 0);
        board.setPos(DISK.BLACK, 3, 1);
        board.setPos(DISK.WHITE, 3, 2);
        board.setPos(DISK.WHITE, 3, 3);
        board.setPos(DISK.BLACK, 3, 4);
        board.setPos(DISK.BLACK, 3, 5);
        board.setPos(DISK.BLACK, 3, 6);
        board.setPos(DISK.BLACK, 3, 7);
        board.setPos(DISK.BLACK, 4, 0);
        board.setPos(DISK.WHITE, 4, 1);
        board.setPos(DISK.BLACK, 4, 2);
        board.setPos(DISK.BLACK, 4, 3);
        board.setPos(DISK.BLACK, 4, 4);
        board.setPos(DISK.BLACK, 4, 5);
        board.setPos(DISK.WHITE, 4, 6);
        board.setPos(DISK.BLACK, 4, 7);
        board.setPos(DISK.WHITE, 5, 0);
        board.setPos(DISK.WHITE, 5, 1);
        board.setPos(DISK.WHITE, 5, 2);
        board.setPos(DISK.WHITE, 5, 3);
        board.setPos(DISK.WHITE, 5, 4);
        board.setPos(DISK.WHITE, 5, 5);
        board.setPos(DISK.WHITE, 5, 6);
        board.setPos(DISK.BLACK, 5, 7);
        board.setPos(DISK.BLACK, 6, 0);
        board.setPos(DISK.BLACK, 6, 1);
        board.setPos(DISK.BLACK, 6, 2);
        board.setPos(DISK.BLACK, 6, 3);
        board.setPos(DISK.BLACK, 6, 4);
        board.setPos(DISK.BLACK, 6, 5);
        board.setPos(DISK.WHITE, 6, 6);
        board.setPos(DISK.BLACK, 6, 7);
        board.setPos(DISK.BLACK, 7, 0);
        board.setPos(DISK.BLACK, 7, 1);
        board.setPos(DISK.BLACK, 7, 2);
        board.setPos(DISK.BLACK, 7, 3);
        board.setPos(DISK.BLACK, 7, 4);
        board.setPos(DISK.BLACK, 7, 5);
        board.setPos(DISK.BLACK, 7, 6);
        board.setPos(DISK.BLACK, 7, 7);

        // Call the method to generate hints for black player
        board.generateNewHints(DISK.BLACK);

        // Get all possible moves after generating hints
        ArrayList<Point> possibleMoves = board.getAllPossibleMoves();

        // Assert that the hints are generated
        assertFalse(board.isAreThereAnyHints(), "Hints should be generated");

        // Assert that the number of hints is as expected
        int expectedNumberOfHints = 0;
        assertEquals(expectedNumberOfHints, possibleMoves.size(), "Number of hints generated should match expected value");

        // Assert that all generated hints are valid positions (i.e., they are empty positions on the board)
        for (Point move : possibleMoves) {
            assertEquals(DISK.HINT, board.getPos(move.x, move.y), "All generated hints should be valid positions");
        }
    }

    @Test
    void removeAllHints() {
        // Set up the board with hints in some positions
        Board board = new Board();
        board.setPos(DISK.HINT, 0, 0);
        board.setPos(DISK.HINT, 2, 3);
        board.setPos(DISK.HINT, 3, 5);

        // Call the removeAllHints method
        board.removeAllHints();

        // Assert that all positions with hints are now empty
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                if (!(row >= 3 && row <= 4 && col >= 3 && col <= 4)) { // Skip initial setup positions
                    assertEquals(DISK.EMPTY, board.getPos(row, col), "All hints should be removed");
                }
            }
        }

        // Assert that the flag indicating hints are present is set to false
        assertFalse(board.isAreThereAnyHints(), "The flag indicating hints should be false after removal");
    }

    @Test
    void gameEnded() {
    }
}