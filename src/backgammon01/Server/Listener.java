/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zeev7
 */
public class Listener extends Thread {

    public Listener(Socket client) throws IOException {
        this.client = client;
        input = new BufferedReader(new InputStreamReader(client.getInputStream()));
        mes = new ArrayList<String>();
        
        
    }
    
    private Socket client;
    private BufferedReader input;
    private String str;
    private ArrayList<String> mes;


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

    }
}
