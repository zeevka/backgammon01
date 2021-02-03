/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.sql;
import backgammon01.Server.player.playerStatus;
import backgammon01.Server.player;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author zeev7
 */
public class logIn extends sqlMain implements Runnable{

    public logIn(String userName, String password, player player) {
        this.sqlSt = new String[]{userName, password};
        this.player = player;
    }
    
    private String [] sqlSt;
    private player player;
    private int token;
    private ResultSet tmp;

    @Override
    public void run() {
        try {
            tmp = whithResultSet("declare @tmp int exec @a= login_P ?,? select @tmp as tmp",sqlSt);
            while(tmp.next()){
                token=tmp.getInt("tmp");
            }
        } catch (SQLException ex) {
           token=0;
        }
        
        if(token==0){
            player.setStatus(playerStatus.no);
            
        }else{
            player.setStatus(playerStatus.yes);
        }
        player.setToken(token);
        System.out.println(token);
    }
    
}
