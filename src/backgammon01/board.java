/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01;

import backgammon01.Server.Handler.color;
import backgammon01.square.ColorStatus;
import java.util.ArrayList;

/**
 *
 * @author zeev7
 */
public class board {

    public board() {
        theBoard = new square[28];
        theBoard = startBord();
        

    }

    public final int N = 28;
    private square[] theBoard;
    

    /**
     * 
     * @param thisColor - the color of the player
     * @param out - the number of the square thet is taken out of it 
     * @param in - the number of the square thet is put of it
     * @return 
     */
    public boolean theMove(color thisColor, int out, int in) {

        ColorStatus tmpColor = theBoard[in].getStatus();//the color of the square
        ColorStatus  playerColor = change(thisColor);// the color of the flyer in colorStatus
        
  
       if(tmpColor==ColorStatus.empty ){//if the square is empty 
       
           theBoard[in].setStatus( playerColor);
           theBoard[in].setAmountOfPieces(1);
       }else if(tmpColor==playerColor){
        theBoard[in].setAopPlusOne();
       }else{//thre is only one thet belongs to the opponent
       
           if(thisColor==color.whith){
           
               theBoard[26].setAopPlusOne();   
           }else{
                theBoard[1].setAopPlusOne();
           }
       
           theBoard[in].setStatus( playerColor);
       }
        
       theBoard[out].setAopMinusOne();

        return true;

    }
    
    
    
    
   public boolean chekForOut(int space, color pliercolor) {
         ColorStatus tmpColor = theBoard[space].getStatus();
         int num=theBoard[space].getAmountOfPieces();
         

         if(change(pliercolor)==tmpColor&&num>0){
            return true;
         }
        return false;
    }

    public boolean chekForIn(int space, color Pliercolor) {

        ColorStatus tmpColor = theBoard[space].getStatus();
        

        if (theBoard[space].getAmountOfPieces() <= 1) {

            return true;
            
        }
        if(change(Pliercolor)==tmpColor){
            return true;
        }


        return false;
    }

    /**
     * this function is for start the bord inn the beginin of the game
     *
     * @return bord thet start
     *
     *
     *
     *
     */
    private square[] startBord() {

        square[] board;
        board = new square[N];
        for (int i = 0; i < N / 2; i++) {

            switch (i) {
                case 0:
                case 1:
                    board[i] = new square(0, square.ColorStatus.White);
                    board[N - i-1] = new square(0, square.ColorStatus.Black);
                    break;
                case 2:
                    board[i] = new square(2, square.ColorStatus.White);  
                    board[N - i-1] = new square(2, square.ColorStatus.Black);
                    break;

                case 7:
                    board[i] = new square(5, square.ColorStatus.Black);
                    board[N - i-1] = new square(5, square.ColorStatus.White);
                    break;
                case 9:
                    board[i] = new square(3, square.ColorStatus.Black);
                    board[N - i-1] = new square(3, square.ColorStatus.White);
                    break;
                case 13:
                    board[i] = new square(5, square.ColorStatus.White);
                    board[N - i-1] = new square(5, square.ColorStatus.Black);
                    break;

                default:
                    board[i] = new square(0, square.ColorStatus.empty);
                    board[N - i-1] = new square(0, square.ColorStatus.empty);

            }

        }
        
        return board;
        

    }
    
    public ColorStatus change(color a){
    
        if(a==color.whith){
        
            return ColorStatus.White;
        }
        
        return ColorStatus.Black;
      
    }

    
    
    
    // tmperry ontil grafic
    public void print() {
        int tmp;
        int tmp2 = 2;
        

            for (int j = 0; j < 12; j++) {
                tmp = theBoard[j + tmp2].getAmountOfPieces();
                if (theBoard[j + tmp2].getStatus() == ColorStatus.Black) {
                    tmp *= -1;
                }
                System.out.printf("%4d",tmp);
                
            }
            System.out.println("");
        
            for (int j = 0; j < 12; j++) {
                tmp = theBoard[27-(j + tmp2)].getAmountOfPieces();
                if (theBoard[27-(j + tmp2)].getStatus() == ColorStatus.Black) {
                    tmp *= -1;
                }
                System.out.printf("%4d",tmp);
                
                
                
            }
            System.out.println("");
            System.out.println("");

    }
    public int getNum(int square){
    
        return theBoard[square].getAmountOfPieces();
    }
    public ColorStatus  getStatus(int square){
    
    return theBoard[square].getStatus();
    }

    
    
}


