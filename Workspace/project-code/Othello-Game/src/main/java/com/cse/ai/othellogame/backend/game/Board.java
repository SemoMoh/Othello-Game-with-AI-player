package com.cse.ai.othellogame.backend.game;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * Represents the game board for an Othello game.
 * <p>
 * The board consists of 64 cells arranged in 8x8 grid. Each cell can contain one of the following values:
 * <ul>
 *     <li>'W' for white disc</li>
 *     <li>'B' for black disc</li>
 *     <li>'H' for hints where to play</li>
 *     <li>'E' for empty cell</li>
 * </ul>
 * <p>
 * The initial configuration of the board is as follows:
 * <ul>
 *     <li>White discs at positions (3,3) and (4,4)</li>
 *     <li>Black discs at positions (3,4) and (4,3)</li>
 *     <li>Hints for black discs at positions (2,3), (3,2), (4,5), and (5,4)</li>
 * </ul>
 * <p>
 * <img src="https://othelloacademy.weebly.com/uploads/5/7/9/7/57973435/2426516.jpg?1439705158" alt="Othello Board">
 * <p>
 * For the rules of the Othello game, see:
 * <a href="https://othelloacademy.weebly.com/rules.html">Othello Rules</a>
 */
public class Board {
    private char[] board;

    /**
     * Constructs a new game board with default dimensions and initializes its black and white disks,
     * also it shows the hint values for the first move of the game for the black.
     */
    public Board() {
        board = new char[64];
        // Init all with empty --> 'E'
        IntStream.range(0, board.length).forEach(i -> board[i] = 'E');
        // put the starting position of black and white --> 'B' and 'W'
        setPos('W', 3, 3);
        setPos('B', 3, 4);
        setPos('B', 4, 3);
        setPos('W', 4, 4);
        // put the initial hints for the black --> 'H'
        setPos('H', 2, 3);
        setPos('H', 3, 2);
        setPos('H', 4, 5);
        setPos('H', 5, 4);
    }

    /**
     * Called when any player makes a move to update the board with the inserted position.
     * <p>
     * <b>Steps:</b>
     * <ol>
     *   <li>Check if the position is valid:
     *     <ul>
     *       <li>Ensure the position corresponds to one of the 'H' positions.</li>
     *       <li>Ensure the position is within the range of the board.</li>
     *     </ul>
     *   </li>
     *   <li>Delete all old 'H' characters from the board.</li>
     *   <li>Place the new disk at its position, representing the player's move.</li>
     *   <li>Update the other disks on the board according to the insertion position and its color.</li>
     *   <li>Generate new hints for the next move by the opposing player.</li>
     * </ol>
     * </p>
     *
     * @param color The player who played this turn ('B' or 'W').
     * @param row   The row index of the inserted position (0-based) (range: [0, 7]).
     * @param col   The column index of the inserted position (0-based) (range: [0, 7]).
     */
    public void updateBoard(char color, int row, int col) {
        //TODO: implement this method
    }

    /**
     * Generates new hints for the next move by analyzing the current state of the board and the newly inserted position.
     * used in the {@link #updateBoard(char, int, int)} method.
     *
     * @param colorToPlay the next players color ('B' or 'W')
     * @param row         The row index of the inserted position (0-based) (range: [0, 7]).
     * @param col         The column index of the inserted position (0-based) (range: [0, 7]).
     */
    public void generateNewHints(char colorToPlay, int row, int col) {
        //TODO: implement this method
        //hello


    }

    /**
     * Checks if the Othello game has ended.
     * <p>
     * This method analyzes the current state of the Othello game board to determine whether the game has ended.
     * The game is considered ended if one of the following conditions is met:
     * <ul>
     *   <li>No more valid moves are available for either player.</li>
     *   <li>The entire board is filled with discs, and no further moves can be made.</li>
     * </ul>
     * </p>
     * <p>
     * If the game has ended, this method returns {@code true}; otherwise, it returns {@code false}.
     * <br>
     * Note: The winner is determined by the player with more discs of their color on the board at the end of the game,
     *       call the {@link #getWinner()} method to get the color of the winning player.
     * </p>
     *
     * @return {@code true} if the Othello game has ended, {@code false} otherwise.
     */
    public boolean gameEnded() {
        //TODO: implement this method
        return false;
    }

    /**
     * Gets the winner of the Othello game.
     * <p>
     * This method determines and returns the winner of the Othello game based on the scores of the white
     * and black players. The player with the higher score is declared the winner. If the scores are equal,
     * the game is considered a tie.
     * </p>
     *
     * @return The color of the winning player ('W' for white, 'B' for black, 'T' for tie).
     */
    public char getWinner() {
        return getWhiteScore() > getBlackScore() ? 'W' :
                getWhiteScore() < getBlackScore() ? 'B' : 'T';
    }

    /**
     * Gets the score of the white player.
     * <p>
     * This method calculates and returns the score of the white player in the Othello game.
     * The score represents the number of discs (pieces) of the white player currently on the board.
     * </p>
     *
     * @return The score of the white player.
     */
    public int getWhiteScore() {
        return getScore('W');
    }

    /**
     * Gets the score of the black player.
     * <p>
     * This method calculates and returns the score of the black player in the Othello game.
     * The score represents the number of discs (pieces) of the black player currently on the board.
     * </p>
     *
     * @return The score of the black player.
     */
    public int getBlackScore() {
        return getScore('B');
    }

    /**
     * Gets the score of the specified player.
     * <p>
     * This method calculates and returns the score of the player specified by the {@code color} parameter
     * in the Othello game. The score represents the number of discs (pieces) of the specified player
     * currently on the board.
     * </p>
     *
     * @param color The color of the player whose score is to be calculated ('W' for white, 'B' for black).
     * @return The score of the specified player.
     */
    private int getScore(char color) {
        int count = 0;
        for (char c : board) {
            if (c == 'W') {
                count++;
            }
        }
        return count;
    }

    /**
     * Sets a position at the specified row and column with the given value.
     *
     * @param value the value of the disc ('W', 'B', 'H', or 'E')
     * @param row   the row index (0-based) (range: [0, 7])
     * @param col   the column index (0-based) (range: [0, 7])
     */
    public void setPos(char value, int row, int col) {
        setPos(value, row * 8 + col);
    }

    /**
     * Sets a position at the specified position with the given value.
     *
     * @param value the value of the disc ('W', 'B', 'H', or 'E')
     * @param pos   the position index (0-based) (range: [0, 63])
     */
    public void setPos(char value, int pos) {
        board[pos] = value;
    }

    /**
     * Gets the value of the position at the specified row and column.
     *
     * @param row the row index (0-based) (range: [0, 7])
     * @param col the column index (0-based) (range: [0, 7])
     * @return the value of the disc at the specified position
     */
    public char getPos(int row, int col) {
        return getPos(row * 8 + col);
    }

    /**
     * Gets the value of the position at the specified position.
     *
     * @param pos the position index (0-based) (range: [0, 63])
     * @return the value of the disc at the specified position
     */
    public char getPos(int pos) {
        return board[pos];
    }

    /**
     * Gets the 1D array representation of the board.
     *
     * @return the 1D array representing the board
     */
    public char[] getBoard() {
        return board;
    }

    /**
     * Gets the 2D array representation of the board.
     *
     * @return the 2D array representing the board
     */
    public char[][] getBoard2D() {
        char[][] board2D = new char[8][8];
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                board2D[row][col] = this.getPos(row, col);
            }
        }
        return board2D;
    }

    /**
     * Gets the list representation of the 1D board.
     *
     * @return the list representing the board
     */
    public List<Character> getBoardList() {
        return IntStream.range(0, board.length)
                .mapToObj(i -> board[i])
                .collect(Collectors.toList());
    }

    /**
     * Gets the 2D list representation of the 2D board.
     *
     * @return the 2D list representing the board
     */
    public List<List<Character>> getBoardList2D() {
        char[][] board2D = this.getBoard2D();
        return Arrays.stream(board2D).map(chars -> IntStream.range(0, chars.length)
                        .mapToObj(j -> chars[j])
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    /**
     * Returns a string representation of the 2D board.
     *
     * @return a string representation of the board
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                sb.append(this.getPos(row, col)).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
