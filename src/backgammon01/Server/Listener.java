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

/**
 *
 * @author zeev7
 */
public class Listener extends Thread{
    
private  Socket client;
private PrintWriter out;
private BufferedReader input;
private String str;

    public Listener(Socket client) throws IOException {
        this.client = client;
        input= new BufferedReader(new InputStreamReader(client.getInputStream()));
        out= new PrintWriter(client.getOutputStream());
    }
   
        @Override
    public void run() {
        while(true){
            try {
                str= input.readLine();
            } catch (IOException ex) {
                System.err.println("we have a ");
            }
            System.out.println("client: "+str );
            str="";
        }
    }    
}
