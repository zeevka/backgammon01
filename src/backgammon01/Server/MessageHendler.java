/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.Server;

import TurenLibrey.messages.Message;
import TurenLibrey.messages.Step;
import backgammon01.backgammon.turn;

/**
 *
 * @author zeev7
 */
public class MessageHendler {

    public MessageHendler(Message theMessage) {
        
        this.theMessage = theMessage;
        this.id = theMessage.getId();
        this.obj= theMessage.getObj();
        hendler();
    }
    public MessageHendler(Message theMessage, turn theTurn) {
        
        this.theMessage = theMessage;
        this.id = theMessage.getId();
        this.obj= theMessage.getObj();
        this.theTurn = theTurn;
        hendler();
    }
  
    
    private Game playerGame;
    private Message theMessage;
    private int id;
    private Object obj;
    private turn theTurn;
    
    public void hendler(){
        
    boolean tmp;
        System.out.println("get masseg: "+id);
        switch(id){
        
            case 110: 
                Step a = (Step) obj;
               //theTurn.doStep(a.getFrom(),a.getTo(), a.getDise());
                theTurn.doStep(a);
                break;
                
                

        }
    
    }
    
    
}
