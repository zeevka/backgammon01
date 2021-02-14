/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.Server;

import TurenLibrey.messages.Message;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zeev7
 */
public class sender extends Thread {

    public sender(Socket net) {
        this.net = net;

    }

    public sender(Socket net, Object obj, int id, int token) {
        this.net = net;

        messege = new Message(id, obj, token);

    }

    public sender(Socket net, String str, int id, int token) {
        this.net = net;

        messege = new Message(id, (Object) str, token);

    }

    private Socket net;
    private ObjectOutputStream out;
    private Message messege;

    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(net.getOutputStream());

        } catch (IOException ex) {
            Logger.getLogger(sender.class.getName()).log(Level.SEVERE, null, ex);
        }
        send(messege);
    }

    public void send(Message mes) {

        try {
            out.writeObject(mes);
        } catch (IOException ex) {
            Logger.getLogger(sender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void sendString(String str, int id, int token){
    
        Message  a = new Message(id, (Object) str, token);
        send(a);
    }
    
    public void sendOBJ(Object obj, int id) {

        Message  a = new Message(id, obj, 0);
        send(a);
    }

    public Socket getNet() {
        return net;
    }

    public void setNet(Socket net) {
        this.net = net;
    }

    public Message getMessege() {
        return messege;
    }

    public void setMessege(Message messege) {
        this.messege = messege;
    }

}
