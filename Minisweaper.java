package aggrigation.minisweaper;

import java.io.*;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;

/**
 *
 * @author ASUS
 */
public class Minisweaper extends JPanel {

    public static cell[][] game = new cell[10000][10000];
    public static int score1 = 0, score2 = 0;
    public static boolean multiPlayerMode = false;
    public static int allBombsNumber;
    public static int player1Score;
    public static int player2Score;
    public static int playerTurn = 1;
    public static int cMoves = 1;
    public static boolean computerMode = false;

    // Rows and Cols Nums
    public static int rows;
    public static int cols;

    public Minisweaper(boolean multi, boolean comp, int r, int c, int b) {
        // Reset rows and cols number
        rows = r;
        cols = c;
        allBombsNumber = b;

        // Set MultiPlayer Mode
        multiPlayerMode = multi;

        // Set computer Mode
        computerMode = comp;

        // Create Cell Grid
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                game[i][j] = new cell();
            }
        }
    }

    public static boolean ComputerMove() {
        Random r = new Random();
        int x, y;
        if (cMoves % 5 == 0) {
            do {
                x = r.nextInt(rows);
                y = r.nextInt(cols);
            } while (!game[x][y].show);
            if (game[x][y].bomb) {
                System.out.println("You Won");
                System.out.println("Your Score is: ");
                System.out.println(player1Score);
                printNshow();
                cMoves++;
                return false;
            }
            floodfill(x, y);
            return true;
        } else {
            do {
                x = r.nextInt(rows);
                y = r.nextInt(cols);
            } while (game[x][y].show || game[x][y].bomb);
            System.out.println(game[x][y].bomb);
            System.out.println(game[x][y].show);
            floodfill(x, y);
            cMoves++;
            return true;
        }
    }

    public static void calculatePlayer1Score(int score) {
        player1Score += score;
    }

    public static void calculatePlayer2Score(int score) {
        player2Score += score;
    }

    public static void print(cell[][] game) {
        System.out.println();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                game[i][j].PrintCell();
                System.out.print("  ");
            }
            System.out.println();
        }
    }

    public static int calculateScore() {
        int score = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (game[i][j].show) {
                    score += game[i][j].NoBomb;
                }
                if (game[i][j].isFlaged) {
                    if (game[i][j].bomb) {
                        score += 5;
                    } else {
                        score -= 1;
                    }
                }
            }
        }
        return score;
    }

    public static boolean allNumShown() {
        int v = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!game[i][j].bomb) {
                    if (game[i][j].show) {
                        v++;
                    }
                }
            }
        }
        return v == (rows * cols) - allBombsNumber;
    }

    public static void printNshow() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                game[i][j].show = true;
                game[i][j].PrintCell();
                System.out.print("  ");
            }
            System.out.println();
        }
    }

    public static void callNoBomb(cell[][] game) {
        int[] mox = {1, 1, 1, 0, 0, -1, -1, -1};
        int[] moy = {1, -1, 0, 1, -1, 0, 1, -1};
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (game[i][j].bomb) {
                    for (int k = 0; k < 8; k++) {
                        if ((i + mox[k]) >= 0 && (i + mox[k]) < rows && j + moy[k] >= 0 && j + moy[k] < cols) {
                            game[i + mox[k]][j + moy[k]].NoBomb++;
                        }
                    }
                }
            }
        }
    }

    public static boolean MoveisSafe(int x, int y) {
        return (x >= 0 && x < rows && y >= 0 && y < cols);
    }

    public static void floodfill(int x, int y) {
        int[] movex = {1, 1, 1, 0, 0, -1, -1, -1};
        int[] movey = {1, -1, 0, 1, -1, 0, 1, -1};
        if (!MoveisSafe(x, y) || game[x][y].bomb || game[x][y].show) {
            return;
        }
        game[x][y].show = true;
        // Calculate score in multiplayer mode
        if (multiPlayerMode) {
            if (playerTurn == 1) {
                calculatePlayer1Score(game[x][y].NoBomb);
            }
            if (playerTurn == 2) {
                calculatePlayer2Score(game[x][y].NoBomb);
            }
        } else {
            // Calculate score in single player game
            calculatePlayer1Score(game[x][y].NoBomb);
        }
        if (game[x][y].NoBomb == 0) {
            for (int i = 0; i < 8; i++) {
                floodfill(x + movex[i], y + movey[i]);
            }
        }
    }

    public static void setBombsPlaces(int x, int y) {
        for (int i = 0; i < allBombsNumber; i++) {
            Random rand = new Random();
            // Obtain a number between [0 - 9].
            int box = rand.nextInt(rows);
            int boy = rand.nextInt(cols);
            if (box == x && boy == y) {
                i--;
                continue;
            }
            game[box][boy].bomb = true;
        }
        callNoBomb(game);
    }

    public static void startGame() {
        Scanner in = new Scanner(System.in);
        int k = 0;
        while (!allNumShown()) {
            print(game);
            int x, y;
            try {
                if (multiPlayerMode) {

                    if (playerTurn == 1) {
                        System.out.println("Player 1 Enter a cell coordinates ( 0,0 is the first cell)");
                    } else if (playerTurn == 2) {
                        System.out.println("Player 2 Enter a cell coordinates ( 0,0 is the first cell)");
                    }
                } else if (computerMode) {
                    if (k % 2 == 0) {
                        System.out.println("Enter a cell coordinates ( 0,0 is the first cell)");
                    } else {
                        if (ComputerMove()) {
                            k++;
                            continue;
                        } else {
                            k++;
                            break;
                        }
                    }

                } else {
                    System.out.println("Enter a cell coordinates ( 0,0 is the first cell)");
                }
                String m = in.next();
                String[] c = new String[2];
                c = m.split(",");
                x = Integer.parseInt(c[0]);
                y = Integer.parseInt(c[1]);
            } catch (NumberFormatException e) {
                System.out.println(e);
                continue;
            }
            // Start playing the game
            int d;
            do {
                System.out.println("Enter an action");
                System.out.println("1 to Flag a cell");
                System.out.println("2 to Press a cell");
                System.out.println("3 to unflag a cell");
                System.out.println("4 to exit");
                System.out.println("5 to go back");
                try {
                    d = in.nextInt();
                    if (d == 1 || d == 2 || d == 3 || d == 4) {
                        break;
                    }
                } catch (Exception e) {
                }
                System.out.println("wrong number");
            } while (true);
            if (d == 1) {
                if (!game[x][y].isFlaged) {
                    game[x][y].isFlaged = true;
                    game[x][y].show = true;
                    if (multiPlayerMode) {
                        if (game[x][y].bomb == true) {
                            if (playerTurn == 1) {
                                calculatePlayer1Score(5);
                            } else {
                                calculatePlayer2Score(5);
                            }
                        } else {
                            if (playerTurn == 1) {
                                calculatePlayer1Score(-1);
                            } else {
                                calculatePlayer2Score(-1);
                            }
                        }
                    }
                } else {
                    System.out.println("(" + x + "," + y + ") is already flagged");
                }
            }

            // Click on a cell
            if (d == 2) {
                if (game[x][y].bomb) {

                    // In MultiPlayer Mode show both players scores
                    if (multiPlayerMode) {
                        // Select winner
                        if (playerTurn == 1) {
                            System.out.println("The winner is: Player 2");
                        } else {
                            System.out.println("The winner is: Player 1");
                        }
                    } else {
                        // Print single player score
                        System.out.println("You lost!");
                        System.out.println("Your Score is: ");
                        System.out.println(player1Score);
                    }

                    printNshow();
                    break;
                }

                // Set bomb places on first click only
                if (k == 0) {
                    setBombsPlaces(x, y);
                }

                floodfill(x, y);
            }
            if (d == 3) {
                if (game[x][y].isFlaged) {
                    game[x][y].isFlaged = false;
                    game[x][y].show = false;
                    if (multiPlayerMode) {
                        if (!game[x][y].bomb) {
                            if (playerTurn == 1) {
                                calculatePlayer1Score(5);
                            } else {
                                calculatePlayer2Score(5);
                            }
                        } else {
                            if (playerTurn == 1) {
                                calculatePlayer1Score(-5);
                            } else {
                                calculatePlayer2Score(-5);
                            }
                        }
                    } else {
                        System.out.println("(" + x + "," + y + ") is not flagged");
                    }
                }
            }
            if (d == 4) {
                System.out.println("enter 1 if you want to return to the game");
                System.out.println("enter 2 if you want to leave without a save");
                System.out.println("enter 3 if you want to save and leave the game");
                try {
                    int h = in.nextInt();
                    if (h == 1) {
                        continue;
                    } else if (h == 2) {
                        if (multiPlayerMode) {
                            System.out.println("Player 1 score is " + score1);
                            System.out.println("Player 2 score is " + score2);
                        } else {
                            System.out.println("Your Score is " + calculateScore());
                        }
                        break;
                    } else if (h == 3) {
                        System.out.println("Enter file name (must have txt extintion)");
                        String name = in.next();

                        try {
                            File f = new File("D:\\ITE\\سنة 2\\برمجة 3\\minisweaper\\Savedgames\\" + name);
                            if (f.createNewFile()) {
                                FileOutputStream fw = new FileOutputStream(f);
                                String s = "";
                                for (int i = 0; i < rows; i++) {
                                    for (int j = 0; j < cols; j++) {
                                        if (game[i][j].show) {
                                            if (game[i][j].isFlaged) {
                                                s += "F ";
                                            } else {
                                                s += game[i][j].NoBomb;
                                                s += " ";
                                            }
                                        } else {
                                            s += "? ";
                                        }
                                    }
                                    s += "\r\n";
                                }
                                fw.write(s.getBytes());
                                fw.flush();
                                fw.close();
                                System.out.println("Your game is saved");
                                break;
                            }
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println(e);
                }
            }
            if (d == 5) {
                continue;
            }

            // change player turn in multiPlayerMode
            if (multiPlayerMode) {
                if (playerTurn == 1) {
                    playerTurn = 2;
                } else {
                    playerTurn = 1;
                }
            }
            k++;
        }
        if (allNumShown()) {
            if (multiPlayerMode) {
                System.out.println("Player 1 score is " + player1Score);
                System.out.println("Player 2 score is " + player2Score);
            } else {
                System.out.println("You Won!");
                System.out.println("Your Score is " + calculateScore());
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);

        Menu men = new Menu();

        // Starting Statements
        System.out.println("welcome to minesweaper by y3hw");
        System.out.println("if you want to load a saved game enter 1");
        System.out.println("if you want to start a new multiPlayer game enter 2");
        System.out.println("if you want to play against an ai enter 3");
        System.out.println("if you want to start a new single player game enter any num");

        int w = in.nextInt();

        // Load Game
        if (w == 1) {
            game = men.load(game);

            startGame();
        } else {

            // MultiPlayer Mode
            if (w == 2) {
                multiPlayerMode = true;
            } // case playing vs computer
            else if (w == 3) {
                computerMode = true;
            }

            // Select Game Level
            System.out.println("Please select game level: ");
            System.out.println("For easy (10, 10), enter: 1");
            System.out.println("For medium (9, 16) , enter: 2");
            System.out.println("For expert (32 x 32), enter: 3");

            int levelInput = in.nextInt();

            if (levelInput == 1) {
                rows = 9;
                cols = 9;
                allBombsNumber = 10;

            } else if (levelInput == 2) {
                rows = 9;
                cols = 16;
                allBombsNumber = 16;
            } else if (levelInput == 3) {
                rows = 32;
                cols = 32;
                allBombsNumber = 32;
            } else {
                System.out.println("Something went wrong!");
                return;
            }

            // Create Cell Grid
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    game[i][j] = new cell();
                }
            }

            // Start Game
            startGame();

        }

    }
}
