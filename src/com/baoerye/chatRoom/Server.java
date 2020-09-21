package com.baoerye.chatRoom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    //定义保存所有socket的arrayList
    public static ArrayList<Socket> arraySocket=new ArrayList<Socket>();
    public static void main(String[] args) throws IOException {

        ServerSocket ss=new ServerSocket(30000);
        while (true){
            //等待别人连接
            Socket socket=ss.accept();
            arraySocket.add(socket);
            //没连接一个连接，开启一个线程处理
            new Thread(new ServerThread(socket)).start();
            //获取对应的输出流
            PrintStream ps=new PrintStream(socket.getOutputStream());
            String line=null;
            //不断获取输入
            BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
            while ((line=br.readLine())!=null){
                ps.println(line);
            }
        }
    }
}
