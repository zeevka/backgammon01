/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.backgammon;

import backgammon01.Server.Game;
import backgammon01.Server.Game.color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import TurenLibrey.messages.dises;
import backgammon01.Server.player;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * for evry torn i open new object
 *
 * @author zeev7
 */
public class turn {

    public turn(board turnBoard, Game.color playerColor, player player1, player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.turnBoard = turnBoard;
        this.playerColor = playerColor;

        turnBoard.print();

        starting();

    }

    private player player1, player2;

    private board turnBoard;
    private Game.color playerColor;
    private int numOfKills;
    private Move TheMove;
    private boolean iswait;
    private turnStatus status;

    public ArrayList<Integer> steps = new ArrayList<Integer>();

    ////////////////////////////////////////////////////////////////
    //-- function
    
    private void starting() {

        steps = startSteps();
        dises tmpDises = new dises(steps);
        sendObject(14, tmpDises, 1, player1);
        sendObject(14, tmpDises, 0, player2);

        Iwait();

        status = chekForKills();

        if (status == turnStatus.next) {
            turnMove();
        }
    }

    /**
     * this is function thet give the steps, number btuein 1-6
     *
     * @return number btwine 1-6
     */
    private int dice() {
        Random rand = new Random();
        return rand.nextInt(6) + 1;
    }

    private void Iwait() {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(turn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * start the steps with random numbers, if has double thet need for steps
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

    }

    /**
     * this function chek if there is kill pieces, if so thet
     * @return 
     */
    public turnStatus chekForKills() {

        turnStatus toReturn;
        //if there is eny kill pieces
        if (getNumOfKills(playerColor) > 0) {
            TheMove = new AfterKillMove(turnBoard, playerColor, steps);
            automatic();
            chekForFinish();

            //if the client need to choose plase for is kill pice
            if (TheMove.isWait()) {
                iswait = true;
                sendWait();
                //sendId(23, player1);
                toReturn = turnStatus.witeToAfterKill;

                // if still hhve a kill pieces thet - 
            } else if (getNumOfKills(playerColor) > 0) {
                toReturn = turnStatus.finish;
            } else {
                toReturn = turnStatus.next;
            }

        } else {
            toReturn = turnStatus.next;
        }
        return toReturn;
    }

    public void turnMove() {

        Move move;
        int toOut = chekTOout();

        // if there is to meny pieces out of the aria
        if (toOut > steps.size()) {
            status = turnStatus.onlyMove;
            TheMove = new Move(turnBoard, playerColor, steps);
        } else {
            if (toOut == 0) {
                status = turnStatus.onlyMoveToEnd;
                newMoveToEndHendler();

            } else {
                status = turnStatus.needToCHeck;
                TheMove = new Move(turnBoard, playerColor, steps);

            }
        }

    }

    public void doStep(int from, int to, int step) {
        // todo send  to client the risulte of do step
        // todo if the resulte is true send the step

        boolean flag;

        flag = TheMove.doStep(from, to);
        automatic();
        if (TheMove.isWait()) {
            sendWait();
            return;
        }

        if (flag) {

            switch (status) {

                case witeToAfterKill:
                    turnMove();
                    break;

                case onlyMove:
                case onlyMoveToEnd:

                    break;

                case needToCHeck:

                    if (TheMove.getMovesSize() > 0) {

                    }
                    if (chekTOout() == 0) {

                        status = turnStatus.onlyMoveToEnd;
                        newMoveToEndHendler();
                    }
                    break;

            }
        } else {

            sendId(17, player1);

        }

        chekForFinish();
    }

    private void newMoveToEndHendler() {
        TheMove = new moveToEnd(turnBoard, playerColor, steps);
        automatic();
        chekForFinish();
    }
    
    private void newMoveHendler() {
        TheMove = new Move(turnBoard, playerColor, steps);
        sendWait();
    }
    

    private void automatic() {

        while (TheMove.getMovesSize() > 0) {
            sendObject(15, TheMove.getStep(0), 1, player1);
            sendObject(16, TheMove.getStep(0), 0, player2);
            TheMove.removeStep(0);
        }

    }

    private void chekForFinish() {
        if (steps.size() == 0) {
            sendId(11, player1);
            status = turnStatus.finish;
        }
    }
    
    
    public void sendWait() {

        sendId(13, player1);
    }

    //////////////////////////////////////////////////////////////////////////////
    //--send to the client
    public void sendSteps() {

        while (TheMove.getMovesSize() == 0) {

            sendObject(12, (Object) TheMove.getStep(0), 1, player1);
            sendObject(12, (Object) TheMove.getStep(0), 0, player2);

            TheMove.removeStep(0);
        }
    }


    public void sendObject(int id, Object toSed, int token, player playerToSend) {

        playerToSend.sendObject(id, toSed, token);
    }

    public void sendId(int id, player playerToSend) {
        playerToSend.sendObject(id, null, 0);
        //playerToSend.sendOBJ(null, id);

    }

    public void sendId(int id, player playerToSend, int token) {

        playerToSend.sendObject(id, null, token);

    }
////////////////////////////////////////////////////////////////////////////////

    public turnStatus getStatus() {
        return status;
    }

    public enum turnStatus {

        next,
        witeToAfterKill,
        onlyMove,
        onlyMoveToEnd,
        needToCHeck,
        finish,
        win

    }
}
