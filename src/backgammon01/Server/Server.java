/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backgammon01.Server;

import backgammon01.board;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author zeev7
 */
public class Server {

    
    
    private ServerSocket ss;
    private Socket Socket1, Socket2;

    public void start(){
    
        try {
            ss = new ServerSocket(4447);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 4444.");
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
            try {
                Socket2 = ss.accept();
                System.out.println("client 1 connected");
            } catch (IOException e) {
                System.err.println("Accept failed.");
                System.exit(1);
            }
            
            
        }
    }
}
