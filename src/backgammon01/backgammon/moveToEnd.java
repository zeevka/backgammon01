/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.backgammon;

import TurenLibrey.messages.Step;
import backgammon01.Server.Game;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author zeev7
 */
public class moveToEnd extends Move {

    public moveToEnd(board moveBoard, Game.color thisColor, ArrayList<Integer> steps) {
        super(moveBoard, thisColor);
        super.steps = steps;
        initialization();
        if (!auto()) {
            super.wait = true;
        }
    }
    private int plase;
    private int plaseForFunk;
    private int index;

    public final void initialization() {
        if (thisColor == Game.color.whith) {
            plase = 27;
            plaseForFunk = 20;
            index = 1;
        } else {
            plase = 0;
            plaseForFunk = 8;
            index = -1;
        }
    }

    /**
     * this function chek if it's automaton to take out pieces if yes she do it
     *
     * @return false if it not automaton
     */
    public boolean auto() {
        int tmp, numof = 0, theTyni;
        Step tmperry;

        if (steps.size() > 1 && abs(steps.get(0)) > abs(steps.get(1))) {
            theTyni = abs(steps.get(1));
        } else {
            theTyni = abs(steps.get(0));
        }

        for (tmp = plaseForFunk; tmp != plase - theTyni - index; tmp += index) {

            if (moveBoard.getStatus(tmp) == moveBoard.change(thisColor)) {// todo chek if after ove the square is empty
                return false;
            }
        }

        for (; tmp != plase - index && steps.size() > 0; tmp += index) {
            if (moveBoard.getStatus(tmp) == moveBoard.change(thisColor)) {
                numof = moveBoard.getNum(tmp);

                while (steps.size() > 0 && numof > 0) {
                    if (!moveBoard.theMove(thisColor, tmp, plase)) {
                        System.err.println("erorr in aoto movetoend");
                        return false;
                    }
                    tmperry = new Step(0, tmp, plase);
                    moves.add(tmperry);
                    steps.remove(0);
                    numof -= 1;
                }
                if (moveBoard.getNum(plase) == 15) {
                    System.out.println("we heve a winer !! ");
                    System.exit(0);
                }
            }
        }
        return true;
    }

    @Override
    public boolean doStep(int from, int stepToDo) {

        if (from + steps.get(stepToDo) <= 1 || from + steps.get(stepToDo) >= 26) {
            return false;
        }

        //--
        if (from + steps.get(0) + index == plase) {
            moveBoard.theMove(thisColor, from, plase);
        } else {
            moveBoard.theMove(thisColor, from, from + steps.get(stepToDo));
        }
        steps.remove(stepToDo);
        auto();
        return true;
    }
    /**
     * this fonctin do the mive out
     */
    /*    public void mteMove() {
     int tmp, theTyni;
     Scanner s = new Scanner(System.in);
    
     if (steps.size() > 1 && abs(steps.get(0)) > abs(steps.get(1))) {
     theTyni = abs(steps.get(1));
     } else {
     theTyni = abs(steps.get(0));
     }
    
     if (auto(theTyni)) {//if it automaton to take out brake..
     return;
     }
     while (steps.size() > 0) {
    
     if (auto(theTyni)) {//if it automaton to take out brake..
     break;
     }
     tmp = 0;
     while (true) {
     // tmp =lis.giv(0);
     tmp = s.nextInt();
     //first check if in square tmp there is piece   next- chek if there is optien to do it
     if (moveBoard.chekForOut(tmp, thisColor) && (tmp + steps.get(0) >= 1 && tmp + steps.get(0) <= 26)) {
     if (tmp + steps.get(0) + index == plase) {
     moveBoard.theMove(thisColor, tmp, plase);
     } else {
     moveBoard.theMove(thisColor, tmp, tmp + steps.get(0));
     }
     steps.remove(0);
     break;
     }
     System.out.println("erorr");
     }
    
     }
    
     }*/

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
