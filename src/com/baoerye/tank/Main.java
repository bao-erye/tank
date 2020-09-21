package com.baoerye.tank;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {

        TankFrame tf=new TankFrame();
        while(true){
            Thread.sleep(25);
            tf.repaint();
        }

    }
}
