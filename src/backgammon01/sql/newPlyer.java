/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.sql;

import backgammon01.Server.player;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zeev7
 */
public class newPlyer extends Thread {

    public newPlyer(String name, String password,player player) {
        this.name = name;
        this.password = password;
        this.player=player;
    }

    private player player;
    private String name, password;
    private String SQL_url = "jdbc:sqlserver://localhost:1433;"
            + "databaseName=Backgammon;integratedSecurity=true";
    private String SPsql = "EXEC new_player ?,?";

    @Override
    public void run() {
        Connection conn;
        try {
            
            conn = DriverManager.getConnection(SQL_url);
            PreparedStatement ps = conn.prepareStatement(SPsql);
            ps.setEscapeProcessing(true);
            ps.setString(1, name);
            ps.setString(2, password);
            ps.executeQuery();
        } catch (SQLException ex) {
            System.err.println("database prublem");
            Logger.getLogger(newPlyer.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
