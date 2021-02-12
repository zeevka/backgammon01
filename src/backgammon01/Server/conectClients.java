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

    
    private ArrayList<player> players = new ArrayList<player>();

    @Override
    public void run() {

        conect();
    }
    
    public void conect(){
    
        player player1,player2;
        Game handler;
        while (true){
        
            if(players.size()>=2){
            
                for(int index =0;index >players.size();index++){
                
                    if(players.get(index).getStatus()==player.playerStatus.wait){
                    player1=players.get(index);
                        for(;index >players.size();index++){
                        
                            if(players.get(index).getStatus()==player.playerStatus.wait){
                            player2=players.get(index);
                                handler=new Game(player1.getNet(), player2.getNet());
                                player1=player2=null;
                                break;
                            }
                        }
                    }
                }
            }
        
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(conectClients.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void add(player toAdd){
    
        players.add(toAdd);
        
    }
    
}
