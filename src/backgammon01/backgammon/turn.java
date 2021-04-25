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

    public turn(board turnBoard, Game.color playerColor, sender playerSender, sender player2Sender) {
        this.playerSender = playerSender;
        this.player2Sender = player2Sender;
        this.turnBoard = turnBoard;
        this.playerColor = playerColor;
        turnBoard.print();

        steps = startSteps();
        dises tmpDises = new dises(steps);
        sendObject(14, tmpDises, 1, playerSender);
        sendObject(14, tmpDises, 0, player2Sender);

        status = chekForKills();

        if(TheMove.getMovesSize()>0){
                
                    sendSteps();
                }
        if (status == turnStatus.next) {
            turnMove();
        }

    }

    /*    public turn(board GameBoard, color color) {
     throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
     }*/
    private sender playerSender, player2Sender;
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
                sendId(23, playerSender);
                return turnStatus.witeToAfterKill;
            }

            // if still hve a kill pieces thet - 
            if (getNumOfKills(playerColor) > 0) {
                return turnStatus.finish;
            }

            if (steps.size() == 0) {
                sendId(22, playerSender);
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
        if(flag){
        
            while(TheMove.getMovesSize()>0){
                sendObject(15, TheMove.getStep(0), 1, playerSender);
                sendObject(16, TheMove.getStep(0), 0, player2Sender);
                TheMove.removeStep(0);
            }
            
        }else{
        
        
            sendId(17, playerSender);
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
                
                if(TheMove.getMovesSize()>0){
                
                    sendSteps();
                }
                break;

            case needToCHeck:
                
                if(TheMove.getMovesSize()>0){
                
                    sendSteps();
                }
                if (chekTOout() == 0) {
                    TheMove = new moveToEnd(turnBoard, playerColor, steps);
                    status = turnStatus.onlyMoveToEnd;
                }
                break;

        }
        
        if (steps.size() == 0) {
            sendId(11, playerSender);
            status = turnStatus.finish; 
        }
    }
    
    public void sendSteps(){
    
        while(TheMove.getMovesSize()==0){
        
             sendObject(12,(Object)TheMove.getStep(0),1,playerSender);
             sendObject(12,(Object)TheMove.getStep(0),0,player2Sender);
            
             TheMove.removeStep(0);
        }
    }

    public void sendObject(int id, Object toSed, int token, sender playerToSend) {

        playerToSend.sendOBJ(toSed, id, token);
    }

    public void sendId(int id, sender playerToSend) {

        playerToSend.sendOBJ(null, id);
    }
    
    public void sendId(int id, sender playerToSend, int token) {

        playerToSend.sendOBJ(null, id);
    }

    enum turnStatus {

        next,
        witeToAfterKill,
        onlyMove,
        onlyMoveToEnd,
        needToCHeck,
        finish

    }
}
