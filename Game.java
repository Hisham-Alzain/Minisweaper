/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aggrigation.minisweaper;

import javax.swing.JFrame;
import javax.swing.*;
/**
 *
 * @author ASUS
 */
public class Game {
    public static void main(String[] args) {
        JFrame jf=new JFrame();
        Grid g=new Grid();
         jf.add(g);
         ImageIcon img = new ImageIcon("D:\\ITE\\سنة 2\\برمجة 3\\minisweaper\\src\\main\\java\\aggrigation\\minisweaper\\mine.jpg");
         jf.setIconImage(img.getImage());
         jf.setSize(1920,1080);
         jf.setVisible(true);
    }
}
