package aggrigation.minisweaper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Win {
    Button b1;
    Button b2;
    Button b3;

    public Win() {
        JFrame winframe = new JFrame();
        winframe.setTitle("Minesweeper");
        winframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        winframe.setSize(1920, 1080);
        ImageIcon image = new ImageIcon("topleft.png");
        JButton b1 = new JButton("Play Again");
        b1.setFont(new Font("Arial", Font.BOLD, 48));
        b1.setBounds(818, 708, 300, 80);
        b1.setFocusable(false);
        b1.setBackground(new Color(20, 40, 70));
        b1.setForeground(Color.white);
        b1.setBorder(BorderFactory.createEtchedBorder(new Color(50, 70, 100), Color.white));
        //Play again
        b1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame jf=new JFrame();
                 GUI g=new GUI();
                 jf.add(g);
                 ImageIcon img = new ImageIcon("D:\\ITE\\سنة 2\\برمجة 3\\minisweaper\\src\\main\\java\\aggrigation\\minisweaper\\mine.jpg");
                 jf.setIconImage(img.getImage());
                 jf.setSize(1920, 1080);
                 jf.setVisible(true);
                 winframe.dispose();
            }
        });
        JButton b2 = new JButton("Quit Game");
        b2.setFont(new Font("Comic Sans", Font.BOLD, 48));
        b2.setBounds(818, 800, 300, 80);
        b2.setFocusable(false);
        b2.setBackground(new Color(20, 40, 70));
        b2.setForeground(Color.red);
        b2.setBorder(BorderFactory.createEtchedBorder(new Color(50, 70, 100), Color.white));
        
        //Quit game 
        b2.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    winframe.dispose();
                }
            });
        
        winframe.setIconImage(image.getImage());
        ImageIcon bg = new ImageIcon("D:\\ITE\\سنة 2\\برمجة 3\\minisweaper\\src\\main\\java\\aggrigation\\minisweaper\\rec\\winpicc.png");
        JLabel bk = new JLabel("",bg, JLabel.CENTER);
        bk.setBounds(0,0,1920,1080);
        JPanel pane = new JPanel();
        JLabel scoreLabel = new JLabel("   Score: 0");
        scoreLabel.setText("Score: 0");
        scoreLabel.setFont(new Font("Arial",Font.BOLD,60));
        scoreLabel.setBackground(new Color(250,215,0));
        scoreLabel.setForeground(Color.black);
//            scoreLabel.setBorder(BorderFactory.createLineBorder(Color.cyan));
        scoreLabel.setBounds(734,575,470,100);
        scoreLabel.setOpaque(true);
        scoreLabel.setVisible(true);
        scoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
        pane.setBounds(734,575,470,100);
        pane.add(scoreLabel);
        pane.setVisible(true);
        pane.setBackground(new Color(250,215,0));
        pane.setBorder(BorderFactory.createEtchedBorder(new Color(50, 70, 100), Color.white));
        winframe.add(pane);
        winframe.add(bk);
        winframe.add(b1);
        winframe.add(b2);
        winframe.setLayout(null);
        winframe.setVisible(true);
    }

}

