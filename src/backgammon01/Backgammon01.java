/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01;

import backgammon01.Server.Handler;
import backgammon01.Server.Handler.color;
import backgammon01.sql.sqlMain;
import java.sql.SQLException;

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
        
        sqlMain a = new sqlMain();
    }

}
