/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01;

import backgammon01.Server.Handler;
import backgammon01.Server.Handler.color;
import backgammon01.square.ColorStatus;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author zeev7
 */
public class AfterKillMove extends Move {

    public AfterKillMove(board moveBoard, Handler.color thisColor, ArrayList<Integer> steps) {
        super(moveBoard, thisColor);
        start();
        super.steps = steps;
        if (check()) {
            themove();
        }
    }

    /**
     * plase - the end of the board
     * start - for the function thet chek to out
     */
    int start, place;

    /**
     * this function to start the variables for according to player color
     */
    public void start() {

        if (thisColor == color.whith) {

            start = place = 1;

        } else {

            start = 20;
            place = 26;
        }
    }


    /**
     * this function chek if there is eny option to put in a kill piece 
     * @return 
     */
    public boolean check() {
        ColorStatus squareColor, anmyColor;
        int num;
        if (thisColor == color.whith) {
            anmyColor = ColorStatus.Black;
        } else {
            anmyColor = ColorStatus.White;
        }
        for (int i = 0; i < 6; i++) {
            squareColor = super.moveBoard.getStatus(i + start);
            num = super.moveBoard.getNum(i + start);
            if (!(squareColor == anmyColor && num > 1)) {
                return true;
            }
        }

        return false;
    }

    /**
     * this function do the actually move 
     */
    public void themove() {
        Scanner in = new Scanner(System.in);
        boolean Bool1, Bool2;
        int numOfKill = super.moveBoard.getNum(place);

        // chek if thre us option to put in piece 
        Bool1 = super.moveBoard.chekForIn(place + steps.get(0), thisColor);
        Bool2 = super.moveBoard.chekForIn(place + steps.get(1), thisColor);

        if (Bool1 && !Bool2) {

            super.moveBoard.theMove(thisColor, place, place + steps.get(0));
            super.steps.remove(0);
        } else if (!Bool1 && Bool2) {

            super.moveBoard.theMove(thisColor, place, place + steps.get(1));
            super.steps.remove(1);
        } else if (numOfKill >= 2 && Bool1 && Bool2) {

            super.moveBoard.theMove(thisColor, place, place + steps.get(0));
            super.moveBoard.theMove(thisColor, place, place + steps.get(1));
            super.steps.remove(1);
            super.steps.remove(0);
            if (numOfKill > 2 && super.steps.size() > 0) {
                for (int i = 0; i < (numOfKill - 2); i++) {

                    super.moveBoard.theMove(thisColor, place, place + steps.get(1));
                }
            }
        } else if (Bool1 && Bool2) {
            if (steps.get(0) == steps.get(1)) {
                super.moveBoard.theMove(thisColor, place, place + steps.get(0));
                super.steps.remove(0);
            } else {
                System.out.println(abs(steps.get(0)) + " or " + abs(steps.get(1)) + " ?");
                int num;
                num = in.nextInt();
                while (!(num == abs(steps.get(1)) || num == abs(steps.get(0)))) {
                    System.out.println("erorr");
                    num = in.nextInt();
                }
                if (thisColor == color.whith) {
                    super.moveBoard.theMove(thisColor, place, place + num);
                } else {
                    super.moveBoard.theMove(thisColor, place, place - num);
                }
                 // to do for black !!!!
                if (abs(steps.get(0)) == num) {
                    super.steps.remove(0);
                } else {
                    super.steps.remove(1);
                }
            }

        }

    }


        /*    public void next() {
        
        int numOfKill = super.moveBoard.getNum(place);
        if (numOfKill > 0) {
        return;
        }
        //to do next torn is normal
        if (steps.size() > 0) {
        super.game();
        }
        
        }*/

}
