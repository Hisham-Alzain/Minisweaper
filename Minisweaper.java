/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package aggrigation.minisweaper;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author ASUS
 */
public class Minisweaper {

    public static void print(cell[][] game) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                game[i][j].PrintCell();
                System.out.print("  ");
            }
            System.out.println();
        }
    }
    public static int calculateScore(cell[][] game){
       int score=0;
       for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if(game[i][j].show){
                    score+=game[i][j].NoBomb;
                }
                if(game[i][j].isFlaged){
                    if(game[i][j].bomb)
                        score+=5;
                    else
                        score-=0;
                }
            }
       }
       return score;
   }

    public static boolean allNumShown(cell[][] game) {
        int v = 0;
        for (int i = 0; i < 10; i++) 
            for (int j = 0; j < 10; j++) 
                if (!game[i][j].bomb) 
                    if (game[i][j].show) 
                        v++;
        return v ==90;
    }
    
    public static void printNshow(cell[][] game) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                game[i][j].show = true;
                game[i][j].PrintCell();
                System.out.print("  ");
            }
            System.out.println();
        }
    }

    public static void callNoBomb(cell[][] game) {
        int[] mox = {1, 1, 1, 0, 0, -1, -1, -1};
        int[] moy = {1, -1, 0, 1, -1, 0, 1, -1};
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (game[i][j].bomb) {
                    for (int k = 0; k < 8; k++) {
                        if ((i + mox[k]) >= 0 && (i + mox[k]) < 10 && j + moy[k] >= 0 && j + moy[k] < 10) {
                            game[i + mox[k]][j + moy[k]].NoBomb++;
                        }
                    }
                }
            }
        }
    }

    public static boolean MoveisSafe(int x, int y) {
        return (x >= 0 && x < 10 && y >= 0 && y < 10);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int k = 0;
        cell[][] game = new cell[10][10];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                game[i][j] = new cell();
            }
        }
        int[] movex = {1, 1, 1, 0, 0, -1, -1, -1};
        int[] movey = {1, -1, 0, 1, -1, 0, 1, -1};
        while (true) {
            print(game);
            int x,y;
            try{
            System.out.println("Enter a cell coordinates ( 0,0 is the first cell)");
            String m = in.next();
            String[] c = new String[2];
            c = m.split(",");
             x = Integer.parseInt(c[0]);
             y = Integer.parseInt(c[1]);
            }catch(NumberFormatException e){
                System.out.println(e);
                continue;
            }
            if (k == 0) {
                for (int i = 0; i < 10; i++) {
                    Random rand = new Random();
                    // Obtain a number between [0 - 9].
                    int box = rand.nextInt(10);
                    int boy = rand.nextInt(10);
                    if (box == x && boy == y) {
                        i--;
                        continue;
                    }
                    game[box][boy].bomb = true;
                }
                callNoBomb(game);
            }
            if(true){
                printNshow(game);
                break;
            }
            int d;
            do {
                System.out.println("Enter an action");
                System.out.println("1 to Flag a cell");
                System.out.println("2 to Press a cell");
                System.out.println("3 to unflag a cell");
                d = in.nextInt();
                if (d == 1 || d == 2 || d == 3) {
                    break;
                }
                System.out.println("wrong number");
            } while (true);
            if (allNumShown(game)) {
                        System.out.println("You have won");
                        System.out.println(calculateScore(game));
                        break;
                    }
            if (d == 1) {
                if (!game[x][y].isFlaged) {
                    game[x][y].isFlaged = true;
                    game[x][y].show = true;
                } else {
                    System.out.println("(" + x + "," + y + ") is already flagged");
                }
            }
            if (d == 2) {
                if (game[x][y].bomb) {
                    System.out.println("You Lost");
                    System.out.println(calculateScore(game));
                    printNshow(game);
                    break;
                }
                game[x][y].show = true;

                for(int i=0;i<8;i++){
                    if(MoveisSafe(x+movex[i],y+movey[i]))
                        if(!game[x+movex[i]][y+movey[i]].bomb)
                            game[x+movex[i]][y+movey[i]].show=true;
                   }
//                if(k==0){
//                for(int i=0;i<8;i++){
//                    int j=0;
//                    if(MoveisSafe(x+movex[i],y+movey[i])){
//                        while(!game[x+movex[i]][y+movey[i]].bomb && game[x+movex[i]][y+movey[i]].NoBomb==0){
//                            if(MoveisSafe(x+movex[i]+movex[j],y+movey[i]+movey[j]))
//                                game[x+movex[i]+movex[j]][y+movey[i]+movey[j]].show=true;
//                             j++;
//                             if(j==8)
//                                break;
//                        }
//                    }
//                }
//            }
                if (d == 3) {
                    if (game[x][y].isFlaged) {
                        game[x][y].isFlaged = false;
                        game[x][y].show = false;
                    } else {
                        System.out.println("(" + x + "," + y + ") is not flagged");
                    }
                }
                k++;
            }
        }
    }
}
