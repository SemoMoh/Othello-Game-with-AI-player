# Othello Game with AI Player

<div align="center">
    <img src="https://github.com/SemoMoh/Othello-Game-with-AI-player/assets/131382032/048ba0d0-15ba-48b0-97e7-8a257cddcaef" alt="Othello GIF">
</div>


## Overview

This project is an implementation of the classic board game Othello (also known as Reversi) with an AI player. Othello is a two-player strategy game where the players take turns placing their colored discs on an 8x8 board with the goal of having the majority of discs of their color when the game ends.

## Links
- [JavaDocs](https://semomoh.github.io/Othello-Game-with-AI-player/com.cse.ai.othellogame/module-summary.html)
- [Game Demo](https://www.youtube.com/watch?v=IXwt6i9gb7w&lc=Ugxq-iJzZZxt094c0q54AaABAg) 
- [Download Game](https://drive.google.com/drive/u/1/folders/1cmy4UNAc_ssc8r2DEd2ZSSEjbg6zXx_c)
    - Choose the version with jre if you don't have JDK 17 or latest versions.

## Features

- Play against the computer AI.
- Choose difficulty level for AI.
- Interactive interface.
- Basic graphics for the game board.

## Technologies Used

- Java programming language.
- Object-oriented design principles.
- Minimax algorithm for AI decision-making.
- Git for version control.
- Maven for project build and dependency management.
- JavaFX for GUI.


Note: If the device where the program will be setup has Windows 10 version or one of the the older Windows versions, please make sure that the display size of the device is at 100% from the settings of the device.

## How to Play

1. **Main Menu**: Configure players, choosing either human or AI, and set AI difficulty.
2. **Game Screen**: 
    - The game starts with an initial setup of four discs in the center.
    - Players take turns placing their discs on the board.
    - Valid moves must flip at least one of the opponent's discs.
    - If no valid moves are available, the turn is passed to the opponent.
    - The game ends when the board is full or no moves are possible.
    - The player with the most discs of their color wins.
3. **Result Screen**: Displays the winner and provides options to restart or return to the main menu.


## AI Player

- The AI player uses the Minimax algorithm with alpha-beta pruning for decision-making.
- The difficulty level can be adjusted to control the depth of the search tree explored by the AI.
- 
### AI Player default names:
- **Black AI Player**: Batman
- **White AI Player**: Joker

## Contributors 🤝
<table align="center">
  <tbody>
    <tr>
      <td align="center" valign="top" width="25%"><a href="https://github.com/d3cypherd"><img src="https://avatars.githubusercontent.com/u/108687433?v=4" width="100px;" alt="Abdelrahman"/><br /><sub><b>Abdelrahman Elsayed</b></sub></a><br/></td>
      <td align="center" valign="top" width="25%"><a href="https://github.com/jokr0000"><img src="https://avatars.githubusercontent.com/u/131316529?v=4" width="100px;" alt="Mohamed"/><br /><sub><b>Mohamed Ayman</b></sub></a><br /></td>
      <td align="center" valign="top" width="25%"><a href="https://github.com/Karim-308"><img src="https://avatars.githubusercontent.com/u/105867500?v=4" width="100px;" alt="Karim"/><br /><sub><b>Karim Ibrahim</b></sub></a><br /></td>
      <td align="center" valign="top" width="25%"><a href="https://github.com/Mahmoud-Abdelraouf"><img src="https://avatars.githubusercontent.com/u/90259729?v=4" width="100px;" alt="Mahmoud"/><br /><sub><b>Mahmoud Abdelraouf</b></sub></a><br /></td>
    </tr>
    <tr>
      <td align="center" valign="top" width="25%"><a href="https://github.com/Omar5033"><img src="https://avatars.githubusercontent.com/u/132155550?v=4" width="100px;" alt="Omar"/><br /><sub><b>Omar Salah</b></sub></a><br /></td>
      <td align="center" valign="top" width="25%"><a href="https://github.com/Sarrah-Sherif"><img src="https://github.com/Sarrah-Sherif.png" width="100px;" alt="Sarrah"/><br /><sub><b>Sarrah Sherif</b></sub></a><br /></td>
      <td align="center" valign="top" width="25%"><a href="https://github.com/tasneem-elhady"><img src="https://github.com/tasneem-elhady.png" width="100px;" alt="Tasneem"/><br /><sub><b>Tasneem Salama</b></sub></a><br /></td>
      <td align="center" valign="top" width="25%"><a href="https://github.com/UmarTahanix"><img src="https://github.com/UmarTahanix.png" width="100px;" alt="Umar"/><br /><sub><b>Omar Al-Tahan</b></sub></a><br /></td>
    </tr>
      <tr>
          <td align="center" valign="top" width="25%"><a href="https://github.com/SemoMoh"><img src="https://github.com/SemoMoh.png" width="100px;" alt="Eslam"/><br /><sub><b>Eslam Mohamed</b></sub></a><br /></td>
      </tr>
  </tbody>
</table>

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
---

Note: For optimal display, ensure that your device's display size is set to 100%, especially if you are using Windows 10 or an older version of Windows.

