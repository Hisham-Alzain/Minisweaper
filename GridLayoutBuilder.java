package aggrigation.minisweaper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    public int flagsNumber;
    public JLabel numOfFlags;
    public int time = 0;
    public JLabel timerLabel;
    public JLabel thescorelabel;

    public GridLayoutBuilder(boolean multi, boolean compMode, int rows, int cols, int bombsNum) {

        // Grid frame builder
        JFrame gridFrame = new JFrame();
        gridFrame.setUndecorated(true);
        gridFrame.setTitle("Minesweeper");
        gridFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gridFrame.setSize(1920, 1080);
        ImageIcon image = new ImageIcon("topleft.png");
        gridFrame.setIconImage(image.getImage());
        ImageIcon gridbackground = new ImageIcon("D:\\ITE\\سنة 2\\برمجة 3\\minisweaper\\src\\main\\java\\aggrigation\\minisweaper\\res\\grid-bg.png");
        JLabel gridlabell = new JLabel("", gridbackground, JLabel.CENTER);
        gridlabell.setBounds(0, 0, 1920, 1080);
        gridlabell.setOpaque(false);

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

        JTextField saveAdd = new JTextField();
        saveAdd.setBorder(BorderFactory.createTitledBorder("Enter File full path and name"));
        saveAdd.setBounds(1580, 822, 270, 70);
        saveAdd.setVisible(false);
        gridFrame.add(saveAdd);
        JButton submit = new JButton("submit");
        submit.setFont(new Font("Arial", Font.BOLD, 35));
        submit.setBounds(1580, 635, 270, 70);
        submit.setFocusable(false);
        submit.setHorizontalAlignment(SwingConstants.CENTER);
        submit.setBackground(new Color(100, 230, 200));
        submit.setForeground(Color.black);
        submit.setOpaque(true);
        submit.setVisible(false);
        gridFrame.add(submit);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String path = saveAdd.getText();
                minis.save(path);
                gridFrame.dispose();
            }

        });
        saveGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAdd.setVisible(true);
                submit.setVisible(true);

            }
        });

        JButton qgrid = new JButton("Quit Game");
        qgrid.setFont(new Font("Comic Sans", Font.BOLD, 35));
        qgrid.setBounds(1580, 935, 270, 70);
        qgrid.setFocusable(false);
        qgrid.setBackground(new Color(20, 40, 70));
        qgrid.setForeground(Color.red);
        qgrid.setBorder(BorderFactory.createEtchedBorder(new Color(50, 70, 100), Color.black));

        thescorelabel = new JLabel("Score: 0");
        thescorelabel.setText("Score: 0");
        thescorelabel.setFont(new Font("Arial", Font.BOLD, 45));
        thescorelabel.setBackground(new Color(250, 215, 0));
        thescorelabel.setForeground(Color.black);
//            scoreLabel.setBorder(BorderFactory.createLineBorder(Color.cyan));
        thescorelabel.setBounds(1600, 570, 250, 70);
        thescorelabel.setOpaque(true);
        thescorelabel.setVisible(true);
        thescorelabel.setHorizontalAlignment(SwingConstants.CENTER);

        numOfFlags = new JLabel();
        numOfFlags.setFont(new Font("Arial", Font.BOLD, 45));
        numOfFlags.setBackground(new Color(250, 15, 90));
        numOfFlags.setForeground(Color.black);
        thescorelabel.setBorder(BorderFactory.createLineBorder(Color.cyan));
        numOfFlags.setBounds(1600, 355, 250, 70);
        numOfFlags.setOpaque(true);
        numOfFlags.setVisible(true);
        numOfFlags.setHorizontalAlignment(SwingConstants.CENTER);
        // Set Flags Number
        flagsNumber = bombsNum;
        numOfFlags.setText(Integer.toString(flagsNumber));

        // Mines and Timer and Players labels
        qgrid.setBounds(1580, 935, 270, 70);
        timerLabel = new JLabel(Integer.toString(time));

        // ToDo: need to be chnaged
        timerLabel.setBounds(1680, 750, 270, 70);

        qgrid.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gridFrame.dispose();
            }
        });

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
        outerPanel.setLayout(new BorderLayout());
        outerPanel.setPreferredSize(new Dimension(NUM_COLS * 60, NUM_ROWS * 60));
//        outerPanel.setBackground(Color.BLACK);
        outerPanel.setOpaque(true);
        outerPanel.setVisible(true);
        // Create Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(new Dimension(1920 - 720, 1080 - 336));
        buttonPanel.setLayout(new GridLayout(NUM_ROWS, NUM_COLS));
        buttonPanel.setOpaque(true);

        // in MultiPlayerMode => add player turn label
        if (multiPlayerMode) {
            outerPanel.add(playerLabel);
        }

        // Create Minis
        minis = new Minisweaper(multi, compMode, rows, cols, bombsNum);

        // Add to panels
        gridlabell.add(saveGame);
        gridlabell.add(numOfFlags);
        gridlabell.add(timerLabel);
        gridFrame.add(qgrid);

        gridFrame.add(thescorelabel);

        gridFrame.add(gridlabell);

        // Add the button panel to the center of the outer panel
        outerPanel.add(buttonPanel, BorderLayout.WEST);
        gridFrame.add(outerPanel);
//        gridFrame.setLayout(null);
        gridFrame.setVisible(true);

        // Initial State
        for (int r = 0; r < NUM_ROWS; r++) {
            for (int c = 0; c < NUM_COLS; c++) {
                // Add Each Cell from Minis to buttons panel
                minis.game[r][c].setOpaque(true);
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
                new Win(minis.player1Score);

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
                    if (NUM_COLS == 9) {
                        ImageIcon f = new ImageIcon("flageasyy.png");
                        minis.game[i][j].setIcon(f);
                    } else if (NUM_COLS == 16) {
                        ImageIcon f = new ImageIcon("m.png");
                        minis.game[i][j].setIcon(f);
                    } else {
                        ImageIcon f = new ImageIcon("flaghard.png");
                        minis.game[i][j].setIcon(f);
                    }
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
                    minis.game[i][j].setForeground(Color.red);
                    minis.game[i][j].setFont(new Font("Arial", Font.BOLD, 35));

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
            if (NUM_COLS == 9) {
                ImageIcon f = new ImageIcon("flageasyy.png");
                minis.game[r][c].setIcon(f);
            } else if (NUM_COLS == 16) {
                ImageIcon f = new ImageIcon("m.png");
                minis.game[r][c].setIcon(f);
            } else {
                ImageIcon f = new ImageIcon("flaghard.png");
                minis.game[r][c].setIcon(f);
            }
            // update label
            flagsNumber--;
            System.out.println(flagsNumber);
            numOfFlags.setText(Integer.toString(flagsNumber));
        } else {
            minis.game[r][c].isFlaged = false;
            minis.game[r][c].setText("");
            // update label
            flagsNumber++;
            numOfFlags.setText(Integer.toString(flagsNumber));
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

        // update score
        if (!multiPlayerMode) {
            // update score
            thescorelabel.setText("Score: " + Integer.toString(minis.player1Score));
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
//                playerLabel.setText("player 2 turn");
            } // player 2 turn
            else {
                playerTurn = 1;
//                playerLabel.setText("player 1 turn");
            }
        }

        // Count number of elligble clicks (also prevent calling setBombsPlaces again)
        counter++;

        // Prevent doble click on same button
        minis.game[x][y].setEnabled(false);

        // Change clicked button color
        minis.game[x][y].setForeground(Color.red);
        minis.game[x][y].setFont(new Font("Arial", Font.BOLD, 35));

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
//                    playerLabel.setText("player: 1 won");
                } // player 2 turn
                else {
//                    playerLabel.setText("player: 2 won");
                }
            }

            // open losing frame
            if (multiPlayerMode) {
                Losing lose = new Losing(0);
            } else {
                
                new Losing(minis.player1Score);
            }

        } else {
//            if (minis.computerMode) {
//                if (counter % 2 != 0) {
//                    try {
//                        minis.ComputerMove();
//                        counter++;
//                    } catch (Exception e) {
//                    }
//
//                } else {
//                    minis.floodfill(x, y);
//                    setShowedCellsText();
//                }
//            }
            System.out.println("x: " + x);
            System.out.println("y: " + y);
            minis.floodfill(x, y);
            setShowedCellsText();
        }

        if (!multiPlayerMode) {
            // update score
            thescorelabel.setText("Score: " + Integer.toString(minis.player1Score));
        }

    }
}
