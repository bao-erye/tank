package com.baoerye.chatRoom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientThread implements Runnable{
    //该线程负责处理的Socket;
    private Socket socket=null;
    //socket对应的输入流;
    BufferedReader br=null;
    public ClientThread(Socket ss) throws IOException {
        this.socket=ss;
        br=new BufferedReader(new InputStreamReader(ss.getInputStream()));
    }
    @Override
    public void run() {
        String content=null;
        //不断读取输入流内容，显示出来
        try {
            while ((content=br.readLine())!=null){
                System.out.println("收到的回复:"+content);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
