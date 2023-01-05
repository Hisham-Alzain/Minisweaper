package com.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionListener;
public class MultiResult {
    Button b1;
    Button b2;
    Button b3;
    public MultiResult(){
        JFrame multiFrame = new JFrame();
        multiFrame.setTitle("Minesweeper");
        multiFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        multiFrame.setSize(1920, 1080);
        ImageIcon image = new ImageIcon("topleft.png");
        multiFrame.setIconImage(image.getImage());


        ImageIcon background5 = new ImageIcon("multiscore.png");
        JLabel label5 = new JLabel("",background5, JLabel.CENTER);
        label5.setBounds(0,0,1920,1080);
        JPanel panelmulti = new JPanel();
        JLabel scoreLabel2 = new JLabel("   Score: 0");
        scoreLabel2.setText("Score: 0");
        scoreLabel2.setFont(new Font("Arial",Font.BOLD,60));
        scoreLabel2.setBackground(new Color(255,215,0));
        scoreLabel2.setForeground(Color.black);
//            scoreLabel.setBorder(BorderFactory.createLineBorder(Color.cyan));
        scoreLabel2.setBounds(450,560,350,100);
        scoreLabel2.setOpaque(true);
        scoreLabel2.setVisible(true);
        scoreLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        panelmulti.setBounds(450,560,350,100);
        panelmulti.add(scoreLabel2);
//            pane.setVisible(true);
        panelmulti.setBackground(new Color(255,215,0));
        panelmulti.setBorder(BorderFactory.createEtchedBorder(new Color(50, 70, 100), Color.white));

        JPanel panelmulti2 = new JPanel();
        JLabel scoreLabel3 = new JLabel("   Score: 0");
        scoreLabel3.setText("Score: 0");
        scoreLabel3.setFont(new Font("Arial",Font.BOLD,60));
        scoreLabel3.setBackground(new Color(255,215,0));
        scoreLabel3.setForeground(Color.black);
//            scoreLabel.setBorder(BorderFactory.createLineBorder(Color.cyan));
        scoreLabel3.setBounds(1120,560,350,100);
        scoreLabel3.setOpaque(true);
        scoreLabel3.setVisible(true);
        scoreLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        panelmulti2.setBounds(1120,560,350,100);
        panelmulti2.add(scoreLabel3);
//            pane.setVisible(true);
        panelmulti2.setBackground(new Color(255,215,0));
        panelmulti2.setBorder(BorderFactory.createEtchedBorder(new Color(50, 70, 100), Color.white));

        JButton b1 = new JButton("Play Again");
        b1.setFont(new Font("Arial", Font.BOLD, 48));
        b1.setBounds(818, 708, 300, 80);
        b1.setFocusable(false);
        b1.setBackground(new Color(20, 40, 70));
        b1.setForeground(Color.white);
        b1.setBorder(BorderFactory.createEtchedBorder(new Color(50, 70, 100), Color.white));
        JButton b2 = new JButton("Quit Game");
        b2.setFont(new Font("Comic Sans", Font.BOLD, 48));
        b2.setBounds(818, 800, 300, 80);
        b2.setFocusable(false);
        b2.setBackground(new Color(20, 40, 70));
        b2.setForeground(Color.red);
        b2.setBorder(BorderFactory.createEtchedBorder(new Color(50, 70, 100), Color.white));
        multiFrame.add(panelmulti);
        multiFrame.add(panelmulti2);
        multiFrame.add(b1);
        multiFrame.add(b2);
        multiFrame.add(label5);
        multiFrame.setLayout(null);
        multiFrame.setVisible(true);




}
}