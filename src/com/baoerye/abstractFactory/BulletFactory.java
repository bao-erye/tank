package com.baoerye.abstractFactory;

import com.baoerye.tank.Direction;
import com.baoerye.tank.Group;
import com.baoerye.tank.TankFrame;

public class BulletFactory extends AbstratFactory{

    @Override
    public Tank getTank(String tankType, int x, int y, TankFrame tf, int i) {
        return null;
    }

    @Override
    public Bullet getBullet(String bulletType, int x, int y, Direction dir, TankFrame tf) {
        if (bulletType == "goodBullet"){
            return new GoodBullet(x,y, dir, Group.GOOD,tf);
        }else if (bulletType == "badBullet"){
            return new BadBullet(x,y, dir, Group.BAD,tf);
        }else return null;
    }
}
