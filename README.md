# Minesweeper - game

## Introduction

This repo was made for the "Software Development Methods" exam at the University of Trieste.

The goal of this project was to develop a game, practice Continuous Integration (CI), and apply automated testing and Test Driven Development (TDD) principles to iteratively enhance the code. Additionally, Agile methodology was utilized to manage the project in a collaborative group environment.

The game implemented is the classic Minesweeper.

This project is built using Gradle and programmed in Java.

## Structure

The project is organized into the following directories:

- ` cli `: Handles the functionality of the command line interface.
- ` handler `: Manages the game flow, board handling, and user commands.
- ` main `: Responsible for starting the game.
- ` model `: Contains all the essential structural components of the game, such as the modeling of cells and the board.


## Prerequisites
- The project was developed using Java 21. It utilizes features like records, which are only available starting from Java 14. Therefore, versions of Java prior to 14 are not compatible with this project.

- **Java Development Kit (JDK)**: You need to have Java 21 installed. You can download it from [Oracle's JDK download page](https://www.oracle.com/java/technologies/javase-downloads.html) or [AdoptOpenJDK](https://adoptopenjdk.net/).

## Run the Project

Follow these steps to set up and run the Minesweeper project:

### **_1. Running the .jar File_**

- Download the `MinesweeperSDM-1.0-SNAPSHOT.jar` file from the releases page. 
- Run the following command to execute the file:

```sh
 java -jar MinesweeperSDM-1.0-SNAPSHOT.jar 
 ```

### **_2. Using Gradle Wrapper_**
If you prefer to build, test, and run the project from the source code, follow these steps:

- **Clone the repository using Git:** 
    ```sh
    git clone https://github.com/PasqualiniGabriele/MinesweeperSDM.git
    ```

- **Build the Project:**  
    ```sh
    ./gradlew build 
    ```

- **Run the Game:**  
    ```sh
    ./gradlew run 
    ```
    **Note**: The game interface may remain at 75% loading because it expects to receive standard input. Be sure to provide the necessary input via the console.

- **Run the Tests:** 
    ```sh
    
    ./gradlew test
    ```


## Usage

### Objective
- The goal of Minesweeper is to uncover all the cells on the board without triggering any bombs. You must use logical deduction to avoid mines and reveal safe areas.

### How to Play
- Before you start playing, select the difficulty level. The available options are:

    - **Easy**: 9x9 board with 10 bombs.
    - **Medium**: 16x16 board with 40 bombs.
    - **Hard**: 16x30 board with 99 bombs.  

- The game board consists of cells. Each cell can either be uncovered or flagged.
- **Uncover a Cell**: Use the command `C x y`, where `(x, y)` are the coordinates of the cell you want to uncover.
- **Flag or Unflag a Cell**: Use the command `F x y`, where `(x, y)` are the coordinates of the cell you want to mark or unmark as a potential bomb.

### Game Over
- The game ends if you uncover a cell containing a bomb.


### Example Commands
- To _uncover_ the cell at coordinates (3, 4): `C 3 4`
- To _flag_ the cell at coordinates (5, 6): `F 5 6`
- To _unflag_ the cell at coordinates (5, 6): `F 5 6` (same command as flagging)

## License
- This project is licensed under the [MIT License](https://opensource.org/licenses/MIT). See the _license_ file for more details.

## Acknowledgements
For the graphical representation of the board, we took inspiration from a similar implementation available in an open-source project under the MIT License. Specifically, we used the approach for rendering the game board but made significant modifications to suit the needs of our project.

**Details:**
- **Repository URL**:  [\[URL to the project's repository\]](https://github.com/Squirrelbear/Minesweeper)

We used the approach for graphical rendering of the board as a reference; the rest of the project is original and developed independently. We thank the authors of the original project for their contribution to the open-source community.

## Authors
- **Gabriele Pasqualini**: https://github.com/PasqualiniGabriele
- **Anna Guccione**: https://github.com/pannafritta
- **Federico Marenco**: https://github.com/Marrenk
- **Laura Gallo**: https://github.com/LauraG88