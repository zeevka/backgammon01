/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.Server;

import backgammon01.backgammon.board;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zeev7
 */
public class Server {

    public Server() {
        
        try {
            ss = new ServerSocket(4447);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4447.");
            System.exit(1);
        }
        
        doConect = new conectClients();
        doConect.start();
        start();
    }

    
    
    private ServerSocket ss;
    private Socket Socket;
    private validation doValidation;
    private conectClients doConect;

    public void start(){

        while(true){
        
            try {
                Socket = ss.accept();
                System.out.println("client connected");
                doValidation = new validation(Socket, doConect);
                doValidation.start();
            } catch (IOException ex) {
                System.err.println("Accept failed.");
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            Socket = null;
            doValidation = null;
        
        }


    }
}
        /*        while (true) {
        try {
        Socket1 = ss.accept();
        System.out.println("client 1 connected");
        } catch (IOException e) {
        System.err.println("Accept failed.");
        System.exit(1);
        }
        
        //    string a = Socket1
        
        try {
        Socket2 = ss.accept();
        System.out.println("client 1 connected");
        } catch (IOException e) {
        System.err.println("Accept failed.");
        System.exit(1);
        }
        
        ServerHandler= new Handler(Socket1, Socket2);
        ServerHandler.start();
        }*/
