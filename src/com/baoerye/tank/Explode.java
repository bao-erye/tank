package com.baoerye.tank;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Random;

public class Explode {

    private int x;
    private int y;
    private TankFrame tFrame;
    private int step=0;
    public Explode(int px,int py,TankFrame tf){
        this.x=px;
        this.y=py;
        this.tFrame=tf;
    }

    public void paint(Graphics g) throws IOException {
        g.drawImage(ResourceMgr.explodes[step++],x,y,null);
        if (step>9) tFrame.arrayExplodes.remove(this);

    }
}
