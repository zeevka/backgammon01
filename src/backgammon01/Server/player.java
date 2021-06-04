/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.Server;

import TurenLibrey.messages.Message;
import backgammon01.backgammon.turn;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zeev7
 */
public class player {

    public player(Socket net) {
        this.net=net;
    }

    public player() {
    }
    
    
    
    private Socket net;
    private int token;
    private playerStatus status;
    private sender send;
    private Listener playerListiner;
    private ObjectOutputStream out;
    
    
    
    public enum playerStatus{
    
        inGame,
        wait,
        no,
        yes,
        no2
    }
    
    public enum validation{
   
         ialid,
         not_valid,
         Inull
        
    }
    
    
    //////////////////////////////////////////////////////////////////////////////////////
    //fonction
    

    public void sendObject(int id ,Object toSed,int token){
        sender tmpSend;
        tmpSend = new sender(net, toSed,id,token,out);
        tmpSend.start();
    }
    
    public void setTurn(turn tmp){
    
        playerListiner.setMyTurn(tmp);
        
    }
        
    
    /////////////////////////////////////////////////////////////////////////////////////
    
    //geters and seters
    

    public player(Socket net, int token, playerStatus status) {
        this.net = net;
        this.token = token;
        this.status = status;
    }

    public Socket getNet() {
        return net;
    }

    public void setNet(Socket net) {
        this.net = net;
    }

    public int getToken() {
        return token;
    }

    public void setToken(int token) {
        this.token = token;
    }

    public playerStatus getStatus() {
        return status;
    }

    public void setStatus(playerStatus status) {
        this.status = status;
    }

    public sender getSend() {
        return send;
    }

    public void setSend(sender send) {
        this.send = send;
    }

    public Listener getPlayerListiner() {
        return playerListiner;
    }

    public void setPlayerListiner(Listener playerListiner) {
        this.playerListiner = playerListiner;
    }
    
    public void setlis(){
           playerListiner = new Listener(net);
    }
    
    public void setlis(ObjectInputStream in){
           playerListiner = new Listener(net,in);
           playerListiner.start();
    }

    public ObjectOutputStream getOut() {
        return out;
    }

    public void setOut(ObjectOutputStream out) {
        this.out = out;
    }
    
    
    
    
}
