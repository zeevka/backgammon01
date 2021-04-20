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

    public boolean isWait() {
        return wait;
    }

    public void setWait(boolean wait) {
        this.wait = wait;
    }
    
    
    
    
    
        public void game() {
        System.err.println("this function is dead");
        /*        Scanner in = new Scanner(System.in);
        int tmp, thestep;
        
        for (int index = 0; index < steps.size(); index++) {
        tmp = in.nextInt();
        
        // tmp = lis.giv(0);
        
        thestep = steps.get(index);
        while (!(moveBoard.chekForOut(tmp, thisColor))
        || !(moveBoard.chekForIn(tmp + thestep, thisColor))) {
        System.out.println("error");
        tmp = in.nextInt();
        }
        moveBoard.theMove(thisColor, tmp, tmp + thestep);
        }*/
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

/*    public ArrayList<Integer> startMove(){

 ArrayList<Integer> tmp = new ArrayList<Integer>
 (Arrays.asList(dice(),dice()));

 if (tmp.get(0)==tmp.get(1)) {

 tmp.addAll(Arrays.asList(tmp.get(0),tmp.get(0)));
 }

 System.out.println(tmp.get(0)+" "+tmp.get(1));

 if(thisColor==color.black){
 for(int i=0;i<tmp.size();i++){

 tmp.set(i,tmp.get(i)*-1);

 }
 //  for(int i =0; i<tmp.size();i++){System.out.println(tmp.get(i));}
 }

 return tmp;
 }


 private int dice(){
 Random rand = new Random();
 return rand.nextInt(6)+1;
 }*/
/*    private void findPlace(){
 ColorStatus squareColor= moveBoard.change(thisColor);
 int reelstep1 = step1*-1;
 int reelstep2 = step2*-1;
    
 for(int index = 0;index<24;index++){
    
 if(moveBoard.)
    
 }
    
 }*/
/*
 for themove
    
            
 place1 = in.nextInt();
 while (!(moveBoard.chekForOut(place1, thisColor))||
 !(moveBoard.chekForIn(place1, thisColor))) {            
 System.out.println("error");
 place1 = in.nextInt();
 }
 moveBoard.theMove(thisColor,place1, place1 + step1);
        
 ///
 place2 = in.nextInt();
 while (!(moveBoard.chekForOut(place1, thisColor))||
 !(moveBoard.chekForIn(place1, thisColor))) {            
 System.out.println("error");
 place1 = in.nextInt();
 }
        
        
 moveBoard.theMove(thisColor,place1,place1 + step2);
       
    
    
 */
