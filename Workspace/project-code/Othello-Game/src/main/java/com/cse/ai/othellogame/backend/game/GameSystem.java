package com.cse.ai.othellogame.backend.game;

import com.cse.ai.othellogame.HelloApplication;
import com.cse.ai.othellogame.backend.player.AIPlayer;
import com.cse.ai.othellogame.backend.player.HumanPlayer;
import com.cse.ai.othellogame.backend.player.Player;
import com.cse.ai.othellogame.gui.gamescreen.GameScreen;

/**
 * The game's backend.
 * TODO: implement this class
 */
public class GameSystem {
    private Player playerBlack;
    private Player playerWhite;

    private Board board;
    private boolean blackTurn = true;

    private GameScreen gameGUI;

    public GameSystem(Player playerBlack, Player playerWhite, Board board) {
        this.board = board;
        this.playerBlack = playerBlack;
        this.playerWhite = playerWhite;

        //TODO: update players to accept names
        String blackName = "";
        String whiteName = "";
        if (playerBlack instanceof AIPlayer) {
            blackName = "AI Player";
        } else {
            blackName = "Human Player";
            ((HumanPlayer) playerBlack).setGameGUI(gameGUI);
        }

        if (playerWhite instanceof AIPlayer) {
            whiteName = "AI Player";
        } else {
            whiteName = "Human Player";
            ((HumanPlayer) playerWhite).setGameGUI(gameGUI);
        }

        this.gameGUI = new GameScreen(board, blackName, whiteName);

        if (playerBlack instanceof HumanPlayer) {
            ((HumanPlayer) playerBlack).setGameGUI(gameGUI);
        }

        if (playerWhite instanceof HumanPlayer) {
            ((HumanPlayer) playerWhite).setGameGUI(gameGUI);
        }
        HelloApplication.scene.setRoot(gameGUI);
        //startGame();
    }

    public void startGame() {
        while (!board.gameEnded()) {
            if (blackTurn) {
                board.updateBoard(DISK.BLACK, playerBlack.makeMove());
            } else {
                board.updateBoard(DISK.BLACK, playerWhite.makeMove());
            }

            blackTurn = !blackTurn;
            gameGUI.update(true);
        }
    }

    public GameScreen getGameScreen() {
        return this.gameGUI;
    }
}
