package aggrigation.minisweaper;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
public class Levels extends JPanel {
    Button easy;
    Button medium;
    Button expert;

    public Levels(boolean multi, boolean compMode) {
        
        JFrame lframe = new JFrame();
        lframe.setTitle("Minesweeper");
        lframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lframe.setSize(1920,1080);
        ImageIcon image = new ImageIcon("D:\\ITE\\سنة 2\\برمجة 3\\minisweaper\\src\\main\\java\\aggrigation\\minisweaper\\topleft.png");
        lframe.setIconImage(image.getImage());
        ImageIcon bgr = new ImageIcon("D:\\ITE\\سنة 2\\برمجة 3\\minisweaper\\src\\main\\java\\aggrigation\\minisweaper\\res\\levelff.png");
        JLabel brg = new JLabel("",bgr, JLabel.CENTER);
        brg.setBounds(0,0,1920,1080);
        JButton easy = new JButton("Easy");
        easy.setFont(new Font("Arial",Font.BOLD,60));
        easy.setBounds(750, 360, 350, 120);
        easy.setFocusable(false);
        easy.setHorizontalAlignment(SwingConstants.CENTER);
        easy.setBackground(new Color(20,40,70));
        easy.setForeground(Color.white);
        easy.setOpaque(true);
        easy.setVisible(true);
        
        //easy game
        easy.addActionListener((ActionEvent e) -> {
            JFrame jf=new JFrame();
            Grid g=new Grid(multi, compMode, 9, 9, 10);
            jf.add(g);
            ImageIcon img = new ImageIcon("D:\\ITE\\سنة 2\\برمجة 3\\minisweaper\\src\\main\\java\\aggrigation\\minisweaper\\mine.jpg");
            jf.setIconImage(img.getImage());
            jf.setSize(1920, 1080);
            jf.setVisible(true);
            lframe.dispose();
        });
        
        JButton medium = new JButton("Medium");
        medium.setFont(new Font("Arial",Font.BOLD,60));
        medium.setBounds(750, 500, 350, 120);
        medium.setFocusable(false);
        medium.setHorizontalAlignment(SwingConstants.CENTER);
        medium.setBackground(new Color(20,40,70));
        medium.setForeground(Color.white);
        medium.setOpaque(true);
        medium.setVisible(true);
        
        //meduim level
        medium.addActionListener((ActionEvent e) -> {
            JFrame jf=new JFrame();
            Grid g=new Grid(multi,compMode, 9, 16, 15);
            jf.add(g);
            ImageIcon img = new ImageIcon("D:\\ITE\\سنة 2\\برمجة 3\\minisweaper\\src\\main\\java\\aggrigation\\minisweaper\\mine.jpg");
            jf.setIconImage(img.getImage());
            jf.setSize(1920, 1080);
            jf.setVisible(true);
            lframe.dispose();
        });
        
        JButton expert = new JButton("Hard");
        expert.setFont(new Font("Arial",Font.BOLD,60));
        expert.setBounds(750, 650, 350, 120);
        expert.setFocusable(false);
        expert.setHorizontalAlignment(SwingConstants.CENTER);
        expert.setBackground(new Color(20,40,70));
        expert.setForeground(Color.white);
        expert.setOpaque(true);
        expert.setVisible(true);
        lframe.add(medium);
        lframe.add(easy);
        lframe.add(expert);
        lframe.add(brg);
        lframe.setVisible(true);
        lframe.setLayout(null);
        expert.addActionListener((ActionEvent e) -> {
            JFrame jf=new JFrame();
            Grid g=new Grid(multi, compMode, 24, 24, 50);
            jf.add(g);
            ImageIcon img = new ImageIcon("D:\\ITE\\سنة 2\\برمجة 3\\minisweaper\\src\\main\\java\\aggrigation\\minisweaper\\mine.jpg");
            jf.setIconImage(img.getImage());
            jf.setSize(1920, 1080);
            jf.setVisible(true);
            lframe.dispose();
        });

    }
}
//    JPanel lpanel = new JPanel();
//        lpanel.setBounds(688,230,480,600);
////        lpanel.setLayout(null);
//        lpanel.setLayout(new GridLayout());
//        lpanel.setBackground(new Color(211,211,211));
//        lpanel.setBorder(BorderFactory.createEtchedBorder(new Color(50, 70, 100), Color.white));
// lpanel.add(easy);
//         lframe.add(lpanel);
//         lpanel.setVisible(true);
