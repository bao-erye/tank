package com.baoerye.abstractFactory;

import com.baoerye.tank.Direction;
import com.baoerye.tank.TankFrame;

public abstract class AbstratFactory {
    public abstract Tank getTank(String tankType, int x, int y, TankFrame tf, int i);
    public abstract Bullet getBullet(String bulletType, int x, int y,Direction dir,TankFrame tf);
}
