package com.baoerye.tank;

import com.baoerye.abstractFactory.AbstratFactory;
import com.baoerye.abstractFactory.FactoryProducer;
import com.baoerye.abstractFactory.Tank;

public interface FireStrategy {
    void fire(Tank t);
    // 获取坦克工厂
    AbstratFactory bulletFactory = FactoryProducer.getFactory("bullet");
}
