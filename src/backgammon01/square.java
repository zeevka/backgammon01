/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01;

import java.util.ArrayList;

/**
 *
 * @author zeev7
 */
public class square {

    public square(int amountOfPieces, ColorStatus status) {
        this.amountOfPieces = amountOfPieces;
        this.status = status;
    }

    private int amountOfPieces;
    private ColorStatus status;

    public int getAmountOfPieces() {
        return amountOfPieces;
    }

    public void setAmountOfPieces(int amountOfPieces) {
        this.amountOfPieces = amountOfPieces;
    }

    public ColorStatus getStatus() {
        return status;
    }

    public void setStatus(ColorStatus status) {
        this.status = status;
    }

    /**
     * set the amountOfPieces plus one
     */
    public void setAopPlusOne() {

        this.amountOfPieces++;
    }

    /**
     * set the amountOfPieces minus one
     */
    public void setAopMinusOne() {

        this.amountOfPieces--;
        if (amountOfPieces == 0) {
            status = ColorStatus.empty;
        }
    }

    public enum ColorStatus {

        empty,
        Black,
        White

    }

}
