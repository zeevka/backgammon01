/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.Server;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zeev7
 */
public class conectClients extends Thread{

    public conectClients() {
     players = new ArrayList<player>();
     numOfWaits=0;
        System.out.println("conect Clients ");
    }

    
    
    private ArrayList<player> players;
    private int numOfWaits;

    @Override
    public void run() {

        conect();
    }
    
    public void conect(){

        while (true){
            if(players.size()>=2){
                for(int index =0;index < players.size();index++){
                    if(players.get(index).getStatus()==player.playerStatus.wait){
                        System.out.println("1 client wait");
                        
                        index=onePlay(index);
                    }
                }
            }
        
            try {
                Thread.sleep(20);
            } catch (InterruptedException ex) {
                Logger.getLogger(conectClients.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public int onePlay(int playerIndex) {
        Game newGame;
        int index = playerIndex + 1;
        for (; index < players.size(); index++) {
            if (players.get(index).getStatus() == player.playerStatus.wait) {
                System.out.println("2 client wait");

                players.get(playerIndex).setStatus(player.playerStatus.inGame);
                players.get(index).setStatus(player.playerStatus.inGame);

                newGame = new Game(players.get(playerIndex), players.get(index));
                newGame.start();
                numOfWaits -= 2;

                break;
            }

        }
        return index;
    }
    public void add(player toAdd){
    
        players.add(toAdd);
        System.out.println("adddddd");
        
        
    }
    
}
