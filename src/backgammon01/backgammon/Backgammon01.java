/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.backgammon;


import TurenLibrey.messages.mesLogin;
import backgammon01.Server.Server;
import backgammon01.Server.Game.color;
import backgammon01.sql.sqlMain;
import java.sql.SQLException;
import backgammon01.Server.player;
import backgammon01.sql.newPlyer;
import java.util.Random;
/**
 *
 * 
 * @author zeev7
 */
public class Backgammon01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        // to do server !
        // to do if isn't have any plase to move

        /*        player a = new player();
        mesLogin l = new mesLogin("aaa","aaa");
        newPlyer n = new newPlyer(null, null, a);*/
        
      Server s = new Server();
    }

}
