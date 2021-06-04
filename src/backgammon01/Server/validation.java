/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.Server;

import backgammon01.sql.logIn;
import backgammon01.sql.newPlyer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import backgammon01.Server.player.playerStatus;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import TurenLibrey.messages.Message;
import TurenLibrey.messages.mesLogin;
import java.net.Socket;

/**
 *
 * @author zeev7
 */
public class validation extends Thread {

    public validation(Socket client, conectClients next) {
        this.client = client;
        this.next = next;
        vladPlyer = new player(client);

        /*       try {
        
        } catch (IOException ex) {
        Logger.getLogger(validation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
        Logger.getLogger(validation.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        
        
    }

    private Socket client;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Message hello;
    private Message tmp;
    private player vladPlyer;
    public conectClients next;
    public sender send;

    @Override
    public void run() {
        try {
            in = new ObjectInputStream(client.getInputStream());
            Message gar = (Message)in.readObject();
            //send = new sender(client, new Message(1, null, 333));
            out = new ObjectOutputStream(client.getOutputStream());
            vladPlyer.setOut(out);
            vladPlyer.sendObject(1, null, 333);
            Dovalidation();
        } catch (IOException ex) {
            Logger.getLogger(validation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(validation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            System.err.println("theard catch");
            Logger.getLogger(validation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Dovalidation() throws IOException, ClassNotFoundException, InterruptedException {

        /*        hello = new Message(1, null, 333);
        send = new sender(client, hello);
        send.start();*/
      //  send = null;
        System.out.println("send");//////////////////
        for (int i = 0; i < 3; i++) {

            //in = new ObjectInputStream(client.getInputStream());
            tmp = null;
            while (tmp == null) {
               tmp = (Message) in.readObject();
               Thread.sleep(25);
            }
            
            

            if (tmp.getId() == 2) {
                mesLogin tmpMesLogin = (mesLogin) tmp.getObj();
                logIn logIn = new logIn(tmpMesLogin.getId(), tmpMesLogin.getPassword(), vladPlyer);
                logIn.run();
            } else if (tmp.getId() == 3) {
                mesLogin tmpMesLogin = (mesLogin) tmp.getObj();
                newPlyer logUp = new newPlyer(tmpMesLogin.getId(), tmpMesLogin.getPassword(), vladPlyer);
                logUp.run();
            } else {

                /*    hello = new Message(-1, null, 0);
                out.writeObject(hello);
                out.flush();*/
                 vladPlyer.sendObject(-1, null, 0);
                

            }
            //todo: wait the thread wil finish
            if (vladPlyer.getStatus() == playerStatus.yes) {
                System.out.println("player conect");

                vladPlyer.setStatus(playerStatus.wait);
                vladPlyer.setlis(in);
                next.add(vladPlyer);
                hello = new Message(5, null, 0);
                
               // send = new sender(client, hello);
                //send.start();
               // send = null;
                vladPlyer.sendObject(5, null, 0);
                 
                Thread.currentThread().stop();
            }else {

                System.out.println("player f");
                hello = new Message(-1, null, 0);
                vladPlyer.sendObject(-1, null, 0);
               // send = new sender(client, hello);
               // send.start();
                //send = null;

            }

        }
        vladPlyer.sendObject(-1, null, 0);
    }

}
