/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01;

import backgammon01.Server.Handler;
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
    
}
