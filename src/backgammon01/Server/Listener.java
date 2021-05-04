/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.Server;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import TurenLibrey.messages.Message;
import backgammon01.backgammon.turn;
import java.io.ObjectInputStream;

/**
 *
 * @author zeev7
 */
public class Listener extends Thread {

    public Listener(Socket client){
         
        this.client = client;
        messeges = new ArrayList<Message>();

    }
    public Listener(Socket client, ObjectInputStream in) {
         
        this.client = client;
        this.in = in;
        messeges = new ArrayList<Message>();

    }

    private Socket client;
    private ObjectInputStream in;
    private String str;
    private ArrayList<Message> messeges;
    private ArrayList<Integer> waits = new ArrayList<>();
    private boolean isCame;
    private turn myturn;

    @Override
    public void run() {
        try {
            in = new ObjectInputStream(client.getInputStream());
            System.out.println("ss");
            theListener();
        } catch (IOException ex) {
            Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void theListener() {
        Message tmp;
        while (true) {
            tmp=null;
            try {
                tmp = (Message) in.readObject();
                MessageHendler hendler = new MessageHendler(tmp,myturn);
                in = new ObjectInputStream(client.getInputStream());
            } catch (IOException ex) {
                Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
            }
            
          
        }

    }
    
    public Message getMesseg(int token){

        while(true){
        
            
        }
    }

    public ArrayList<Message> getMesseges() {
        return messeges;
    }

    public void setMesseges(ArrayList<Message> messeges) {
        this.messeges = messeges;
    }

    public ArrayList<Integer> getWaits() {
        return waits;
    }

    public void setWaits(ArrayList<Integer> waits) {
        this.waits = waits;
    }

    public boolean isIsCame() {
        return isCame;
    }

    public void setIsCame(boolean isCame) {
        this.isCame = isCame;
    }

    public void setMyTurn(turn myTurn) {
        this.myturn = myTurn;
    }
    
    
    

}
/*  
 @Override
 public void run() {
 PrintWriter pr;
 try {
 pr = new PrintWriter(client.getOutputStream());
 pr.println("hey from server");
 pr.flush();
 } catch (IOException ex) {
 Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
 }

 while (true) {
 try {
 str = input.readLine();
 mes.add(str);
 System.out.println("client: " + str);
 str = "";        
 } catch (IOException ex) {
 System.err.println("we have a problem");
 }

 }
 }

 public int giv(int a) {
    
 // mes.remove(mes.size()-1);
 while(mes.size()==0){
 try {
 Thread.sleep(100);
 } catch (InterruptedException ex) {
 Logger.getLogger(Listener.class.getName()).log(Level.SEVERE, null, ex);
 }
 }
 mes.remove(mes.size()-1);
 return Integer.parseInt(str) ;
    
 }*/
