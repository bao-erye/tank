package com.baoerye.tank;

public class FourDirFireStrategy  implements FireStrategy{

    @Override
    public void fire(Tank t) {
        int bx = t.x + Tank.TANK_WIDTH/2 - Bullet.BULLET_WIDTH/2;
        int by = t.y + Tank.TANK_HEIGHT/2 - Bullet.BULLET_HEIGHT/2;

        Direction dirs[] = Direction.values();
        for (Direction dir : dirs) {
            new Bullet(bx, by, dir, t.group, t.tFrame);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Audio("audio/tank_fire.wav").play();
            }
        }).start();

    }
}
