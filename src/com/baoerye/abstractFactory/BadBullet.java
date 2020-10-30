package com.baoerye.abstractFactory;

import com.baoerye.tank.*;

import java.awt.*;

public class BadBullet extends Bullet {

    public BadBullet(int px,int py,Direction dir,Group gp,TankFrame tankFrame){

        super(px,py,dir,gp,tankFrame);
        BULLET_HEIGHT = ResourceMgr.getResoureMgrInstance().badBulletD.getHeight();
        BULLET_WIDTH = ResourceMgr.getResoureMgrInstance().badBulletD.getWidth();
    }

    public void paint(Graphics g){
        //更新rect
        rect.x=x;
        rect.y=y;

        switch (direction){
            case UP:
                g.drawImage(ResourceMgr.getResoureMgrInstance().badBulletU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.getResoureMgrInstance().badBulletD,x,y,null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.getResoureMgrInstance().badBulletL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.getResoureMgrInstance().badBulletR,x,y,null);
                break;
        }
        collideWithBridge();//子弹与边界碰撞检测
        move();
        if (!isLiving) tf.arrayBullets.remove(this);
    }

}
