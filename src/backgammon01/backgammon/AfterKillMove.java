/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.backgammon;

import TurenLibrey.messages.Step;
import backgammon01.Server.Game;
import backgammon01.Server.Game.color;
import java.util.ArrayList;

/**
 *
 * @author zeev7
 */
public class AfterKillMove extends Move {

    public AfterKillMove(board moveBoard, Game.color thisColor, ArrayList<Integer> steps) {
        super(moveBoard, thisColor);
        initialization();
        super.steps = steps;
        themove();
        
    }

    /**
     * plase - the end of the board 
     */
    private int place;
    private boolean option = false;
    

    /**
     * this function to start the variables for according to player color
     */
    public void initialization() {

        option = false;
        if (thisColor == color.whith) {
           place = 1;
        } else {
            place = 26;
        }
    }


    /**
     * this function do the actually move
     */
    public void themove() {
        Step tmp;
        boolean Bool1, Bool2;
        int numOfKill = super.moveBoard.getNum(place);

        // chek if thre us option to put in piece 
        Bool1 = super.moveBoard.chekForIn(place + steps.get(0), thisColor);
        Bool2 = super.moveBoard.chekForIn(place + steps.get(1), thisColor);

        if (!Bool1&&!Bool2){
            return;
        }
        if (Bool1 && !Bool2) {

            super.moveBoard.theMove(thisColor, place, place + steps.get(0));
            tmp = new Step(0, place, place + steps.get(0));
            moves.add(tmp);
            super.steps.remove(0);
        } else if (!Bool1 && Bool2) {

            super.moveBoard.theMove(thisColor, place, place + steps.get(1));
            tmp = new Step(1, place, place + steps.get(0));
            moves.add(tmp);
            super.steps.remove(1);
        } else if (Bool1 && Bool2) {
            if (numOfKill >= 2) {
                int i =0;
                while( numOfKill > 0 && super.steps.size() > 0) {
                    super.moveBoard.theMove(thisColor, place, place + steps.get(0));
                    tmp = new Step(i++, place, place + steps.get(0));
                    moves.add(tmp);
                    super.steps.remove(0);
                }

            } else {
                if (steps.get(0) == steps.get(1)) {
                    super.moveBoard.theMove(thisColor, place, place + steps.get(0));
                    tmp = new Step(0, place, place + steps.get(0));
                    moves.add(tmp);
                    super.steps.remove(0);
                } else {
                    option= true;
                    //todo
                    //System.out.println(abs(steps.get(0)) + " or " + abs(steps.get(1)) + " ?");
                }

            }

        }
    }
    
    @Override
    public boolean doStep(int from, int stepToDo){
    Step tmp;
        if(from != -1){
            return false;
        }
        if(stepToDo != 0 && stepToDo != 1){
            return false;
        }

        if( super.moveBoard.theMove(thisColor, place, place + super.steps.get(stepToDo))){
            tmp = new Step(stepToDo, place, place + steps.get(0));
            moves.add(tmp);
            steps.remove(stepToDo);
            return true;
        }else{
            return false;
        }
    }

    public boolean isOption() {
        return option;
    }
}

    /**
     * this function chek if there is eny option to put in a kill piece
     *
     * @return
     *
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
*/

/* 
                    option= true;
                    System.out.println(abs(steps.get(0)) + " or " + abs(steps.get(1)) + " ?");
                    int num=0;
                    // num = in.nextInt();
                    //   num = lis.giv(0);a
                    
                    while (!(num == abs(steps.get(1)) || num == abs(steps.get(0)))) {
                        System.out.println("erorr");
                        //  num = in.nextInt();
                        //     num=lis.giv(0);
                    }
                    if (thisColor == color.whith) {
                        super.moveBoard.theMove(thisColor, place, place + num);
                    } else {
                        super.moveBoard.theMove(thisColor, place, place - num);
                    }
                    // to do for black !!!!
                    if (abs(steps.get(0)) == num) {
                        super.steps.remove(0);
                    } else {
                        super.steps.remove(1);
                    }

public void next() {
        
 int numOfKill = super.moveBoard.getNum(place);
 if (numOfKill > 0) {
 return;
 }
 //to do next torn is normal
 if (steps.size() > 0) {
 super.game();
 }
        
 }

    
    
    
    
    
    public void doPlqyerChoice(boolean q) {

        int p = 0;
        if(q){
        p=1;
        }
        if (thisColor == color.whith) {
            super.moveBoard.theMove(thisColor, place, place + abs(steps.get(p)));
        } else {
            super.moveBoard.theMove(thisColor, place, place - abs(steps.get(p)));
        }
        // to do for black !!!!
            super.steps.remove(p);
       
    }

*/
