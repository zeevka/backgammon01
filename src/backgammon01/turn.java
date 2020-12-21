/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01;

import backgammon01.Server.Handler;
import backgammon01.Server.Handler.color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 *
 * @author zeev7
 */
public class turn {

    public turn(board turnBoard,Handler.color playerColor) {
        this.turnBoard=turnBoard;
        this.playerColor=playerColor;
    }
    
private board turnBoard;    
private Handler.color playerColor;
private int step1,step2;
private int numOfKills;
ArrayList<Integer> steps = new ArrayList<Integer>();   
    
    
    private int dice(){
        Random rand = new Random();
        return rand.nextInt(6)+1;
    }
    
    public ArrayList<Integer> startMove(){
    
        ArrayList<Integer> tmp = new ArrayList<Integer>
                                (Arrays.asList(dice(),dice()));

        if (tmp.get(0)==tmp.get(1)) {
            
            tmp.addAll(Arrays.asList(tmp.get(0),tmp.get(0)));
        }
    
        System.out.println(tmp.get(0)+" "+tmp.get(1));
        
        if(playerColor==Handler.color.black){
        for(int i=0;i<tmp.size();i++){
            
            tmp.set(i,tmp.get(i)*-1);
            
        }
    }
        
        return tmp;
    }
    
    public int getNumOfKills(color playerColor){
    
        int tmp =1;
        if(playerColor==color.black){
        tmp =26;
        }
        return turnBoard.getNum(tmp);
    } 
        public boolean chekTOout() {

        int sum = 0, num, plase,i;
        
        if (playerColor == Handler.color.whith) {
            num = 20;
            plase = 27;
        } else {
            num = 2;
            plase = 0;
        }

        for (i = 0; i > 6; i++) {
            if (turnBoard.getStatus(num+i)==turnBoard.change(playerColor)) {
                sum += turnBoard.getNum(num+i);
            }
        }

        if (sum == (15-turnBoard.getNum(plase))) {
            return true;
        }

        return false;
    }
    public void turnMove(){
    
        Move move;
        if(getNumOfKills(playerColor)>0){
          move = new AfterKillMove(turnBoard, playerColor);
          
          if(getNumOfKills(playerColor)>0){
          return;
          }
          
        }
        
        while (steps.size()>0) {            
           if(chekTOout()){
           
           }
        }
        
    }
}
