/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.Server;

import backgammon01.backgammon.board;
import backgammon01.backgammon.turn;
import TurenLibrey.messages.Matrix;
import backgammon01.backgammon.turn.turnStatus;
import backgammon01.sql.GameSqlHendler;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zeev7
 */
public class Game extends Thread {

    public Game(player playeri1, player playeri2) {
        this.GameBoard = new board();
        /*if (getRand() == 0) {
        this.player1 = playeri1;
        this.player2 = playeri2;
        } else {
        this.player1 = playeri2;
        this.player2 = playeri1;
        }*/
        this.player1 = playeri1;
        this.player2 = playeri2;
        sql = new GameSqlHendler(playeri1.getToken(), playeri2.getToken());
        System.out.println("new game ");
        starting();
       
    }

    private int a;
    private board GameBoard;
    private player player1, player2;
    private turn wite, black;
    private Timer turnTimer;
    private TimerTask turnTimerTask;
    public int time;
    private GameSqlHendler sql;

    @Override
    public void run() {
        
        while (true) {
        wite = new turn(GameBoard, color.whith, player1, player2);
        player1.setTurn(wite);
        wait(wite);
        //sendBoard();
        
        black = new turn(GameBoard, color.black, player2, player1);
        player2.setTurn(black);
        wait(black);
       // sendBoard();
        }
    }

    /**
     * this method returm a random number 0 or 1 
     * @return 
     */
    private int getRand() {
        Random rand = new Random();
        return rand.nextInt(2);
    }
    
    public void starting(){
        
        
        player1.sendObject(10, null,10);
        player2.sendObject(10, null,11);

        
        sendBoard();
       
    }
    

    /**
     * this methode make sure thet client dont wasting time
     * @param toWait 
     */
    public void wait(turn toWait) {
        time = 0;
        turnTimer = new Timer();
        turnTimerTask = new TimerTask() {

            @Override
            public void run() {
                time++;
                if (time == 120) {
                    System.out.println("to long");
                }
            }
        };
        
        turnTimer.schedule(turnTimerTask, 1000, 250);
        
        while (toWait.getStatus() != turnStatus.finish) {
            try {
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
    
    public void doTurn(color theturn){
    
        
    }
    
    // this function send the boars to the clients
    public void sendBoard(){
        Matrix tmpMatrix = new Matrix(GameBoard.toIntArray());
        player1.sendObject(11, (Object) tmpMatrix, 0);
        player2.sendObject(11, (Object) tmpMatrix, 0);
    }
    
    
    public enum color {

        whith,
        black;
    }

   //////////////////////////////////////////////
    //geters and seters

    public board getGameBoard() {
        return GameBoard;
    }

    public void setGameBoard(board GameBoard) {
        this.GameBoard = GameBoard;
    }

    public turn getWite() {
        return wite;
    }

    public void setC(turn c) {
        this.wite = c;
    }

}
