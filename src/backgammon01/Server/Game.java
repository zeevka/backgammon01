/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.Server;

import backgammon01.backgammon.board;
import backgammon01.backgammon.turn;
import TurenLibrey.messages.Matrix;
import java.util.Random;

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

        System.out.println("new game ");
        starting();
       
    }

    private int a;
    private board GameBoard;
    private player player1, player2;
    private Listener lis1, lis2;
    private turn wite, black;

    @Override
    public void run() {
        
        while (true) {
        wite = new turn(GameBoard, color.whith, player1, player2);
        player1.setTurn(wite);
        black = new turn(GameBoard, color.black, player2, player1);
        player2.setTurn(black);
        
        }
    }

    private int getRand() {
        Random rand = new Random();
        return rand.nextInt(2);
    }
    
    public void starting(){
        player1.sendObject(10, null,10);
        player2.sendObject(10, null,11);
        System.out.println("GAME: send message");
        
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

    public turn getWite() {
        return wite;
    }

    public void setC(turn c) {
        this.wite = c;
    }

}
