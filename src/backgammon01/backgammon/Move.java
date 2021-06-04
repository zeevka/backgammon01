/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.backgammon;

import backgammon01.Server.Game.color;
import java.util.ArrayList;
import TurenLibrey.messages.Step;

/**
 *
 * @author zeev7
 */
public class Move {

    /**
     * this is constractor for the successors
     *
     * @param moveBoard get the board
     * @param thisColor
     */
    public Move(board moveBoard, color thisColor) {
        this.moveBoard = moveBoard;
        this.thisColor = thisColor;
    }

    /**
     * this is constractor for normal move for all the steps
     *
     * @param moveBoard get the board
     * @param thisColor get the player color
     * @param steps get the result from the dice
     */
    public Move(board moveBoard, color thisColor, ArrayList<Integer> steps) {
        this.moveBoard = moveBoard;
        this.thisColor = thisColor;
        this.steps = steps;
    }

    /**
     * this is constractor for normal move for one step wiche
     *
     * @param moveBoard get the board
     * @param thisColor get the player color
     * @param stam yhe step to do
     */
    public Move(board moveBoard, color thisColor, int stam) {

        this.moveBoard = moveBoard;
        this.thisColor = thisColor;
        this.steps = new ArrayList<>();
        steps.add(stam);

    }

    protected color thisColor;
    protected board moveBoard;
    protected boolean wait;
    ArrayList<Integer> steps = new ArrayList<Integer>();
    ArrayList<Step> moves = new ArrayList<Step>();
    

    public boolean doStep(int from, int stepToDo){

        System.out.println("from: "+ from + "step: "+ stepToDo);
        if(steps.size()<stepToDo){
            return false;
        }
        if( moveBoard.theMove(thisColor, from, from + steps.get(stepToDo))){
            steps.remove(stepToDo);
            return true;
        }else{
            return false;
        }
       
    }

///////////////////////////////////////////////////////////////////////////////
    //-- geter and seter
    
    public boolean isWait() {
        return wait;
    }

    public void setWait(boolean wait) {
        this.wait = wait;
    }

    public ArrayList<Step> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Step> moves) {
        this.moves = moves;
    }
        
    public int getMovesSize() {
        return moves.size();
    }    
    
    public Step getStep(int stepIndex){
    
        return moves.get(stepIndex);
    }
    
    public void removeStep(int stepIndex){
    
        moves.remove(stepIndex);
    }
    
}

