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
        board a=new board();
        //a.print();
        Move b;
        turn c;
        
        while (true) {            
            c=new turn(a, color.whith);
            c=null;
            c=new turn(a, color.black);
            c=null;
        }
        
        
        
        /*        while (true) {
        doMove(color.whith,a);
        doMove(color.black,a);
        
        
        }*/
    }
    /*    public static boolean chekforkiil(color playerColor,board a){
    if(playerColor==color.whith&&a.getNum(1)>0){
    return true;
    }else if(playerColor==color.black&&a.getNum(26)>0){
    return true;
    }
    return false;
    }*/
    /*    public static void doMove(color playerColor, board a) {
    Move b;
    if (chekforkiil(playerColor,a)) {
    b = new AfterKillMove(a, playerColor);
    } else {
    b = new Move(a, playerColor, 0);
    }
    b=null;
    }
    }*/
}

