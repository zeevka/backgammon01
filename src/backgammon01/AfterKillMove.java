/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01;

import backgammon01.Server.Handler;
import backgammon01.Server.Handler.color;
import backgammon01.square.ColorStatus;
import static java.lang.Math.abs;
import java.util.Scanner;

/**
 *
 * @author zeev7
 */
public class AfterKillMove extends Move {

    public AfterKillMove(board moveBoard, Handler.color thisColor) {
        super(moveBoard, thisColor);
        start();
        if (check()) {

            themove();
            next();
        }
    }

    int start, place;

    public void next() {

        int numOfKill = super.moveBoard.getNum(place);
        if(numOfKill>0){
        return;
        }
        //to do next torn is normal
        if(steps.size()>0){
        super.game();
        }

    }

    public void themove() {
        Scanner in = new Scanner(System.in);
        boolean Bool1, Bool2;
        int numOfKill = super.moveBoard.getNum(place);

        Bool1 = super.moveBoard.chekForIn(place + step1, thisColor);
        Bool2 = super.moveBoard.chekForIn(place + step2, thisColor);

        if (Bool1 && !Bool2) {

            super.moveBoard.theMove(thisColor, place, place + step1);
            super.steps.remove(0);
        } else if (!Bool1 && Bool2) {

            super.moveBoard.theMove(thisColor, place, place + step2);
            super.steps.remove(1);
        } else if (numOfKill >= 2 && Bool1 && Bool2) {

            super.moveBoard.theMove(thisColor, place, place + step1);
            super.moveBoard.theMove(thisColor, place, place + step2);
            super.steps.remove(1);
            super.steps.remove(0);
            if (numOfKill > 2 && super.steps.size() > 0) {
                for (int i = 0; i < (numOfKill - 2); i++) {
                    
                    super.moveBoard.theMove(thisColor, place, place + step1);
                }
            }
        }else if(numOfKill==1&&Bool1&&Bool2){
            
            System.out.println(abs(steps.get(0))+" or "+abs(steps.get(1))+" ?");
            int num;
            num =in.nextInt();
            while(!(num==abs(steps.get(1))||num==abs(steps.get(0)))){
                System.out.println("erorr");
                num =in.nextInt();
            }
            
            super.moveBoard.theMove(thisColor, place, place + num);
             super.steps.remove(0);
        }

    }

    public void start() {

        if (thisColor == color.whith) {

            start = place = 1;

        } else {

            start = 20;
            place = 26;
        }
    }

    public boolean check() {
        ColorStatus squareColor, anmyColor;
        int num;
        if (thisColor == color.whith) {
            anmyColor = ColorStatus.Black;
        } else {
            anmyColor = ColorStatus.White;
        }
        for (int i = 0; i < 6; i++) {
            squareColor = super.moveBoard.getStatus(i + start);
            num = super.moveBoard.getNum(i + start);
            if (!(squareColor == anmyColor && num > 1)) {
                return true;
            }
        }

        return false;
    }
}
