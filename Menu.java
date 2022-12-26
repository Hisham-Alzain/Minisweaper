/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aggrigation.minisweaper;
import java.io.*;
import java.util.*;
/**
 *
 * @author ASUS
 */
public class Menu {

    public cell[][] load(cell[][] game) throws FileNotFoundException, IOException {

        while (true) {
            System.out.println("enter file name or return by entering ***");
            Scanner in = new Scanner(System.in);
            String fname = in.next();
            if (fname == "***") {
                break;
            }
            File f = new File(fname);
            if (f.exists()) {
                String s;
                String[] so;
                BufferedReader fr = new BufferedReader(new FileReader(f));
                for (int i = 0; i < 10; i++) {
                    s = fr.readLine();
                    so = s.split(" ");
                    for (int j = 0; j < 10; j++) {
                        if (so[j] == "?") {
                            continue;
                        }
                        if (so[j] == "F") {
                            game[i][j].isFlaged = true;
                        } else {
                            game[i][j].show = true;
                        }
                    }
                }
                break;
            } else {
                System.out.println("not here");
            }
        }
        return game;
    }
}
