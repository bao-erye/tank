package com.baoerye.tank;

import com.baoerye.abstractFactory.Bullet;
import com.baoerye.abstractFactory.Tank;

public class DefaultFireStrategy implements FireStrategy{

    @Override
    public void fire(Tank t) {
        int bx = t.x + t.TANK_WIDTH/2 - Bullet.BULLET_WIDTH/2;
        int by = t.y + t.TANK_HEIGHT/2 - Bullet.BULLET_HEIGHT/2;

        if (t.group == Group.GOOD){
            bulletFactory.getBullet("goodBullet",bx,by, t.direction, t.tFrame);
        }else if (t.group == Group.BAD){
            bulletFactory.getBullet("badBullet",bx,by, t.direction, t.tFrame);
        }

        if(t.group == Group.GOOD){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    new Audio("audio/tank_fire.wav").play();
                }
            }).start();
        }
    }
}
