/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.sql;

import backgammon01.Server.player;
import backgammon01.Server.Game;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zeev7
 */
public class newGame extends sqlMain implements Runnable{

    public newGame(player player1, player player2) {
        this.player1 = player1;
        this.player2 = player2;
        tokens[0]= ""+player1.getToken();
        tokens[1]= ""+player2.getToken();
    }

    private Game game;
    private ResultSet rs;
    private player player1,player2; 
    private String[] tokens =new String[2];
    
    
    public void run() {
        int tmp=0;
       rs = whithResultSet("declare @tmp int exec @tmp= new_game ?,?"
               + " select @tmp as tmp",tokens);
       
        try {
            tmp=rs.getInt("tmp");
        } catch (SQLException ex) {
            Logger.getLogger(newGame.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        if(tmp!=0){
            game.setA(tmp);
        }
       
    }
    
}
