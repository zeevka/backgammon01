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

/**
 *
 * @author zeev7
 */
public class Server {

    public Server() {
        start();
    }

    
    
    private ServerSocket ss;
    private Socket Socket1, Socket2;
    private int token1, token2;
    private Handler ServerHandler;

    public void start(){
    
        try {
            ss = new ServerSocket(4447);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4447.");
            System.exit(1);
        }


        while (true) {
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
        }
    }
}
