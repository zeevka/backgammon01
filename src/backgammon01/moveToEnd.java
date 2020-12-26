/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01;

import backgammon01.Server.Handler;
import java.util.Scanner;

/**
 *
 * @author zeev7
 */
public class moveToEnd extends Move {

    public moveToEnd(board moveBoard, Handler.color thisColor) {
        super(moveBoard, thisColor);
        start();
    }
    private int plase;
    private int plaseForFunk;
    private int index;

    public final void start() {
        if (thisColor == Handler.color.whith) {
            plase = 27;
            plaseForFunk = 20;
            index = 1;
        } else {
            plase = 0;
            plaseForFunk = 7;
            index = -1;

        }
    }

    /**
     * this function chek if it's automaton to take out pieces 
     * if yes she do it
     * @return fale if it not automaton 
     */
    public boolean aoto() {
        int tmp, numof = 0;
        for (tmp = plaseForFunk; tmp != plase + steps.get(0); tmp += index) {

            if (moveBoard.getStatus(tmp) == moveBoard.change(thisColor)) {// todo chek if after ove the square is empty
                return false;
            }
        }

        for (; tmp != plase; tmp += index) {
            if (moveBoard.getStatus(tmp) == moveBoard.change(thisColor)) {
                numof = moveBoard.getNum(tmp);
            }
            while (steps.size() > 0 && numof > 0) {
                moveBoard.theMove(thisColor, tmp, plase);
                steps.remove(0);
                numof -= 1;
            }
            if (steps.size() == 0) {
                System.out.println("we heve a winer !! ");
                System.exit(0);
            }
        }
        return true;

    }

    /**
     * this fonctin do the mive out
     */
    public void mteMove() {
        int tmp;
        Scanner s = new Scanner(System.in);
        // sort the list 
        if (steps.size() > 1 && steps.get(0) < steps.get(1)) {
            tmp = steps.get(0);
            steps.set(0, steps.get(1));
            steps.set(1, tmp);
        }

        while (steps.size() > 0) {

            if (aoto()) {//if it automaton to take out brake..
                break;
            }
            tmp = 0;
            while (true) {
                tmp = s.nextInt();
                //first check if in square tmp there is piece   next- chek if there is optien to do it
                if (moveBoard.chekForOut(tmp, thisColor) && (tmp + steps.get(0) >= 1 && tmp + steps.get(0) <= 26)) {
                    if(tmp + steps.get(0)==plase){
                         moveBoard.theMove(thisColor,tmp ,plase );
                    }else{
                         moveBoard.theMove(thisColor, tmp,tmp+ steps.get(0));
                    }
                    break;
                }
                System.out.println("erorr");
            }
             
        }

    }
}
/*    public boolean chekTOout() {
    
 int sum = 0, num, i;
    
 if (thisColor == Handler.color.whith) {
 num = 20;
 } else {
 num = 2;
 }
    
 for (i = 0; i > 6; i++) {
 if (moveBoard.getStatus(num+i)==moveBoard.change(thisColor)) {
 sum += moveBoard.getNum(num+i);
 }
 }
    
 if (sum == (15-moveBoard.getNum(plase))) {
 return true;
 }
    
 return false;
 }*/
