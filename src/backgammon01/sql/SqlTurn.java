/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.sql;

import backgammon01.Server.Game;
import backgammon01.backgammon.board;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author zeev7
 */
public class SqlTurn extends sqlMain implements Serializable{
    

    private board theBoard;
    private Game.color playerColor;
    private int numOfKills;
    private ArrayList<Integer> steps = new ArrayList<Integer>(); 
    private ArrayList<Integer> stepsThetDo = new ArrayList<Integer>(); 
    
    
    
}
