package com.baoerye.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.nio.Buffer;
import java.util.ArrayList;

public class GameOverFrame extends Frame {
    ArrayList<String> array=new ArrayList<String >();
    TextField text=new TextField();
    Button btn=new Button("send");
    public GameOverFrame(){
        setSize(800,800);
        setVisible(true);
        setTitle("坦克大战");
        array.add("对话情况：");
        text.setBounds(500,300,200,30);
        btn.setBounds(500,350,80,30);
        add(text);
        add(btn);


        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        startThread();
    }
    Image offScreenImage = null;

    public void startThread() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket=new Socket("127.0.0.1",30000);
                    BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    String line=br.readLine();
                    array.add(line);
                    br.close();
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(800, 800);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, 800, 800);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.WHITE);
        for (int i=0;i<array.size();i++){
            g.drawString(array.get(i),200,200+20*i);
        }

    }
}
