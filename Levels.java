package com.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
public class Levels {
    Button easy;
    Button medium;
    Button expert;

    public Levels() {
        JFrame lframe = new JFrame();
        lframe.setTitle("Minesweeper");
        lframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        lframe.setSize(1920,1080);
        ImageIcon image = new ImageIcon("topleft.png");
        lframe.setIconImage(image.getImage());
        ImageIcon bgr = new ImageIcon("levelff.png");
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
        JButton medium = new JButton("Medium");
        medium.setFont(new Font("Arial",Font.BOLD,60));
        medium.setBounds(750, 500, 350, 120);
        medium.setFocusable(false);
        medium.setHorizontalAlignment(SwingConstants.CENTER);
        medium.setBackground(new Color(20,40,70));
        medium.setForeground(Color.white);
        medium.setOpaque(true);
        medium.setVisible(true);
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