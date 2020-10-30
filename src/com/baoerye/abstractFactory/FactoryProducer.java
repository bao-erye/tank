package com.baoerye.abstractFactory;

public class FactoryProducer {
    public static AbstratFactory getFactory(String type){
        if (type == "tank") {
            return new TankFactory();
        }else if (type == "bullet"){
            return new BulletFactory();
        }else return null;
    }
}
