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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zeev7
 */
public class Handler extends Thread{

    public Handler(Socket player1, Socket player2) {
        this.GameBoard = new board();
        this.player1=player1;
        this.player2=player2;
        
        try {
            lis1=new Listener(player1);
            lis2=new Listener(player2);

        } catch (IOException ex) {
            Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
   private board GameBoard;
    private Socket player1, player2;
    private Listener lis1, lis2;
    private turn c;


    @Override
    public void run() {
        lis1.start();
        lis2.start();
        while (true) {
        c = new turn(GameBoard, color.whith,lis1);
        c = null;
        c = new turn(GameBoard, color.black,lis2);
        c = null;
        }
    }

   public enum color{
    
        whith,
        black;
    
    }

   
}
