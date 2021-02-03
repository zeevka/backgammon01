/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.Server;

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
    
    
    public enum playerStatus{
    
        inGame,
        wait,
        no,
        yes
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
