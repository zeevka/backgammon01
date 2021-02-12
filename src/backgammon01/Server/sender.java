/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.Server;
import TurenLibrey.messages.Message;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zeev7
 */
public class sender extends Thread{

    public sender(Socket net,Object obj,int id, int token) {
        this.net = net;
        
        messege=new Message(id, obj,token);
        
    }

    
    
    private Socket net;
    private ObjectOutputStream out;
    private Message messege;
    
    @Override
    public void run() {
        try {
            out = new ObjectOutputStream(net.getOutputStream());
            out.writeObject(messege);
        } catch (IOException ex) {
            Logger.getLogger(sender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
}
