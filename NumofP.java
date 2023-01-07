package aggrigation.minisweaper;

import static aggrigation.minisweaper.Minisweaper.calculatePlayer1Score;
import static aggrigation.minisweaper.Minisweaper.game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumofP extends JPanel {

    Button solo;
    Button multiplayer;

    public NumofP() {
        JFrame nframe = new JFrame();
        nframe.setTitle("Minesweeper");
        nframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nframe.setSize(1920, 1080);
        ImageIcon image = new ImageIcon("D:\\ITE\\سنة 2\\برمجة 3\\minisweaper\\src\\main\\java\\aggrigation\\minisweaper\\topleft.png");
        nframe.setIconImage(image.getImage());
        ImageIcon background4 = new ImageIcon("D:\\ITE\\سنة 2\\برمجة 3\\minisweaper\\src\\main\\java\\aggrigation\\minisweaper\\res\\mode.png");
        JLabel label4 = new JLabel("", background4, JLabel.CENTER);
        label4.setBounds(0, 0, 1920, 1080);

        JButton solo = new JButton("SOLO");
        solo.setFont(new Font("Arial", Font.BOLD, 55));
        solo.setBounds(740, 360, 400, 120);
        solo.setFocusable(false);
        solo.setHorizontalAlignment(SwingConstants.CENTER);
        solo.setBackground(new Color(20, 40, 70));
        solo.setForeground(Color.white);
        solo.setOpaque(true);
        solo.setVisible(true);
        // solo button action
        solo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame l = new JFrame();
                Levels t = new Levels(false, false);
                l.add(t);
                ImageIcon img = new ImageIcon("D:\\ITE\\سنة 2\\برمجة 3\\minisweaper\\src\\main\\java\\aggrigation\\minisweaper\\mine.jpg");
                l.setIconImage(img.getImage());
                l.setSize(1920, 1080);
                l.setVisible(true);
                // Close First Frame
                l.dispose();
            }
        });

        JButton multiplayer = new JButton("MULTIPLAYER");
        multiplayer.setFont(new Font("Arial", Font.BOLD, 50));
        multiplayer.setBounds(740, 570, 400, 120);
        multiplayer.setFocusable(false);
        multiplayer.setHorizontalAlignment(SwingConstants.CENTER);
        multiplayer.setBackground(new Color(20, 40, 70));
        multiplayer.setForeground(Color.white);
        multiplayer.setOpaque(true);
        multiplayer.setVisible(true);

        multiplayer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame l = new JFrame();
                Levels t = new Levels(true, false);
                l.add(t);
                ImageIcon img = new ImageIcon("D:\\ITE\\سنة 2\\برمجة 3\\minisweaper\\src\\main\\java\\aggrigation\\minisweaper\\mine.jpg");
                l.setIconImage(img.getImage());
                l.setSize(1920, 1080);
                l.setVisible(true);
                // Close First Frame
                l.dispose();
            }
        });

        JButton vsComputer = new JButton("VSCOMPUTER");
        vsComputer.setFont(new Font("Arial", Font.BOLD, 50));
        vsComputer.setBounds(740, 640, 400, 120);
        vsComputer.setFocusable(false);
        vsComputer.setHorizontalAlignment(SwingConstants.CENTER);
        vsComputer.setBackground(new Color(20, 40, 70));
        vsComputer.setForeground(Color.white);
        vsComputer.setOpaque(true);
        vsComputer.setVisible(true);

        vsComputer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame l = new JFrame();
                Levels t = new Levels(true, true);
                l.add(t);
                ImageIcon img = new ImageIcon("D:\\ITE\\سنة 2\\برمجة 3\\minisweaper\\src\\main\\java\\aggrigation\\minisweaper\\mine.jpg");
                l.setIconImage(img.getImage());
                l.setSize(1920, 1080);
                l.setVisible(true);
                // Close First Frame
                l.dispose();
            }
        });

        nframe.add(multiplayer);
        nframe.add(solo);
        nframe.add(label4);
        nframe.add(vsComputer);
        nframe.setVisible(true);
        nframe.setLayout(null);

    }
}
