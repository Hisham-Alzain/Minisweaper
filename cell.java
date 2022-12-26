/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aggrigation.minisweaper;

/**
 *
 * @author ASUS
 */
public class cell {
    boolean bomb;
    int NoBomb;
    boolean show;
    boolean isFlaged;
    public cell() {
        bomb=false;
        NoBomb=0;
        show=false;
        isFlaged=false;
    }
    public void PrintCell(){
        if(show){
            if(isFlaged){
                System.out.print("F");
            }
            else if(bomb){
                    System.out.print("X");
                }
                else
                    System.out.print(this.NoBomb);
            }
        else{
            System.out.print("?");
        }
    }
}
