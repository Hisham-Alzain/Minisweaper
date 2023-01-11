package aggrigation.minisweaper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class GUI extends JPanel {

    Button button1;
    Button button2;
    Button button3;
    Minisweaper minis;

    public GUI() {
        JFrame frame = new JFrame();
        frame.setTitle("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        frame.setUndecorated(true);
        ImageIcon image = new ImageIcon("D:\\ITE\\سنة 2\\برمجة 3\\minisweaper\\src\\main\\java\\aggrigation\\minisweaper\\topleft.png");
        JButton button1 = new JButton("New Game");
        button1.setFont(new Font("Arial", Font.BOLD, 40));
        button1.setBounds(840, 460, 250, 60);
        button1.setFocusable(false);
        button1.setBackground(new Color(20, 40, 70));
        button1.setForeground(Color.white);
        button1.setBorder(BorderFactory.createEtchedBorder(new Color(50, 70, 100), Color.white));
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == button1) {
                    // 
                    NumofP test = new NumofP();
                    ImageIcon img = new ImageIcon("D:\\ITE\\سنة 2\\برمجة 3\\minisweaper\\src\\main\\java\\aggrigation\\minisweaper\\mine.jpg");
                    // Close First Frame
                    frame.dispose();
                    frame.setVisible(false);
                }
//                SwingUtilities.invokeLater(Testing::new );
            }
        });
        JButton button2 = new JButton("Load Game");
        button2.setFont(new Font("Arial", Font.BOLD, 40));
        button2.setBounds(840, 560, 250, 60);
        button2.setFocusable(false);
        button2.setBackground(new Color(20, 40, 70));
        button2.setForeground(Color.white);
        button2.setBorder(BorderFactory.createEtchedBorder(new Color(50, 70, 100), Color.white));
        button2.setOpaque(true);
        
        JTextField loadAdd = new JTextField();
        loadAdd.setBorder(BorderFactory.createTitledBorder("Enter File full path and name"));
        loadAdd.setBounds(840, 560, 250, 60);
        loadAdd.setVisible(false);
        frame.add(loadAdd);
        JButton submit = new JButton("submit");
        submit.setFont(new Font("Arial", Font.BOLD, 35));
        submit.setBounds(840, 660, 250, 60);
        submit.setFocusable(false);
        submit.setHorizontalAlignment(SwingConstants.CENTER);
        submit.setBackground(new Color(100, 230, 200));
        submit.setForeground(Color.black);
        submit.setOpaque(true);
        submit.setVisible(false);
        frame.add(submit);
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                minis = new Minisweaper();
                String path = loadAdd.getText();
                try {
                    minis.load(path);
                    GridLayoutBuilder g = new GridLayoutBuilder(Minisweaper.multiPlayerMode, minis.computerMode, minis.rows, minis.cols, minis.allBombsNumber);
                    frame.dispose();
                    frame.setVisible(false);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }

        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAdd.setVisible(true);
                submit.setVisible(true);

            }
        });
        
        JButton button3 = new JButton("Quit Game");
        button3.setFont(new Font("Comic Sans", Font.BOLD, 40));
        button3.setBounds(840, 660, 250, 60);
        button3.setFocusable(false);
        button3.setBackground(new Color(20, 40, 70));
        button3.setForeground(Color.red);
        button3.setBorder(BorderFactory.createEtchedBorder(new Color(50, 70, 100), Color.white));

        frame.setIconImage(image.getImage());
        ImageIcon background = new ImageIcon("D:\\ITE\\سنة 2\\برمجة 3\\minisweaper\\src\\main\\java\\aggrigation\\minisweaper\\res\\homebgg.png");
        JLabel backG = new JLabel("", background, JLabel.CENTER);
        backG.setBounds(0, 0, 1920, 1080);
        
        backG.setOpaque(true);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(backG);
        // Quit Button
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                frame.setVisible(false);
            }
        });

        frame.setLayout(null);
        frame.setVisible(true);
    }
}
