package com.baoerye.tank;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket ss=new ServerSocket(30000);
        while (true){
            Socket s=ss.accept();
            PrintStream ps=new PrintStream(s.getOutputStream());
            ps.println("您好，您收到了服务器的新年祝福");
            ps.close();
            s.close();
        }
    }
}
