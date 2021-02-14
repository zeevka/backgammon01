/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.Server;

import backgammon01.backgammon.board;
import backgammon01.backgammon.turn;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zeev7
 */
public class Game extends Thread {

    public Game(player playeri1, player playeri2) {
        this.GameBoard = new board();
        if (getRand() == 0) {
            this.player1 = playeri1;
            this.player2 = playeri2;
        } else {
            this.player1 = playeri2;
            this.player2 = playeri1;
        }

        starting();
        System.out.println("new game ");
    }

    private int a;
    private board GameBoard;
    private player player1, player2;
    private Listener lis1, lis2;
    private turn c;

    @Override
    public void run() {
        /*        lis1.start();
        lis2.start();
        while (true) {
        c = new turn(GameBoard, color.whith, lis1);
        c = null;
        c = new turn(GameBoard, color.black, lis2);
        c = null;
        }*/
    }

    private int getRand() {
        Random rand = new Random();
        return rand.nextInt(2);
    }
    
    public void starting(){
    
        player1.sendString(10, "welcome white",10);
        player1.sendString(10, "welcome black",11);
        
    
    }

    public enum color {

        whith,
        black;
    }

   //////////////////////////////////////////////
    //geters and seters
    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public board getGameBoard() {
        return GameBoard;
    }

    public void setGameBoard(board GameBoard) {
        this.GameBoard = GameBoard;
    }

    public Listener getLis1() {
        return lis1;
    }

    public void setLis1(Listener lis1) {
        this.lis1 = lis1;
    }

    public Listener getLis2() {
        return lis2;
    }

    public void setLis2(Listener lis2) {
        this.lis2 = lis2;
    }

    public turn getC() {
        return c;
    }

    public void setC(turn c) {
        this.c = c;
    }

}
