/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.sql;

import backgammon01.Server.player.playerStatus;
import backgammon01.Server.player;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zeev7
 */
public class newPlyer extends sqlMain implements Runnable {

    public newPlyer(String name, String password, player player) {
        this.name = name;
        this.password = password;
        this.player = player;
    }

    private player player;
    private String name, password;
    private ResultSet tmp;
    private String[] sqlSt;
    private logIn logIn;

    @Override
    public void run() {

       String[] sqlSt = new String[2];
       sqlSt[0]=name;
       sqlSt[1]=password;
               
        tmp = whithResultSet("declare @tmp int exec @tmp= new_player ?,? select @tmp as tmp", sqlSt);
        
        try {
            if (tmp.next()) {
                if (tmp.getInt("tmp") == 0) {
                    player.setStatus(playerStatus.no);
                   
                } else {
                     player.setStatus(playerStatus.yes);
                }
            } else {
                System.err.println("problem");
            }

        } catch (SQLException ex) {
            Logger.getLogger(newPlyer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
