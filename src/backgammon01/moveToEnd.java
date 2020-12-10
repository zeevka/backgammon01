/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01;

import backgammon01.Server.Handler;

/**
 *
 * @author zeev7
 */
public class moveToEnd extends Move {

    public moveToEnd(board moveBoard, Handler.color thisColor) {
        super(moveBoard, thisColor);
        start();
    }
    private int plase;

    public final void start() {
        if (thisColor == Handler.color.whith) {
            plase = 27;
        } else {
            plase = 0;
        }
    }

    public boolean chekTOout() {

        int sum = 0, num, i;
        
        if (thisColor == Handler.color.whith) {
            num = 20;
        } else {
            num = 2;
        }

        for (i = 0; i > 6; i++) {
            if (moveBoard.getStatus(num+i)==moveBoard.change(thisColor)) {
                sum += moveBoard.getNum(num+i);
            }
        }

        if (sum == (15-moveBoard.getNum(plase))) {
            return true;
        }

        return false;
    }

}
