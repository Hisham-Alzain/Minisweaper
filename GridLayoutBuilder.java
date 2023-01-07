package aggrigation.minisweaper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import javax.swing.*;
import java.util.*;

public class GridLayoutBuilder extends JPanel {

    Button saveGame;
    JButton qgrid;

    public static JPanel outerPanel;
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

    public GridLayoutBuilder(boolean multi, boolean compMode, int rows, int cols, int bombsNum) {

        // Grid frame builder
        JFrame gridFrame = new JFrame();
        gridFrame.setTitle("Minesweeper");
        gridFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gridFrame.setSize(1920, 1080);
        ImageIcon image = new ImageIcon("topleft.png");
        gridFrame.setIconImage(image.getImage());
        ImageIcon gridbackground = new ImageIcon("D:\\ITE\\سنة 2\\برمجة 3\\minisweaper\\src\\main\\java\\aggrigation\\minisweaper\\res\\grid-bg.png");
        JLabel gridlabell = new JLabel("", gridbackground, JLabel.CENTER);
        gridlabell.setBounds(0, 0, 1920, 1080);
        
        JButton saveGame = new JButton("Save Game");
        saveGame.setFont(new Font("Arial", Font.BOLD, 35));
        saveGame.setBounds(1580, 822, 270, 70);
        saveGame.setFocusable(false);
        saveGame.setHorizontalAlignment(SwingConstants.CENTER);
        saveGame.setBackground(new Color(100, 230, 200));
        saveGame.setForeground(Color.black);
        saveGame.setOpaque(true);
        saveGame.setVisible(true);
        saveGame.setBorder(BorderFactory.createEtchedBorder(new Color(50, 70, 100), Color.black));

        JButton qgrid = new JButton("Quit Game");
        qgrid.setFont(new Font("Comic Sans", Font.BOLD, 35));
        qgrid.setBounds(1580, 935, 270, 70);
        qgrid.setFocusable(false);
        qgrid.setBackground(new Color(20, 40, 70));
        qgrid.setForeground(Color.red);
        qgrid.setBorder(BorderFactory.createEtchedBorder(new Color(50, 70, 100), Color.black));

        // Get Grid System
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
        outerPanel = new JPanel();
        outerPanel.setLayout(new FlowLayout());
        outerPanel.setPreferredSize(new Dimension(720, 400));
        outerPanel.setBackground(Color.BLACK);
        outerPanel.setVisible(true);
        // Create Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(720, 400));
        buttonPanel.setLayout(new GridLayout(NUM_ROWS, NUM_COLS));

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

        
        // Add to panels
        gridlabell.add(saveGame);
        gridFrame.add(qgrid);
        gridFrame.add(gridlabell);
        gridFrame.add(outerPanel);
//        gridFrame.setLayout(null);
        gridFrame.setVisible(true);

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
//            Losing lose = new Losing(minis.calculateScore());
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

    }
}
