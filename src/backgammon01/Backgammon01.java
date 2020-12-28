/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01;

import backgammon01.Server.Handler;
import backgammon01.Server.Handler.color;

/**
 *
 * @author zeev7
 */
public class Backgammon01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        // to do server !
        // to do qa to move to end
        
        board a = new board();
        Move b;
        turn c;
        
        while (true) {
        c = new turn(a, color.whith);
        c = null;
        c = new turn(a, color.black);
        c = null;
        }

    }

}
