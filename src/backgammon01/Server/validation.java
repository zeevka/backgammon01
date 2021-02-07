/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.Server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import backgammon01.sql.logIn;
import backgammon01.sql.newPlyer;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import backgammon01.Server.player.playerStatus;

/**
 *
 * @author zeev7
 */
public class validation extends Thread {

    public validation(player client) {
        this.client = client;

        try {
            in = new InputStreamReader(client.getNet().getInputStream());
            bf = new BufferedReader(in);
            pr = new PrintWriter(client.getNet().getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(validation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private InputStreamReader in;// = new InputStreamReader(s.getInputStream());
    private BufferedReader bf;// = new BufferedReader(in);
    private PrintWriter pr;// =new PrintWriter(s.getOutputStream());
    private newPlyer logUp;
    private logIn logIn;
    private player client;
    private String user, password;

    @Override
    public void run() {
        pr.println("hello from the server");
        pr.flush();
        System.out.println("send");
        do {
            try {
                user = bf.readLine();
                password = bf.readLine();
            } catch (IOException ex) {
                Logger.getLogger(validation.class.getName()).log(Level.SEVERE, null, ex);
            }
            logIn = new logIn(user, password, client);

        } while (client.getStatus() == playerStatus.no);

        pr.println("welcom");
        pr.flush();

        System.out.println("finish");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(validation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
