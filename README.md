# Othello Game with AI Player

![Othello Game](https://github.com/SemoMoh/Othello-Game-with-AI-player/assets/90259729/18050616-bce6-4cbd-8881-1d6be94a2fe9)

## Overview

This project is an implementation of the classic board game Othello (also known as Reversi) with an AI player. Othello is a two-player strategy game where the players take turns placing their colored discs on an 8x8 board with the goal of having the majority of discs of their color when the game ends.

## Features

- Play against the computer AI.
- Choose difficulty level for AI.
- Interactive command-line interface.
- Basic graphics for the game board.

## Technologies Used

- Java programming language.
- Object-oriented design principles.
- Minimax algorithm for AI decision-making.
- Git for version control.

## Setup

1. Clone the repository:

    ```
    git clone https://github.com/yourusername/Othello-Game-with-AI-player.git
    ```

2. Compile the Java source files:

    ```
    javac *.java
    ```

3. Run the game:

    ```
    java OthelloGame
    ```

## How to Play

- The game starts with an empty 8x8 board with two black and two white pieces arranged in the center.
- Players take turns placing their pieces on empty squares.
- A move is valid only if it results in the flipping of at least one of the opponent's pieces.
- The game ends when either the board is full or no player can make a valid move.
- The player with the most pieces of their color on the board wins.

## AI Player

- The AI player uses the Minimax algorithm with alpha-beta pruning for decision-making.
- The difficulty level can be adjusted to control the depth of the search tree explored by the AI.

## Contributors

- [Mahmoud Abdelraouf Mahmoud](https://github.com/Mahmoud-Abdelraouf)
- [Contributor ](https://github.com/contributor1)
- [Contributor 2](https://github.com/contributor2)
- [Omar Muhammad Mahmoud Al-Tahan](https://github.com/UmarTahanix)

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgements

- Inspiration: [Reference to any tutorials, articles, or resources used as inspiration for the project]
