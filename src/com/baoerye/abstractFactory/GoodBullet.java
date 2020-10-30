package com.baoerye.abstractFactory;

import com.baoerye.tank.*;

import java.awt.*;

public class GoodBullet extends Bullet{

    public GoodBullet(int px,int py,Direction dir,Group gp,TankFrame tankFrame){
        super(px,py,dir,gp,tankFrame);
        BULLET_HEIGHT = ResourceMgr.getResoureMgrInstance().goodBulletD.getHeight();
        BULLET_WIDTH = ResourceMgr.getResoureMgrInstance().goodBulletD.getWidth();
    }

    public void paint(Graphics g){
        //更新rect
        rect.x=x;
        rect.y=y;

        switch (direction){
            case UP:
                g.drawImage(ResourceMgr.getResoureMgrInstance().goodBulletU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.getResoureMgrInstance().goodBulletU,x,y,null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.getResoureMgrInstance().goodBulletL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.getResoureMgrInstance().goodBulletR,x,y,null);
                break;
        }
        collideWithBridge();//子弹与边界碰撞检测
        move();
        if (!isLiving) tf.arrayBullets.remove(this);
    }
}
