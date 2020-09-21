package com.baoerye.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        //TankFrame tf=new TankFrame();
        GameOverFrame gameOverFrame=new GameOverFrame();
        while(true){
            Thread.sleep(25);
            //tf.repaint();
            gameOverFrame.repaint();
        }

    }
}
