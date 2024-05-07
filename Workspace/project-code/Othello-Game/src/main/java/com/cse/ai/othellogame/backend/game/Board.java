package com.cse.ai.othellogame.backend.game;

import java.util.ArrayList;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


/**
 * Represents the game board for an Othello game.
 * <p>
 * The board consists of 64 cells arranged in 8x8 grid. Each cell can contain one of the following values:
 * <ul>
 *     <li>DISK.WHITE for white disc</li>
 *     <li>DISK.BLACK for black disc</li>
 *     <li>DISK.HINT for hints where to play</li>
 *     <li>DISK.EMPTY for empty cell</li>
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
public class Board implements Cloneable{
    private DISK[] board;

    private boolean areThereAnyHints;

    /**
     * Constructs a new game board with currrent board state
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        Object newBoard = new Board();
        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++) {
                ((Board)newBoard).board[row*8+col] = board[row*8+col];
            }
        }
        return newBoard;
    }
    /**
     * gets List of positions of possible moves
     * <p>
     *     iterates on board to find positions with Disk.Hint value
     * </p>
     */

    public ArrayList<Point> getAllPossibleMoves() {
        ArrayList<Point> possibleMoves = new ArrayList<>();
//        DISK [] board = node.getBoard();
        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++) {
                if (board[row*8 + col] == DISK.HINT) {
                    possibleMoves.add(new Point(row,col));
                }
            }
        }
        return possibleMoves;
    }

    /**
     * Constructs a new game board with default dimensions and initializes its black and white disks,
     * also it shows the hint values for the first move of the game for the black.
     * areThereAnyHints's value is set false. 
     */
    public Board() {
        board = new DISK[64];
        // Init all with empty --> DISK.EMPTY
        IntStream.range(0, board.length).forEach(i -> board[i] = DISK.EMPTY);
        // put the starting position of black and white --> DISK.BLACK and DISK.WHITE
        setPos(DISK.WHITE, 3, 3);
        setPos(DISK.BLACK, 3, 4);
        setPos(DISK.BLACK, 4, 3);
        setPos(DISK.WHITE, 4, 4);
        // put the initial hints for the black --> DISK.HINT
        setPos(DISK.HINT, 2, 3);
        setPos(DISK.HINT, 3, 2);
        setPos(DISK.HINT, 4, 5);
        setPos(DISK.HINT, 5, 4);
        this.areThereAnyHints = false;
    }

    /**
     * Called when any player makes a move to update the board with the inserted position.
     * <p>
     * <b>Steps:</b>
     * <ol>
     *   <li>Check if the position is valid:
     *     <ul>
     *       <li>Ensure the position corresponds to one of the DISK.HINT positions.</li>
     *       <li>Ensure the position is within the range of the board.</li>
     *     </ul>
     *   </li>
     *   <li>Delete all old DISK.HINT characters from the board.</li>
     *   <li>Place the new disk at its position, representing the player's move.</li>
     *   <li>Update the other disks on the board according to the insertion position and its color.</li>
     *   <li>Generate new hints for the next move by the opposing player.</li>
     * </ol>
     * </p>
     *
     * @param color The player who played this turn (DISK.BLACK or DISK.WHITE).
     * @param row   The row index of the inserted position (0-based) (range: [0, 7]).
     * @param col   The column index of the inserted position (0-based) (range: [0, 7]).
     */
    public void updateBoard(DISK color, int row, int col) {
        //TODO: implement this method
        // valid position check
        if(outOfBounds(row, col) || board[row * 8 + col]!= DISK.HINT)
            return;
        // delete all old DISK.HINT characters from the board
        for(int i = 0; i < board.length; i++){
            if(board[i] == DISK.HINT)
                board[i] = DISK.EMPTY;
        }
        // place the new disk at its position, representing the player's move
        setPos(color, row, col);
        // update the other disks on the board according to the insertion position and its color
        updateOtherDisks(color, row, col);
        // generate new hints for the next move by the opposing player
        if(color == DISK.BLACK){
            color = DISK.WHITE;
        } else if (color == DISK.WHITE){
            color = DISK.BLACK;
        }
        generateNewHints(color);
    }

    /**
     * Called when any player makes a move to update the board with the inserted position.
     * <p>
     * <b>Steps:</b>
     * <ol>
     *   <li>Check if the position is valid:
     *     <ul>
     *       <li>Ensure the position corresponds to one of the DISK.HINT positions.</li>
     *       <li>Ensure the position is within the range of the board.</li>
     *     </ul>
     *   </li>
     *   <li>Delete all old DISK.HINT characters from the board.</li>
     *   <li>Place the new disk at its position, representing the player's move.</li>
     *   <li>Update the other disks on the board according to the insertion position and its color.</li>
     *   <li>Generate new hints for the next move by the opposing player.</li>
     * </ol>
     * </p>
     *
     * @param color The player who played this turn (DISK.BLACK or DISK.WHITE).
     * @param pos   The index of the inserted position (0-based) (range: [0, 63]).
     */
    public void updateBoard(DISK color, int pos) {
        int row = pos / 8;
        int col = pos % 8;
        this.updateBoard(color, row, col);
    }

    /**
     * Called after player's move is inserted on the board.
     * Updates disks opposite of inserted disk horizontally, vertically and diagonally.
     * used in the {@link #updateBoard(DISK, int, int)} method
     *
     * @param player The player who played this turn (DISK.BLACK or DISK.WHITE).
     * @param row   The row index of the inserted position (0-based) (range: [0, 7]).
     * @param col   The column index of the inserted position (0-based) (range: [0, 7]).
     */
    private void updateOtherDisks(DISK player, int row, int col){
        // Initial variables
        ArrayList<Integer> flipped = new ArrayList<Integer>(); // stores disks position to be flipped
        ArrayList<Integer> temp_flipped = new ArrayList<Integer>(); // temp to be used in each loop
        DISK opponent= (player == DISK.BLACK) ?  (DISK.WHITE) : (DISK.BLACK); // define opponent's color

        // Search Horizontally
        // Search left for opponent disks
        int adjacentDisk_LEFT = col - 1;
        while(!outOfBounds(row,adjacentDisk_LEFT) && board[8*row + adjacentDisk_LEFT] == opponent) {
            temp_flipped.add(8*row + adjacentDisk_LEFT);
            adjacentDisk_LEFT--;
        }
        // if adjacentDisk_LEFT stopped at player disk -> flip
        // else -> drop temp_flipped
        if(!outOfBounds(row, adjacentDisk_LEFT) && board[8*row + adjacentDisk_LEFT] == player) {
            flipped.addAll(temp_flipped);
        }

        // Search right for opponent disks
        temp_flipped.clear();
        int adjacentDisk_RIGHT = col + 1;
        while(!outOfBounds(row,adjacentDisk_RIGHT) && board[8*row + adjacentDisk_RIGHT] == opponent) {
            temp_flipped.add(8*row + adjacentDisk_RIGHT);
            adjacentDisk_RIGHT++;
        }
        // Check if adjacentDisk_RIGHT stopped at player disk -> flip, else -> drop flipped
        if(!outOfBounds(row, adjacentDisk_RIGHT) && board[8*row + adjacentDisk_RIGHT] == player){
            flipped.addAll(temp_flipped);
        }

        // Search Vertically
        // Search upwards for opponent
        temp_flipped.clear();
        int adjacentDisk_UP = row - 1;
        while(!outOfBounds(adjacentDisk_UP, col) && board[8*adjacentDisk_UP + col] == opponent) {
            temp_flipped.add(8*adjacentDisk_UP + col);
            adjacentDisk_UP--;
        }
        // Check if adjacentDisk_UP stopped at player disk -> flip, else -> drop flipped
        if(!outOfBounds(adjacentDisk_UP, col) && board[8*adjacentDisk_UP + col] == player) {
            flipped.addAll(temp_flipped);
        }

        // Search downwards for opponent
        temp_flipped.clear();
        int adjacentDisk_DOWN = row + 1;
        while(!outOfBounds(adjacentDisk_DOWN, col) && board[8*adjacentDisk_DOWN + col] == opponent) {
            temp_flipped.add(8*adjacentDisk_DOWN + col);
            adjacentDisk_DOWN++;
        }
        // Check if adjacentDisk_DOWN stopped at player disk -> flip, else -> drop flipped
        if(!outOfBounds(adjacentDisk_DOWN, col) && board[8*adjacentDisk_DOWN + col] == player) {
            flipped.addAll(temp_flipped);
        }

        // Search Diagonally
        // Search upwards and left for opponent
        temp_flipped.clear();
        int adjacentDisk_UP_LEFT = row - 1;
        adjacentDisk_LEFT = col - 1;
        while(!outOfBounds(adjacentDisk_UP_LEFT, adjacentDisk_LEFT) && board[8*adjacentDisk_UP_LEFT + adjacentDisk_LEFT] == opponent) {
            temp_flipped.add(8*adjacentDisk_UP_LEFT + adjacentDisk_LEFT);
            adjacentDisk_UP_LEFT--;
            adjacentDisk_LEFT--;
        }
        // Check if adjacentDisk_UP_LEFT stopped at player disk -> flip, else -> drop flipped
        if(!outOfBounds(adjacentDisk_UP_LEFT, adjacentDisk_LEFT) && board[8*adjacentDisk_UP_LEFT + adjacentDisk_LEFT] == player){
           flipped.addAll(temp_flipped);
        }

        // Search upwards and right for opponent
        temp_flipped.clear();
        int adjacentDisk_UP_RIGHT = row - 1;
        adjacentDisk_RIGHT = col + 1;
        while(!outOfBounds(adjacentDisk_UP_RIGHT, adjacentDisk_RIGHT) && board[8*adjacentDisk_UP_RIGHT + adjacentDisk_RIGHT] == opponent) {
            temp_flipped.add(8*adjacentDisk_UP_RIGHT + adjacentDisk_RIGHT);
            adjacentDisk_UP_RIGHT--;
            adjacentDisk_RIGHT++;
        }
        // Check if adjacentDisk_UP_RIGHT stopped at player disk -> flip, else -> drop flipped
        if(!outOfBounds(adjacentDisk_UP_RIGHT, adjacentDisk_RIGHT) && board[8*adjacentDisk_UP_RIGHT + adjacentDisk_RIGHT] == player){
            flipped.addAll(temp_flipped);
        }

        // Search downwards and left for opponent
        temp_flipped.clear();
        int adjacentDisk_DOWN_LEFT = row + 1;
        adjacentDisk_LEFT = col - 1;
        while(!outOfBounds(adjacentDisk_DOWN_LEFT, adjacentDisk_LEFT) && board[8*adjacentDisk_DOWN_LEFT + adjacentDisk_LEFT] == opponent) {
            temp_flipped.add(8*adjacentDisk_DOWN_LEFT + adjacentDisk_LEFT);
            adjacentDisk_DOWN_LEFT++;
            adjacentDisk_LEFT--;
        }
        // Check if adjacentDisk_DOWN_LEFT stopped at player disk -> flip, else -> drop flipped
        if(!outOfBounds(adjacentDisk_DOWN_LEFT, adjacentDisk_LEFT) && board[8*adjacentDisk_DOWN_LEFT + adjacentDisk_LEFT] == player){
            flipped.addAll(temp_flipped);
        }

        // Search downwards and right for opponent
        temp_flipped.clear();
        int adjacentDisk_DOWN_RIGHT = row + 1;
        adjacentDisk_RIGHT = col + 1;
        while(!outOfBounds(adjacentDisk_DOWN_RIGHT, adjacentDisk_RIGHT) && board[8*adjacentDisk_DOWN_RIGHT + adjacentDisk_RIGHT] == opponent) {
            temp_flipped.add(8*adjacentDisk_DOWN_RIGHT + adjacentDisk_RIGHT);
            adjacentDisk_DOWN_RIGHT++;
            adjacentDisk_RIGHT++;
        }
        // Check if adjacentDisk_DOWN_RIGHT stopped at player disk -> flip, else -> drop flipped
        if(!outOfBounds(adjacentDisk_DOWN_RIGHT, adjacentDisk_RIGHT) && board[8*adjacentDisk_DOWN_RIGHT + adjacentDisk_RIGHT] == player){
            flipped.addAll(temp_flipped);
        }

        // Flip disks
        for(int i = 0; i < flipped.size(); i++){
            board[flipped.get(i)] = player;
        }
    }


    /**
     * Generates new hints(if there) for the next move by analyzing the current state of the board, and sets the flag true.
     * used in the {@link #updateBoard(DISK, int, int)} method.
     *
     * @param colorToPlay the next players color (DISK.BLACK or DISK.WHITE)
     */
    public void generateNewHints(DISK colorToPlay) {
        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++) {
                if (board[row*8 + col] == DISK.EMPTY) {
                    if (isValid(row, col, colorToPlay)) {
                        board[row * 8 + col] = DISK.HINT;
                        setAreThereAnyHints(true);
                    }
                }
            }
        }
    }

    /**
     * Checks if the given row and column indices are out of bounds of an 8x8 board.
     * @param r The row index.
     * @param c The column index.
     * @return true if the indices are out of bounds, false otherwise.
     */
    private boolean outOfBounds(int r, int c){
        if(r < 0 || r >= 8 || c < 0 || c >= 8)
            return true;
        return false;
    }

    /**
     * Checks if a move in a specified direction (dx, dy) from a given position (r, c)
     * can be translated into the current player's color.
     * @param r The starting row index.
     * @param c The starting column index.
     * @param dx The change in row index for each step.
     * @param dy The change in column index for each step.
     * @param colorToPlay The color of the current player.
     * @return true if the move can be translated into the current player's color, false otherwise.
     * @throws ArrayIndexOutOfBoundsException if the move results in an out-of-bounds access.
     */
    private boolean translate(int r, int c, int dx, int dy, DISK colorToPlay) throws ArrayIndexOutOfBoundsException{
        try {
            while (!outOfBounds(r, c)) {
                r += dx;
                c += dy;
                if (board[r * 8 + c] == DISK.EMPTY || board[r * 8 + c] == DISK.HINT)
                    return false;
                else if (board[r * 8 + c] == colorToPlay)
                    return true;
            }
            return false;
        }
        catch(ArrayIndexOutOfBoundsException e){
            return false;
        }
    }

    /**
     * Checks if a move at the specified row and column indices is valid for the current player.
     * @param row The row index.
     * @param col The column index.
     * @param colorToPlay The color of the current player.
     * @return true if the move is valid for the current player, false otherwise.
     */
    private boolean isValid(int row, int col, DISK colorToPlay){
        DISK opponentToPlay = (colorToPlay == DISK.WHITE) ? DISK.BLACK : DISK.WHITE;
        boolean result;
        if(!outOfBounds((row-1),(col-1)) && (board[(row-1)*8 + (col-1)] == opponentToPlay)){
            result = translate(row,col,-1,-1,colorToPlay);
            if(result)
                return result;
        }
        if(!outOfBounds((row-1),(col)) && (board[(row-1)*8 + (col)] == opponentToPlay)){
            result =  translate(row,col,-1,0,colorToPlay);
            if(result)
                return result;
        }
        if(!outOfBounds((row-1),(col+1)) && (board[(row-1)*8 + (col+1)] == opponentToPlay)){
            result =  translate(row,col,-1,1,colorToPlay);
            if(result)
                return result;
        }
        if(!outOfBounds((row),(col-1)) && (board[(row)*8 + (col-1)] == opponentToPlay)){
            result = translate(row,col,0,-1,colorToPlay);
            if(result)
                return result;
        }
        if(!outOfBounds((row),(col+1)) && (board[(row)*8 + (col+1)] == opponentToPlay)){
            result = translate(row,col,0,1,colorToPlay);
            if(result)
                return result;
        }
        if(!outOfBounds((row+1),(col-1)) && (board[(row+1)*8 + (col-1)] == opponentToPlay)){
            result = translate(row,col,1,-1,colorToPlay);
            if(result)
                return result;
        }
        if(!outOfBounds((row+1),(col)) && (board[(row+1)*8 + (col)] == opponentToPlay)){
            result = translate(row,col,1,0,colorToPlay);
            if(result)
                return result;
        }
        if(!outOfBounds((row+1),(col+1)) && (board[(row+1)*8 + (col+1)] == opponentToPlay)){
            result = translate(row,col,1,1,colorToPlay);
            if(result)
                return result;
        }
        return false;
    }

    /**
     * This method removes all the hints for the previous player, and it must be called before
     * changing the turn.
     *
     * @return void
     */
    public void removeAllHints(){
        for(int row = 0; row < 8; row++){
            for(int col = 0; col < 8; col++) {
                if (board[row*8 + col] == DISK.HINT) {
                    board[row * 8 + col] = DISK.EMPTY;
                }
            }
        }
        setAreThereAnyHints(false);
    }

    /**
     * This method returns the current value of the flag
     * areThereAnyHints.
     *
     * @return the current value of the flag, whether true or false
     */
    public boolean isAreThereAnyHints() {
        return areThereAnyHints;
    }

    /**
     * This method changes the current value of the flag to the desired value.
     *
     * @param  areThereAnyHints the desired value for the flag
     * @return  void
     */
    public void setAreThereAnyHints(boolean areThereAnyHints) {
        this.areThereAnyHints = areThereAnyHints;
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
        int black = 0;
        int white = 0;
        boolean ended = true;
        for (DISK d : board){
            if(d == DISK.HINT){
               ended = false;
            }
            if(d == DISK.BLACK){
                black++;
            }
            if(d == DISK.WHITE){
                white++;
            }
        }
        return ended;
    }

    /**
     * Gets the winner of the Othello game.
     * <p>
     * This method determines and returns the winner of the Othello game based on the scores of the white
     * and black players. The player with the higher score is declared the winner. If the scores are equal,
     * the game is considered a tie.
     * </p>
     *
     * @return The color of the winning player (DISK.WHITE for white, DISK.BLACK for black, 'T' for tie).
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
        return getScore(DISK.WHITE);
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
        return getScore(DISK.BLACK);
    }

    /**
     * Gets the score of the specified player.
     * <p>
     * This method calculates and returns the score of the player specified by the {@code color} parameter
     * in the Othello game. The score represents the number of discs (pieces) of the specified player
     * currently on the board.
     * </p>
     *
     * @param color The color of the player whose score is to be calculated (DISK.WHITE for white, DISK.BLACK for black).
     * @return The score of the specified player.
     */
    public int getScore(DISK color) {
        int count = 0;
        for (DISK c : board) {
            if (c == color) {
                count++;
            }
        }
        return count;
    }

    /**
     * Sets a position at the specified row and column with the given value.
     *
     * @param value the value of the disc (DISK.WHITE, DISK.BLACK, DISK.HINT, or DISK.EMPTY)
     * @param row   the row index (0-based) (range: [0, 7])
     * @param col   the column index (0-based) (range: [0, 7])
     */
    public void setPos(DISK value, int row, int col) {
        setPos(value, row * 8 + col);
    }

    /**
     * Sets a position at the specified position with the given value.
     *
     * @param value the value of the disc (DISK.WHITE, DISK.BLACK, DISK.HINT, or DISK.EMPTY)
     * @param pos   the position index (0-based) (range: [0, 63])
     */
    public void setPos(DISK value, int pos) {
        board[pos] = value;
    }

    /**
     * Gets the value of the position at the specified row and column.
     *
     * @param row the row index (0-based) (range: [0, 7])
     * @param col the column index (0-based) (range: [0, 7])
     * @return the value of the disc at the specified position
     */
    public DISK getPos(int row, int col) {
        return getPos(row * 8 + col);
    }

    /**
     * Gets the value of the position at the specified position.
     *
     * @param pos the position index (0-based) (range: [0, 63])
     * @return the value of the disc at the specified position
     */
    public DISK getPos(int pos) {
        return board[pos];
    }

    /**
     * Gets the 1D array representation of the board.
     *
     * @return the 1D array representing the board
     */
    public DISK[] getBoard() {
        return board;
    }

    /**
     * Gets the 2D array representation of the board.
     *
     * @return the 2D array representing the board
     */
    public DISK[][] getBoard2D() {
        DISK[][] board2D = new DISK[8][8];
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
    public List<DISK> getBoardList() {
        return IntStream.range(0, board.length)
                .mapToObj(i -> board[i])
                .collect(Collectors.toList());
    }

    /**
     * Gets the 2D list representation of the 2D board.
     *
     * @return the 2D list representing the board
     */
    public List<List<DISK>> getBoardList2D() {
        DISK[][] board2D = this.getBoard2D();
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
