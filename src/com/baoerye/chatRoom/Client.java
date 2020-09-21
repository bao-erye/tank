package com.baoerye.chatRoom;

import com.baoerye.chatRoom.ClientThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;


public class Client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",30000);
        //客户端启动线程不断接收来自服务器的数据
        new Thread(new ClientThread(socket)).start();
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
