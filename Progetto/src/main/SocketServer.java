/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.*;
import java.net.*;

  
/**
 *
 * @author matxd
 */
public class SocketServer {
    
    public void SocketServer(){
        
    }
    
    
    
    public static void main(String[] args) throws IOException{
        ServerSocket listener = new ServerSocket(9091);
        System.out.println("Server is running");
        
        try {
            while (true) {                
                Socket socket = listener.accept();
                ClientHandler client = new ClientHandler(socket);
                new Thread(client).start();
            }
        }finally{
            listener.close();
        }
        
    }
}
