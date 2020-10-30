package com.baoerye.abstractFactory;

import com.baoerye.tank.*;

import java.awt.*;

public class GoodTank extends Tank{
    public GoodTank(int px, int py, Group gp, TankFrame tf){

        super(px, py, gp, tf);
    }
    @Override
    public void paint(Graphics g) {
        oldX=x;
        oldY=y;

        move();
        //移动后变化矩阵位置
        rect.x=x;
        rect.y=y;

        switch (direction){
            case UP:
                g.drawImage(ResourceMgr.getResoureMgrInstance().goodTankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.getResoureMgrInstance().goodTankD,x,y,null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.getResoureMgrInstance().goodTankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.getResoureMgrInstance().goodTankR,x,y,null);
                break;
        }
    }
}