package com.baoerye.tank;

import java.awt.*;
import java.util.Random;

public class Wall {
    private int x;
    private int y;
    private TankFrame tFrame;
    public Rectangle rect=new Rectangle();
    private static final int WALL_WIDTH=ResourceMgr.getResoureMgrInstance().wall.getWidth();
    private static final int WALL_HEIGHT=ResourceMgr.getResoureMgrInstance().wall.getHeight();
    public Wall(int px,int py,TankFrame tf){
        this.x=px;
        this.y=py;
        this.tFrame=tf;
        rect.x=x;
        rect.y=y;
        rect.width=WALL_WIDTH;
        rect.height=WALL_HEIGHT;
    }

    public void paint(Graphics g){
        g.drawImage(ResourceMgr.getResoureMgrInstance().wall,x,y,null);

    }

}
