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
        assertFalse(board.isAreThereAnyHints(), "Hints should be generated");

        // Assert that the number of hints is as expected
        int expectedNumberOfHints = 1;
        assertEquals(expectedNumberOfHints, possibleMoves.size(), "Number of hints generated should match expected value");

        // Assert that all generated hints are valid positions (i.e., they are empty positions on the board)
        for (Point move : possibleMoves) {
            assertEquals(DISK.HINT, board.getPos(move.x, move.y), "All generated hints should be valid positions");
        }
    }

    @Test
    void removeAllHints() {
    }

    @Test
    void isAreThereAnyHints() {
    }

    @Test
    void gameEnded() {
    }
}