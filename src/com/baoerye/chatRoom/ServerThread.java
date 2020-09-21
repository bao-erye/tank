package com.baoerye.chatRoom;

import java.io.*;
import java.net.Socket;

public class ServerThread implements Runnable{
    //定义当前线程处理的socket
    Socket ss=null;
    //当前线程所处理的socket所对应的输入流
    BufferedReader br=null;
    //socket对应的输出流
    PrintStream ps=null;
    public ServerThread(Socket s) throws IOException {
        this.ss=s;
        br=new BufferedReader(new InputStreamReader(s.getInputStream()));
        ps=new PrintStream(s.getOutputStream());
    }
    @Override
    public void run() {
        String content=null;
        //采用循环不断从socket中读取客户端发送过来的数据
        while((content=readFromClient())!=null){
            System.out.println("收到的内容："+content);
        }

    }
    private String readFromClient(){
        try {
            return br.readLine();
        } catch (IOException e) {
        }
        return null;
    }
}
