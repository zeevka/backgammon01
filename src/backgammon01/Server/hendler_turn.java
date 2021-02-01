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
public class hendler_turn {

    public hendler_turn(Socket s) throws IOException {
        this.s = s;
        this.in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        this.out  =new PrintWriter(s.getOutputStream());
    }
    
    
    private Socket s;
    private BufferedReader in;
    private PrintWriter out;
    
    public void send(String send){
    
        out.println(send);
        out.flush();
    }
    
    public int getInt() throws IOException{
    
        return in.read();
    }
    public String get() throws IOException{
    
        return in.readLine();
    }
    
    
}
