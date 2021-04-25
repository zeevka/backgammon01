/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.Server;

import TurenLibrey.messages.Message;
import java.net.Socket;

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
    
    public void sendString(int id ,String toSed,int token){

        System.err.println("erorrr send string");

        
    }
    public void sendObject(int id ,Object toSed,int token){
    sender tmpSend;
        tmpSend = new sender(net, toSed,id,token);
        tmpSend.start();
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
    
    
    
    
}
