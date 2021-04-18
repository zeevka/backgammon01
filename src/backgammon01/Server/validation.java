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

        System.out.println("new validation");
        vladPlyer = new player(client);
        try {
            out = new ObjectOutputStream(client.getOutputStream());
            in = new ObjectInputStream(client.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(validation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Socket client;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private Message hello;
    private Message tmp;
    private player vladPlyer;
    public conectClients next;

    @Override
    public void run() {
        try {
            Dovalidation();
        } catch (IOException ex) {
            Logger.getLogger(validation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(validation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void Dovalidation() throws IOException, ClassNotFoundException {

        hello = new Message(1, null, 333);
        out.writeObject(hello);
        System.out.println("welcome send");

        for (int i = 0; i < 3; i++) {

            tmp = (Message) in.readObject();
            

            if (tmp.getId() == 2) {
                mesLogin tmpMesLogin = (mesLogin) tmp.getObj();
                System.out.println("client: "+tmpMesLogin.getId()+"  "+ tmpMesLogin.getPassword());
                logIn logIn = new logIn(tmpMesLogin.getId(), tmpMesLogin.getPassword(), vladPlyer);
                logIn.run();
            } else if (tmp.getId() == 3) {
                mesLogin tmpMesLogin = (mesLogin) tmp.getObj();
                newPlyer logUp = new newPlyer(tmpMesLogin.getId(), tmpMesLogin.getPassword(), vladPlyer);
                logUp.run();
            } else {

                hello = new Message(-1, null, 0);
                out.writeObject(hello);

            }
            //todo: wait the thread wil finish
            if (vladPlyer.getStatus() == playerStatus.yes) {
                System.out.println("player conect");

                vladPlyer.setStatus(playerStatus.wait);
                next.add(vladPlyer);
                hello = new Message(5, null, 0);
                
                out.writeObject(hello);
                Thread.currentThread().stop();
            }else {

                System.out.println("player f");
                hello = new Message(-1, null, 0);
                out.writeObject(hello);

            }
            //logIn =(logIn) tmp.getObj();

        }

        vladPlyer.sendString(-1, "sorry", 0);

    }

}
