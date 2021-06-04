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

    public newGame(int token1, int token2, GameSqlHendler game) {

        this.game = game;
        tokens =new String[2];
        tokens[0]= ""+token1;
        tokens[1]= ""+token2;
    }

    private GameSqlHendler game;
    private ResultSet rs;
    private player player1,player2; 
    private String[] tokens;
    
    
    public void run() {
        int tmp=0;
       rs = whithResultSet("declare @tmp int exec @tmp = new_game ? , ? "
                         + " select @tmp as tmp",tokens);
        try {
             while (rs.next()) {
                tmp=rs.getInt("tmp");
             }
        } catch (SQLException ex) {
            System.err.println("database error");
            Logger.getLogger(newGame.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        if(tmp!=0){
            game.setGameId(tmp);
        }else{
            System.err.println("databas result set problem ");
        }
        
       
    }
    
}
/*
 rs = whithResultSet("declare @tmp int"
                         + " declare @i1 int declare @i2 int"
                         + " declare @s1 varchar(10) declare @s2 varchar(10)"
                         + " set @s1 = ? set @s2 = ?"
                         + " set @i1 = convert(INT,@s1) set @i2 = convert(INT,@s2)"
                         + " exec @tmp = new_game @i1 , @i2 "
                         + " select @tmp as tmp",tokens);
*/