package com.example;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class NumofP {
    Button solo;
    Button multiplayer;

    public NumofP() {
        JFrame nframe = new JFrame();
        nframe.setTitle("Minesweeper");
        nframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        nframe.setSize(1920, 1080);
        ImageIcon image = new ImageIcon("topleft.png");
        nframe.setIconImage(image.getImage());
        ImageIcon background4 = new ImageIcon("mode.png");
        JLabel label4 = new JLabel("",background4, JLabel.CENTER);
        label4.setBounds(0,0,1920,1080);


        JButton solo = new JButton("SOLO");
        solo.setFont(new Font("Arial",Font.BOLD,55));
        solo.setBounds(740, 360, 400, 120);
        solo.setFocusable(false);
        solo.setHorizontalAlignment(SwingConstants.CENTER);
        solo.setBackground(new Color(20,40,70));
        solo.setForeground(Color.white);
        solo.setOpaque(true);
        solo.setVisible(true);
        JButton multiplayer = new JButton("MULTIPLAYER");
        multiplayer.setFont(new Font("Arial",Font.BOLD,50));
        multiplayer.setBounds(740, 570, 400, 120);
        multiplayer.setFocusable(false);
        multiplayer.setHorizontalAlignment(SwingConstants.CENTER);
        multiplayer.setBackground(new Color(20,40,70));
        multiplayer.setForeground(Color.white);
        multiplayer.setOpaque(true);
        multiplayer.setVisible(true);
        nframe.add(multiplayer);
        nframe.add(solo);
        nframe.add(label4);
        nframe.setVisible(true);
        nframe.setLayout(null);



    }
}