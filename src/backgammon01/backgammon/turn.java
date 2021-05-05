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
import backgammon01.Server.player;

/**
 * for evry torn i open new object
 *
 * @author zeev7
 */
public class turn {

    public turn(board turnBoard, Game.color playerColor, player playerSender, player player2Sender) {
        this.player1 = playerSender;
        this.player2 = player2Sender;
        this.turnBoard = turnBoard;
        this.playerColor = playerColor;
        turnBoard.print();

        steps = startSteps();
        dises tmpDises = new dises(steps);
        sendObject(14, tmpDises, 1, playerSender);
        sendObject(14, tmpDises, 0, player2Sender);

        status = chekForKills();

        if (status == turnStatus.next) {
            turnMove();
        }

    }

    /*    public turn(board GameBoard, color color) {
     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }*/
    private player player1, player2;
    
    private board turnBoard;
    private Game.color playerColor;
    private int numOfKills;
    private Move TheMove;
    private boolean iswait;
    private turnStatus status;

    public ArrayList<Integer> steps = new ArrayList<Integer>();

    /**
     * this is function thet give the steps, number btuein 1-6
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
    public int chekTOout(){

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
    public turnStatus chekForKills() {
        //if there is eny kill pieces
        if (getNumOfKills(playerColor) > 0) {
            TheMove = new AfterKillMove(turnBoard, playerColor, steps);

            if (TheMove.isWait()) {
                iswait = true;
                sendId(23, player1);
                return turnStatus.witeToAfterKill;
            }

            // if still hve a kill pieces thet - 
            if (getNumOfKills(playerColor) > 0) {
                return turnStatus.finish;
            }

            if (steps.size() == 0) {
                sendId(22, player1);
                return turnStatus.finish;

            }

        }else{
             TheMove = new Move(turnBoard, playerColor);
        }
        return turnStatus.next;
    }

    public void turnMove() {

        Move move;

        // if there is to meny pieces out of the aria
        if (chekTOout() > steps.size()) {
            status = turnStatus.onlyMove;
            TheMove = new Move(turnBoard, playerColor, steps);
        } else {
            if (chekTOout() == 0) {
                status=turnStatus.onlyMoveToEnd;
                TheMove = new moveToEnd(turnBoard, playerColor, steps);

            } else {
                status = turnStatus.needToCHeck;
                TheMove = new Move(turnBoard, playerColor, steps.get(0));
               
            }
        }

    }

    public void doStep(int from, int to, int step) {
        // todo send  to client the risulte of do step
        // todo if the resulte is true send the step

        boolean flag;

        flag = TheMove.doStep(from, to);
        if (flag) {

            while (TheMove.getMovesSize() > 0) {
                sendObject(15, TheMove.getStep(0), 1, player1);
                sendObject(16, TheMove.getStep(0), 0, player2);
                TheMove.removeStep(0);
            }

            switch (status) {

                case witeToAfterKill:
                    flag = TheMove.doStep(from, to);

                    if (TheMove.isWait()) {

                        return;
                    } else {

                        turnMove();
                    }
                    break;

                case onlyMove:

                    break;

                case onlyMoveToEnd:

                    if (TheMove.getMovesSize() > 0) {

                        sendSteps();
                    }
                    break;

                case needToCHeck:

                    if (TheMove.getMovesSize() > 0) {

                        sendSteps();
                    }
                    if (chekTOout() == 0) {
                        TheMove = new moveToEnd(turnBoard, playerColor, steps);
                        status = turnStatus.onlyMoveToEnd;
                    }
                    break;

            }
        } else {

            sendId(17, player1);

        }

        if (steps.size() == 0) {
            sendId(11, player1);
            status = turnStatus.finish;
        }
    }
    
    public void sendSteps(){
    
        while(TheMove.getMovesSize()==0){
        
             sendObject(12,(Object)TheMove.getStep(0),1,player1);
             sendObject(12,(Object)TheMove.getStep(0),0,player2);
            
             TheMove.removeStep(0);
        }
    }
    public void sendWait(){
    
        sendId(13, player1);
    }

    public void sendObject(int id, Object toSed, int token,player playerToSend) {

       
        playerToSend.sendObject(id, toSed, token);
    }

    public void sendId(int id, player playerToSend) {
            playerToSend.sendObject(id, null, 0);
        //playerToSend.sendOBJ(null, id);
        
    }
    
    public void sendId(int id, player playerToSend, int token) {

         playerToSend.sendObject(id, null, token);
       
    }

    public turnStatus getStatus() {
        return status;
    }
    

    public enum turnStatus {

        next,
        witeToAfterKill,
        onlyMove,
        onlyMoveToEnd,
        needToCHeck,
        finish

    }
}
