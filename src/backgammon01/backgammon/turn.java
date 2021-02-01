/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.backgammon;

import backgammon01.Server.Handler;
import backgammon01.Server.Handler.color;
import backgammon01.Server.hendler_turn;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * for evrt torn i open new object
 *
 * @author zeev7
 */
public class turn {

    public turn(board turnBoard, Handler.color playerColor,hendler_turn a) {
        this.turnBoard = turnBoard;
        this.playerColor = playerColor;
        turnBoard.print();
        this.a=a;

     //   System.out.println("\n" + turnBoard.getNum(1) + "  " + turnBoard.getNum(26) + "\n");
        steps = startSteps();
        a.send(steps.get(0) + " " + steps.get(1));
       // System.out.println(steps.get(0) + " " + steps.get(1));
        turnMove();
    }

    private board turnBoard;
    private Handler.color playerColor;
    private int numOfKills;
    private hendler_turn a;
    ArrayList<Integer> steps = new ArrayList<Integer>();

    public turn(board GameBoard, color color) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * this is function thet give the steps,
     *
     * @return number btwine 1-6
     */
    private int dice() {
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }

    /**
     * art the steps with random numbers, if has double thet need for steps
     *
     * @return list with the numbers
     */
    public ArrayList<Integer> startSteps() {

        ArrayList<Integer> tmp = new ArrayList<Integer>(Arrays.asList(dice(), dice()));

        if (tmp.get(0) == tmp.get(1)) {

            tmp.addAll(Arrays.asList(tmp.get(0), tmp.get(0)));
        }
        if (playerColor == Handler.color.black) {
            for (int i = 0; i < tmp.size(); i++) {

                tmp.set(i, tmp.get(i) * -1);

            }
        }

        return tmp;
    }

    /**
     * this functin give the number of kill pieces
     *
     * @param playerColor
     * @return the number of kill pieces
     */
    public int getNumOfKills(color playerColor) {

        int tmp;
        if (playerColor == color.whith) {
            tmp = 1;
        } else {//cheking for blak
            tmp = 26;
        }
        return turnBoard.getNum(tmp);
    }

    /**
     * this function chek if thre is option to take out pieces
     *
     * @return the number of pieces thet is not
     *
     */
    public int chekTOout() {

        int sum = 0, num, plase, i;

        if (playerColor == Handler.color.whith) {
            num = 20;
            plase = 27;
        } else {
            num = 2;
            plase = 0;
        }


        
        for (i = 0; i < 6; i++) {
            if (turnBoard.getStatus(num + i) == turnBoard.change(playerColor)) {
                sum += turnBoard.getNum(num + i);
            }
        }
        return (15 - turnBoard.getNum(plase) - sum);
        /*        if (sum == (15-turnBoard.getNum(plase))) {
         return true;
         }

         return false;*/
    }

    /**
     * this function do the moves
     */
    public void turnMove() {

        Move move;
        //if there is eny kill pieces
        if (getNumOfKills(playerColor) > 0) {
            move = new AfterKillMove(turnBoard, playerColor, steps);
            // if still hve a kill pieces thet - 
            if (getNumOfKills(playerColor) > 0) {
                return;
            }

        }
        // if there is to meny pieces out of the aria
        if (chekTOout() > steps.size()) {
            move = new Move(turnBoard, playerColor, steps);
        } else {

            while (steps.size() > 0) {
                // chek evry step if cen move to end
                if (chekTOout() == 0) {
                    move = new moveToEnd(turnBoard, playerColor,steps);
                    break;
                } else {
                    move = new Move(turnBoard, playerColor, steps.get(0));
                    steps.remove(0);
                }
            }
        }
    }
}
