/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.backgammon;

import backgammon01.Server.Server;
import backgammon01.Server.Handler.color;
import backgammon01.sql.sqlMain;
import java.sql.SQLException;
import backgammon01.Server.player;
import backgammon01.sql.logIn;
/**
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
        
        /*        board a = new board();
        Move b;
        turn c;
        
        while (true) {
        c = new turn(a, color.whith);
        c = null;
        c = new turn(a, color.black);
        c = null;
        }*/
        
      //  sqlMain a = new sqlMain();
        //Server a = new Server();
        player a = new player();
        logIn b = new logIn("aaa","aaa",a);
        Thread c = new Thread(b);
        c.start();
        //System.out.println(player.getToken());
       // logIn c = new logIn();
        
    }

}
