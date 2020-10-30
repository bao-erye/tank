package com.baoerye.abstractFactory;

import com.baoerye.tank.Direction;
import com.baoerye.tank.Group;
import com.baoerye.tank.TankFrame;

public class TankFactory extends AbstratFactory{

    @Override
    public Tank getTank(String tankType, int x, int y, TankFrame tf, int i) {
        if (tankType == "goodTank"){
            return new GoodTank(x,y, Group.GOOD,tf);
        }else if (tankType == "badTank"){
            return new BadTank(x+i*120,y,Group.BAD,tf);
        }else return null;
    }

    @Override
    public Bullet getBullet(String bulletType, int x, int y, Direction dir, TankFrame tf) {
        return null;
    }
}
