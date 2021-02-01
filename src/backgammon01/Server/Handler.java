/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.Server;

import backgammon01.backgammon.board;
import backgammon01.backgammon.turn;
import java.net.Socket;

/**
 *
 * @author zeev7
 */
public class Handler extends Thread{

    public Handler(Socket player1, Socket player2) {
        this.GameBoard = new board();
        this.player1=player1;
        this.player2=player2;
    }
    
   private board GameBoard;
    private Socket player1, player2;
    private Listener lis1, lis2;
    private turn c;


    @Override
    public void run() {
        while (true) {   
        c = new turn(GameBoard, color.whith);
        c = null;
        c = new turn(GameBoard, color.black);
        c = null;
        }
    }

   public enum color{
    
        whith,
        black;
    
    }

   
}
