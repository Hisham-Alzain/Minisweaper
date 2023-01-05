package aggrigation.minisweaper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;

public class GUI {

    Button button1;
    Button button2;
    Button button3;

    public GUI() {
        JFrame frame = new JFrame();
        frame.setTitle("Minesweeper");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        ImageIcon image = new ImageIcon("topleft.png");
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
                    // start grid
                    JFrame jf = new JFrame();
                    Grid g = new Grid();
                    jf.add(g);
                    ImageIcon img = new ImageIcon("D:\\ITE\\سنة 2\\برمجة 3\\minisweaper\\src\\main\\java\\aggrigation\\minisweaper\\mine.jpg");
                    jf.setIconImage(img.getImage());
                    jf.setSize(1920, 1080);
                    jf.setVisible(true);
                    // Close First Frame
                    frame.dispose();
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
        JButton button3 = new JButton("Quit Game");
        button3.setFont(new Font("Comic Sans", Font.BOLD, 40));
        button3.setBounds(840, 660, 250, 60);
        button3.setFocusable(false);
        button3.setBackground(new Color(20, 40, 70));
        button3.setForeground(Color.red);
        button3.setBorder(BorderFactory.createEtchedBorder(new Color(50, 70, 100), Color.white));

        frame.setIconImage(image.getImage());
        ImageIcon background = new ImageIcon("homebgg.png");
        JLabel backG = new JLabel("", background, JLabel.CENTER);
        backG.setBounds(0, 0, 1920, 1080);

        frame.add(backG);
        frame.add(button1);
        frame.add(button2);
        frame.add(button3);

        // Quit Button
        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        frame.setLayout(null);
        frame.setVisible(true);
    }
}

//public class GUI implements ActionListener {
//    JButton button1;
//    JButton button2;
//    JButton button3;
//    public GUI(){
//        JFrame frame = new JFrame();
//        frame.setTitle("Minesweeper");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(1920,1080);
//        ImageIcon image = new ImageIcon("topleft.png");
//        JButton button1 = new JButton("New Game");
//        button1.setFont(new Font("Arial",Font.BOLD,40));
//        button1.setBounds(840, 460, 250, 60);
//        button1.setFocusable(false);
//        button1.setBackground(new Color(20,40,70));
//        button1.setForeground(Color.white);
//        button1.setBorder(BorderFactory.createEtchedBorder(new Color(50,70,100),Color.white));
//        button1.addActionListener(this);
//        JButton button2 = new JButton("Load Game");
//        button2.setFont(new Font("Arial",Font.BOLD,40));
//        button2.setBounds(840, 560, 250, 60);
//        button2.setFocusable(false);
//        button2.setBackground(new Color(20,40,70));
//        button2.setForeground(Color.white);
//        button2.setBorder(BorderFactory.createEtchedBorder(new Color(50,70,100),Color.white));
//        button2.addActionListener(this);
//        JButton button3 = new JButton("Quit Game");
//        button3.setFont(new Font("Comic Sans", Font.BOLD, 40));
//        button3.setBounds(840, 660, 250, 60);
//        button3.setFocusable(false);
//        button3.setBackground(new Color(20,40,70));
//        button3.setForeground(Color.red);
//        button3.setBorder(BorderFactory.createEtchedBorder(new Color(50,70,100),Color.white));
//        button3.addActionListener(this);
//        frame.setIconImage(image.getImage());
//        ImageIcon background = new ImageIcon("homeb.png");
//        JLabel backG = new JLabel("",background, JLabel.CENTER);
//        backG.setBounds(0,0,1920,1080);
//
//        frame.add(backG);
//        frame.add(button1);
//        frame.add(button2);
//        frame.add(button3);
//
//        frame.setLayout(null);
//        frame.setVisible(true);
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//
//    }
//}
//
//
