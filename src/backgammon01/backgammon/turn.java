/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.backgammon;

import backgammon01.Server.Game;
import backgammon01.Server.Game.color;
import backgammon01.Server.sender;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import TurenLibrey.messages.Message;
import TurenLibrey.messages.dises;

/**
 * for evry torn i open new object
 *
 * @author zeev7
 */
public class turn {

    public turn(board turnBoard, Game.color playerColor,sender playerSender, sender player2Sender) {
        this.playerSender =playerSender;
        this.turnBoard = turnBoard;
        this.playerColor = playerColor;
        turnBoard.print();

        steps=startSteps();
        dises tmpDises = new dises(steps);
        sendObject(14, tmpDises, 0);
        
        turnMove();
    }

    
    /*    public turn(board GameBoard, color color) {
    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    private sender playerSender;
    private board turnBoard;
    private Game.color playerColor;
    private int numOfKills;
    private Move TheMove;
    
    public ArrayList<Integer> steps = new ArrayList<Integer>();


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
        if (playerColor == Game.color.black) {
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

        if (playerColor == Game.color.whith) {
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
    public int chekForKills(){
    Move move;
        if (getNumOfKills(playerColor) > 0) {
            TheMove = new AfterKillMove(turnBoard, playerColor, steps);
            // if still hve a kill pieces thet - 
            if (getNumOfKills(playerColor) > 0) {
                sendString(21, "you stiil have a kills",0);
                return 0;
            }
            if(steps.size()==0){
                sendString(22, "you don't have any more steps",0);
                return 0;
            }
            
        }
        return 1;
    }
    
    public void turnMove() {

        Move move;
        //if there is eny kill pieces
        if (getNumOfKills(playerColor) > 0) {
            TheMove = new AfterKillMove(turnBoard, playerColor, steps);
            // if still hve a kill pieces thet - 
            if (getNumOfKills(playerColor) > 0) {
                return;
            }

        }
        // if there is to meny pieces out of the aria
        if (chekTOout() > steps.size()) {
            TheMove = new Move(turnBoard, playerColor, steps);
        } else {

            while (steps.size() > 0) {
                // chek evry step if cen move to end
                if (chekTOout() == 0) {
                    TheMove = new moveToEnd(turnBoard, playerColor,steps);
                    break;
                } else {
                    TheMove = new Move(turnBoard, playerColor, steps.get(0));
                    steps.remove(0);
                }
            }
        }
    }
    
    public void sendString (int id ,String toSed,int token){

        Message tmp = new Message(id, (Object)toSed,token);
        playerSender.setMessege(tmp);
        playerSender.start();
    
    }
       public void sendObject (int id ,Object toSed,int token){

        Message tmp = new Message(id,toSed,token);
        playerSender.sendOBJ(toSed, id);
    
    }
    enum turnStatus{
    
        witeToAfterKill,
        
    }
}
