/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.backgammon;

import backgammon01.Server.Game.color;
import backgammon01.backgammon.square.ColorStatus;
import java.util.ArrayList;

/**
 *
 * @author zeev7
 */
public class board {

    public board() {
        theBoard = new square[28];
        theBoard = startBord();
       // theBoard= newBord();

    }

    public final int N = 28;
    private square[] theBoard;

    /**
     * this function do the move
     *
     * @param thisColor - the color of the player
     * @param out - the number of the square thet is taken out of it
     * @param in - the number of the square thet is put of it
     * @return
     */
    public boolean theMove(color thisColor, int out, int in) {

        ColorStatus tmpColor = theBoard[in].getStatus();//the color of the square
        ColorStatus playerColor = change(thisColor);// the color of the flyer in colorStatus

        
        if (tmpColor == ColorStatus.empty) {//if the square is empty 

            theBoard[in].setStatus(playerColor);
            theBoard[in].setAmountOfPieces(1);
        } else if (tmpColor == playerColor) {//need only to add 
            theBoard[in].setAopPlusOne();
        } else {//thre is only one thet belongs to the opponent

            if (thisColor == color.whith) {

                theBoard[26].setAopPlusOne();
            } else {
                theBoard[1].setAopPlusOne();
            }

            theBoard[in].setStatus(playerColor);
        }

        theBoard[out].setAopMinusOne();

        return true;

    }

    /**
     * this function chek if there is piece to take out
     *
     * @param space get the number of square to chek if thre is there a piece
     * @param pliercolor get the color of the player
     * @return true if there is piece there or fals if there isn't
     */
    public boolean chekForOut(int space, color pliercolor) {

        ColorStatus tmpColor = theBoard[space].getStatus();
        int num = theBoard[space].getAmountOfPieces();

        if (change(pliercolor) == tmpColor && num > 0) {
            return true;
        }
        return false;
    }

    /**
     * this function chek if there is option to put in a piece
     *
     * @param space get the number of square to chek if thre is option to put in
     * a piece
     * @param pliercolor get the color of the player
     * @return true if there is a option or fals if there isn't
     */
    public boolean chekForIn(int space, color Pliercolor) {

        ColorStatus tmpColor = theBoard[space].getStatus();

        if (theBoard[space].getAmountOfPieces() <= 1) {

            return true;

        }
        if (change(Pliercolor) == tmpColor) {
            return true;
        }

        return false;
    }

    /**
     * this function is for start the bord inn the beginin of the game
     *
     * @return bord thet start
     *
     */
    //after this function the board wil look like thet:
    //*****************--****************
    //**  2 0 0 0 0 -5    0 -3 0 0 0  5**
    //** -2 0 0 0 0  5    0  3 0 0 0 -5**
    //******************-****************
    
    private square[] startBord() {

        square[] board;
        board = new square[N];
        for (int i = 0; i < N / 2; i++) {

            switch (i) {
                case 0:
                    board[i] = new square(0, square.ColorStatus.Black);
                    board[N - i - 1] = new square(0, square.ColorStatus.White);
                    break;
                case 1:
                    board[i] = new square(0, square.ColorStatus.White);
                    board[N - i - 1] = new square(0, square.ColorStatus.Black);
                    break;
                case 2:
                    board[i] = new square(2, square.ColorStatus.White);
                    board[N - i - 1] = new square(2, square.ColorStatus.Black);
                    break;

                case 7:
                    board[i] = new square(5, square.ColorStatus.Black);
                    board[N - i - 1] = new square(5, square.ColorStatus.White);
                    break;
                case 9:
                    board[i] = new square(3, square.ColorStatus.Black);
                    board[N - i - 1] = new square(3, square.ColorStatus.White);
                    break;
                case 13:
                    board[i] = new square(5, square.ColorStatus.White);
                    board[N - i - 1] = new square(5, square.ColorStatus.Black);
                    break;

                default:
                    board[i] = new square(0, square.ColorStatus.empty);
                    board[N - i - 1] = new square(0, square.ColorStatus.empty);

            }

        }

        return board;

    }

    /**
     * this function is only for me!!!!!!!!!!!!!!!! to chek the code !
     *
     * @return
     */
    private square[] newBord() {

        //                    0  1  2  3  4  5  6  7 - 8  9 10 11 12 13
        int  [] s =new int[]{ 0 ,0 ,-15 ,0 ,0 ,0 ,0 ,0  ,0 ,0 ,0 ,0 ,0 ,0,
                              0 ,0 ,0 ,0 ,0 ,0  ,0 ,0 ,0 ,0 ,0 ,15 ,0 ,0};
        //                   14 15 16 17 18 19 - 20 21 22 23 24 25 26 27 
        
        square[] board;
        board = new square[N];
        for (int i = 0; i < N ; i++) {

            if(s[i]==0){
                board[i] = new square(0, square.ColorStatus.empty);
            }else if(s[i]>0){
                board[i] = new square(s[i], square.ColorStatus.White);
            }else{
                board[i] = new square(s[i]*-1, square.ColorStatus.Black);
            }
            

        }

        return board;

    }

    /**
     * this function is to get the color of the pleyer and return this color in
     * square
     *
     * @param a get the color of player
     * @return color of square
     */
    public ColorStatus change(color a) {

        if (a == color.whith) {

            return ColorStatus.White;
        }

        return ColorStatus.Black;

    }

    /**
     * this is tmperry function to print the board
     */
    public void print() {
        int tmp;
        int tmp2 = 2;

        for (int j = 0; j < 12; j++) {
            tmp = theBoard[j + tmp2].getAmountOfPieces();
            if (theBoard[j + tmp2].getStatus() == ColorStatus.Black) {
                tmp *= -1;
            }
            System.out.printf("%4d", tmp);

        }
        System.out.println("");

        for (int j = 0; j < 12; j++) {
            tmp = theBoard[27 - (j + tmp2)].getAmountOfPieces();
            if (theBoard[27 - (j + tmp2)].getStatus() == ColorStatus.Black) {
                tmp *= -1;
            }
            System.out.printf("%4d", tmp);

        }
        System.out.println("");
        System.out.println("");

    }

    /**
     * this function is to get a number of square and returnn the amount of
     * pieces to rhere is in this square
     *
     * @param square the number of square
     * @return the amount of pieces
     */
    public int getNum(int square) {

        return theBoard[square].getAmountOfPieces();
    }

    /**
     * this function is to get a number of square and returnn the color-status
     * of this sqare
     *
     * @param square
     * @return
     */
    public ColorStatus getStatus(int square) {

        return theBoard[square].getStatus();
    }
    
    /**
     * this function take the board and convert it to int array 
     * @return 
     */
    public int[] toIntArray() {
        int[] send = new int[27];
        int tmp;

        for (int index = 0; index < 27; index++) {

            tmp=theBoard[index].getAmountOfPieces();
            
            if(theBoard[index].getStatus()==ColorStatus.Black){
                tmp*=-1;
            }
            send[index]=tmp;
        }
        return send;
    }

}
