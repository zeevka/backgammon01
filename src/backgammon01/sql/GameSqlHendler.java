/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.sql;

/**
 *
 * @author zeev7
 */
public class GameSqlHendler{
    
    
    public GameSqlHendler(int token1, int token2){
        
        Thread tmpthThread = new Thread(new newGame(token1, token2, this));
        tmpthThread.start();
        
    }
    
    private int gameId;
    private int token1,token2;

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }
    
    
}
