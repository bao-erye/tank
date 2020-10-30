package com.baoerye.tank;

import com.baoerye.abstractFactory.Bullet;
import com.baoerye.abstractFactory.Tank;

public class FourDirFireStrategy  implements FireStrategy{

    @Override
    public void fire(Tank t) {
        int bx = t.x + t.TANK_WIDTH/2 - Bullet.BULLET_WIDTH/2;
        int by = t.y + t.TANK_HEIGHT/2 - Bullet.BULLET_HEIGHT/2;

        Direction dirs[] = Direction.values();
        for (Direction dir : dirs) {
            bulletFactory.getBullet("goodBullet",bx,by,dir, t.tFrame);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Audio("audio/tank_fire.wav").play();
            }
        }).start();

    }
}
