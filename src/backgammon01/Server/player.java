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

        send = new sender(net, (Object)toSed,id,token);
        send.start();
        
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
    
    
}
