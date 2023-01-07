package aggrigation.minisweaper;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import javax.swing.*;
import java.util.*;

public class Grid extends JPanel {

    public static int NUM_ROWS = 10;
    public static int NUM_COLS = 10;
    public static final int NUM_MINES = 10; // Number of mines to be placed
    public static final Random RANDOM = new Random(); // Random number generator
    public cell[][] buttons;
    public Minisweaper minis;
    public int w = 0, k = 0;
    public static int counter;
    public boolean multiPlayerMode;
    public JLabel playerLabel;
    public int playerTurn = 1;

    // The constructer of the class
    public Grid(boolean multi, boolean compMode, int rows, int cols, int bombsNum) {

        // Set MultiPlayerMode
        multiPlayerMode = multi;
        playerLabel = new JLabel("player: 1 turn");
        playerLabel.setForeground(Color.red);

        // Reset Counter
        counter = 0;

        // Reset Num_ROWS and NUM_COLS
        NUM_ROWS = rows;
        NUM_COLS = cols;

        // Create Outer Panel
        JPanel outerPanel = new JPanel();
        outerPanel.setLayout(new FlowLayout());
        outerPanel.setPreferredSize(new Dimension(720, 400));
        outerPanel.setBackground(Color.BLACK);

        // Create Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(720, 400));
        buttonPanel.setLayout(new GridLayout(NUM_ROWS, NUM_COLS));

        // Add the outer panel to the center of the frame
        add(outerPanel);

        // Add the button panel to the center of the outer panel
        outerPanel.add(buttonPanel);

        // in MultiPlayerMode => add player turn label
        if (multiPlayerMode) {
            outerPanel.add(playerLabel);
        }

        // Create Minis
        minis = new Minisweaper(multi, compMode, rows, cols, bombsNum);

        // Initial State
        for (int r = 0; r < NUM_ROWS; r++) {
            for (int c = 0; c < NUM_COLS; c++) {
                // Add Each Cell from Minis to buttons panel
                buttonPanel.add(minis.game[r][c]);
                // Add event listener to left click
//                minis.game[r][c].addActionListener(new ButtonListener(r, c));

                // Add event listener to mouse-right click
                minis.game[r][c].addMouseListener(new MouseRight(r, c));
            }
        }

    }

    public class MouseRight implements MouseListener {

        int x, y;

        public MouseRight(int xCord, int yCord) {
            this.x = xCord;
            this.y = yCord;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if (minis.allNumShown()) {
                // Winning Panel
                System.out.println("you won!");

                // Change player label in multi player mode
                if (multiPlayerMode) {
                    // player 1 turn
                    if (playerTurn == 1) {
                        playerLabel.setText("player: 2 won");
                    } // player 2 turn
                    else {
                        playerLabel.setText("player: 1 won");
                    }
                }
                return;
            } else if (SwingUtilities.isRightMouseButton(e)) {
                rightClickEvent(x, y);
            } else {
                leftClickEvent(x, y);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

    }

    // Show All Buttons Values
    public void showAll() {
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                if (minis.game[i][j].isFlaged) {
                    minis.game[i][j].setText("F");
                } else if (minis.game[i][j].bomb) {
                    minis.game[i][j].setText("B");
                    minis.game[i][j].setBackground(new Color(255, 0, 0));
                } else {
                    minis.game[i][j].setText(Integer.toString(minis.game[i][j].NoBomb));
                }
            }
        }
    }

    // After calling floodfill in minis, change each button value in panel
    public void setShowedCellsText() {
        for (int i = 0; i < NUM_ROWS; i++) {
            for (int j = 0; j < NUM_COLS; j++) {
                if (minis.game[i][j].show) {
                    // Show cell value
                    minis.game[i][j].setText(Integer.toString(minis.game[i][j].NoBomb));

                    // Change cell color
                    minis.game[i][j].setBackground(new Color(221, 221, 221));

                    // Make cell enables
                    minis.game[i][j].setEnabled(false);
                }
            }
        }
    }

    public void rightClickEvent(int r, int c) {

        if (!minis.game[r][c].isFlaged) {
            minis.game[r][c].isFlaged = true;
            minis.game[r][c].setText("F");
        } else {
            minis.game[r][c].isFlaged = false;
            minis.game[r][c].setText("");
        }

        // Change player label in multi player mode
        if (multiPlayerMode) {
            // player 1 turn
            if (playerTurn == 1) {
                playerTurn = 2;
                playerLabel.setText("player: 2 turn");
            } // player 2 turn
            else {
                playerTurn = 1;
                playerLabel.setText("player: 1 turn");
            }
        }

        // Computer mode
        if (minis.computerMode) {
            System.out.println("counter: ");
            System.out.println(counter);
            try {
                minis.ComputerMove();
            } catch (Exception e) {
            }

            setShowedCellsText();
        }
    }

    private void leftClickEvent(int x, int y) {

//            cell button = (cell) e.getSource();
        // counter used to call setBombsPlaces one time only (only on first click)
        if (counter == 0) {

            // Set bombs places
            minis.setBombsPlaces(x, y);

        }

        // Change player label in multi player mode
        if (multiPlayerMode) {
            // player 1 turn
            if (playerTurn == 1) {
                playerTurn = 2;
                playerLabel.setText("player 2 turn");
            } // player 2 turn
            else {
                playerTurn = 1;
                playerLabel.setText("player 1 turn");
            }
        }

        // Count number of elligble clicks (also prevent calling setBombsPlaces again)
        counter++;

        // Prevent doble click on same button
        minis.game[x][y].setEnabled(false);

        // Change clicked button color
        minis.game[x][y].setBackground(new Color(221, 221, 221));

        // Show cell value
        minis.game[x][y].setText(Integer.toString(minis.game[x][y].NoBomb));

        // Check if we clicked on bomb button
        if (minis.game[x][y].bomb) {
            //JLabel Creation
//                JLabel mine = new JLabel();
            // Sets the image to be displayed as an icon
//                mine.setIcon(new ImageIcon("D:\\ITE\\سنة 2\\برمجة 3\\minisweaper\\src\\main\\java\\aggrigation\\minisweaper\\mine.jpg"));
//                mine.setBounds(minis.game[this.x][this.y].getX(), minis.game[this.x][this.y].getY(), minis.game[this.x][this.y].getWidth(), minis.game[this.x][this.y].getHeight());
//                minis.game[this.x][this.y].add(mine);
//                minis.game[this.x][this.y].setVisible(true);
            // Sets the location and dimention of the image

            minis.game[x][y].setBackground(new Color(255, 0, 0));
            showAll();

            // Change player label in multi player mode
            if (multiPlayerMode) {
                // player 1 turn
                if (playerTurn == 1) {
                    playerLabel.setText("player: 1 won");
                } // player 2 turn
                else {
                    playerLabel.setText("player: 2 won");
                }
            }

            // open losing frame
            Losing lose = new Losing(minis.calculateScore());

        } else {
            if (minis.computerMode) {
                if (counter % 2 != 0) {
                    try {
                        minis.ComputerMove();
                        counter++;
                    } catch (Exception e) {
                    }
                    
                } else {
                    minis.floodfill(x, y);
                    setShowedCellsText();
                }
            }
            minis.floodfill(x, y);
            setShowedCellsText();
        }

        if (false) {
            // Handle the event if the button is a mine
            // ...

            JFrame frame2 = new JFrame("Minesweeper");
            frame2.setSize(400, 400);
            frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ImageIcon image2 = new ImageIcon("large-360x240.jpg");
            frame2.setIconImage(image2.getImage());
            JLabel labellose = new JLabel("Game Over...");
            labellose.setForeground(Color.BLUE);
            labellose.setFont(new Font("Arial", Font.BOLD, 30));
            labellose.setBounds(100, 30, 350, 350);
            frame2.setSize(400, 400);
            frame2.setVisible(true);
            frame2.setLayout(null);
            frame2.setBackground(Color.BLACK);
            frame2.add(labellose);

            // frame2.pack();
            // System.exit(0);
        } else {
            // Handle the event if the button is not a mine
            // ...
//                if (button.isFlaged) {
//                    JLabel flag = new JLabel(); //JLabel Creation
//                    flag.setIcon(new ImageIcon("D:\\ITE\\سنة 2\\برمجة 3\\minisweaper\\src\\main\\java\\aggrigation\\minisweaper\\flag.png")); //Sets the image to be displayed as an icon
//                    Dimension size = flag.getPreferredSize(); //Gets the size of the image
//                    flag.setBounds(buttons[row][col].getX(), buttons[row][col].getY(), buttons[row][col].getWidth(), buttons[row][col].getHeight());
//                    button.add(flag);
//                    button.setEnabled(true);
//                }

        }
    }
}

