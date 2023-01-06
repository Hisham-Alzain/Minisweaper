package aggrigation.minisweaper;

import java.io.*;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author ASUS
 */

public class Minisweaper {
    
    public Minisweaper() {
        // Create game board
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                game[i][j] = new cell();
            }
        }
    }
    
    public static cell[][] game = new cell[10][10];
    public static int score1 = 0, score2 = 0,cMoves=1;
    public static void print(cell[][] game) {
        System.out.println();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                game[i][j].PrintCell();
                System.out.print("  ");
            }
            System.out.println();
        }
    }
    
   public static boolean ComputerMove(){
       Random r=new Random();
           int x,y;
       if(cMoves%5==0){
           do{
            x=r.nextInt(10);
            y=r.nextInt(10);
           }while(!game[x][y].show);
           if (game[x][y].bomb) {
                    System.out.println("You Lost");
                    System.out.println(calculateScore());
                    printNshow();
                    cMoves++;
                    return false;
           }
          floodfill(x,y);
         return true;       
       }
       else{
           do{
                 x=r.nextInt(10);
                 y=r.nextInt(10);
           }while(game[x][y].show || game[x][y].bomb);
           System.out.println(game[x][y].bomb);System.out.println(game[x][y].show);
           floodfill(x,y);
           cMoves++;
           return true;
       }
   }
    
    public static int calculateScore() {
        int score = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (game[i][j].show) {
                    score += game[i][j].NoBomb;
                }
                if (game[i][j].isFlaged) {
                    if (game[i][j].bomb) {
                        score += 5;
                    } else {
                        score -= 1;
                    }
                }
            }
        }
        return score;
    }

    public static boolean allNumShown() {
        int v = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (!game[i][j].bomb) {
                    if (game[i][j].show) {
                        v++;
                    }
                }
            }
        }
        return v == 90;
    }

    public static void printNshow() {
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
    
    public static void floodfill(int x,int y){
        int[] movex = {1, 1, 1, 0, 0, -1, -1, -1};
        int[] movey = {1, -1, 0, 1, -1, 0, 1, -1};
        if( !MoveisSafe(x,y) || game[x][y].bomb || game[x][y].show){
            return;
        }
        game[x][y].show=true;
//        if(w==2){
//            if(w%k==0)
//                score1+=game[x][y].NoBomb;
//            if(w%k==1)
//                score2+=game[x][y].NoBomb;
//         }
        if(game[x][y].NoBomb==0)
            for(int i=0;i<8;i++){
                floodfill(x+movex[i],y+movey[i]);
        }
    }

    public static void setBombsPlaces(int x, int y) {
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
    
    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int k = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                game[i][j] = new cell();
            }
        }
        int[] movex = {1, 1, 1, 0, 0, -1, -1, -1};
        int[] movey = {1, -1, 0, 1, -1, 0, 1, -1};
        Menu men = new Menu();
        System.out.println("welcome to minesweaper by y3hw");
        System.out.println("if you want to load a saved game enter 1");
        System.out.println("if you want to start a new multiPlayer game enter 2");
        System.out.println("if you want to play against an ai enter 3");
        System.out.println("if you want to start a new singlePlayer game enter any num");
        int w = in.nextInt();
        if (w == 1) {
            game = men.load(game);
        }
        while (!allNumShown()) {
            print(game);
            int x, y;
            try {
                // case multiplayer
                if (w == 2) {
                    if (k % 2 == 0) {
                        System.out.println("Player 1 Enter a cell coordinates ( 0,0 is the first cell)");
                    } else if (k % 2 == 1) {
                        System.out.println("Player 2 Enter a cell coordinates ( 0,0 is the first cell)");
                    }
                }
                // case playing vs computer
                else if(w==3){
                    if(k%2==0)
                        System.out.println("Enter a cell coordinates ( 0,0 is the first cell)");
                    else{
                        if(ComputerMove()){
                            k++;
                            continue;
                        }
                        else{
                                    k++;
                                    break;
                              }
                    }
                }
                // case single player
                else {
                    System.out.println("Enter a cell coordinates ( 0,0 is the first cell)");
                }
                String m = in.next();
                String[] c = new String[2];
                c = m.split(",");
                x = Integer.parseInt(c[0]);
                y = Integer.parseInt(c[1]);
            } catch (NumberFormatException e) {
                System.out.println(e);
                continue;
            }
            if (k == 0) {
                setBombsPlaces(x, y);
            }
            int d;
            do {
                System.out.println("Enter an action");
                System.out.println("1 to Flag a cell");
                System.out.println("2 to Press a cell");
                System.out.println("3 to unflag a cell");
                System.out.println("4 to exit");
                System.out.println("5 to go back");
                try{
                    d = in.nextInt();  
                    if (d == 1 || d == 2 || d == 3 || d == 4) {
                        break;
                    }
                }catch(Exception e){}
                System.out.println("wrong number");
            } while (true);
            if (d == 1) {
                if (!game[x][y].isFlaged) {
                    game[x][y].isFlaged = true;
                    game[x][y].show = true;
                    if (w == 2) {
                        if (game[x][y].bomb == true) {
                            if (k % 2 == 0) {
                                score1 += 5;
                            } else {
                                score2 += 5;
                            }
                        } else {
                            if (k % 2 == 0) {
                                score1 -= 1;
                            } else {
                                score2 -= 1;
                            }
                        }
                    }
                } else {
                    System.out.println("(" + x + "," + y + ") is already flagged");
                }
            }
            if (d == 2) {
                if (game[x][y].bomb) {
                    System.out.println("You Lost");
                    System.out.println(calculateScore());
                    printNshow();
                    break;
                }
                floodfill(x,y);
            }
            if (d == 3) {
                if (game[x][y].isFlaged) {
                    game[x][y].isFlaged = false;
                    game[x][y].show = false;
                    if (w == 2) {
                        if (game[x][y].bomb == true) {
                            if (k % 2 == 0) {
                                score1 += 1;
                            } else {
                                score2 += 5;
                            }
                        } else {
                            if (k % 2 == 0) {
                                score1 -= 5;
                            } else {
                                score2 -= 5;
                            }
                        }

                    } else {
                        System.out.println("(" + x + "," + y + ") is not flagged");
                    }
                }
            }
                if (d == 4) {
                    System.out.println("enter 1 if you want to return to the game");
                    System.out.println("enter 2 if you want to leave without a save");
                    System.out.println("enter 3 if you want to save and leave the game");
                    try {
                        int h = in.nextInt();
                        if (h == 1) {
                            continue;
                        } else if (h == 2) {
                            if(w==2){
                            System.out.println("Player 1 score is "+score1);
                            System.out.println("Player 2 score is "+score2);
                            }
                            else
                                System.out.println("Your Score is "+calculateScore());
                            break;
                        } else if (h == 3) {
                            System.out.println("Enter file name (must have txt extintion)");
                            String name = in.next();

                            try {
                                File f = new File("D:\\ITE\\سنة 2\\برمجة 3\\minisweaper\\Savedgames\\" + name);
                                if (f.createNewFile()) {
                                    FileOutputStream fw = new FileOutputStream(f);
                                    String s = "";
                                    for (int i = 0; i < 10; i++) {
                                        for (int j = 0; j < 10; j++) {
                                            if (game[i][j].show) {
                                                if (game[i][j].isFlaged) {
                                                    s += "F ";
                                                } else {
                                                    s += game[i][j].NoBomb;
                                                    s += " ";
                                                }
                                            } else {
                                                s += "? ";
                                            }
                                        }
                                        s += "\r\n";
                                    }
                                    fw.write(s.getBytes());
                                    fw.flush();
                                    fw.close();
                                    System.out.println("Your game is saved");
                                    break;
                                }
                            } catch (IOException e) {
                                System.out.println(e);
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println(e);
                    }
                }
                if(d==5){
                    continue;
                }
              k++;  
            }
             if (allNumShown()) {
                System.out.println("You have won");
                if(w==2){
                    System.out.println("Player 1 score is "+score1);
                    System.out.println("Player 2 score is "+score2);
                }
                else
                    System.out.println("Your Score is "+calculateScore());
            }
        }
    }
