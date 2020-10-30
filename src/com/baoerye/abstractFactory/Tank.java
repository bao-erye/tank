package com.baoerye.abstractFactory;

import com.baoerye.tank.*;

import java.awt.*;
import java.util.Random;

public abstract class Tank {
    public int x;
    public int y;
    public int oldX;
    public int oldY;
    public TankFrame tFrame;
    public boolean ismoving=false;
    public boolean isLiving=true;
    public Direction direction=Direction.UP;
    public Direction oldDirection;
    public Group group;
    public static int TANK_WIDTH = ResourceMgr.getResoureMgrInstance().goodTankD.getWidth();;
    public static int TANK_HEIGHT = ResourceMgr.getResoureMgrInstance().goodTankD.getHeight();
    public static final int TANK_SPEED=3;
    public Rectangle rect=new Rectangle();
    private Random random=new Random();

    public Tank(int px, int py, Group gp, TankFrame tf){
        this.x=px;
        this.y=py;
        this.group=gp;
        this.tFrame=tf;
        rect.x=px;
        rect.y=py;

        rect.width=TANK_WIDTH;
        rect.height=TANK_HEIGHT;
    }

    public abstract void paint(Graphics g);

    public void move(){
        //敌人改变方向
        if (group==Group.BAD&&random.nextInt(100)>95){
            ismoving=true;
            direction=Direction.values()[random.nextInt(4)];
        }
        if (group==Group.BAD&&random.nextInt(100)>95){
            fire(new DefaultFireStrategy());
        }



        if (!isLiving){
            if (this.group==Group.BAD){
                tFrame.arrayBadTanks.remove(this);
            }
            else {
                //主坦克死了后,退出游戏
                tFrame.setVisible(false);
                System.exit(0);
            }
        }
        if(!ismoving) return;
        //边界碰撞检测
        collideDetect();
        switch (direction){
            case UP:
                y -= TANK_SPEED;
                break;
            case DOWN:
                y += TANK_SPEED;
                break;
            case LEFT:
                x -= TANK_SPEED;
                break;
            case RIGHT:
                x += TANK_SPEED;
                break;
        }


    }

    //坦克边界碰撞检测
    public void collideDetect() {
        if (x<0) x=0;
        if (x>(800-TANK_HEIGHT)) x=800-TANK_HEIGHT;
        if (y<40) y=40;
        if (y>(800-TANK_HEIGHT)) y=800-TANK_HEIGHT;
    }

    //坦克和墙碰撞检测
    public void collideWithWall(Wall wall){
        if (this.rect.intersects(wall.rect)){
            this.x=this.oldX;
            this.y=this.oldY;

        }

    }

    //坦克和坦克碰撞检测
    public void collideWithTank(Tank tank){
        if (this.rect.intersects(tank.rect)){
            this.x=this.oldX;
            this.y=this.oldY;
        }
    }

    //发射子弹
    public void fire(FireStrategy fs) {
        fs.fire(this);

    }
    public void die() {
        isLiving=false;
    }
    //坦克
    public void setDirection(Direction direction) {this.direction = direction; }
    public Direction getDirection() {return direction; }
    public boolean getIsmoving() { return ismoving; }
    public void setIsmoving(boolean ismoving) {this.ismoving = ismoving; }
    public int getOldX() { return oldX; }
    public void setOldX(int oldX) { this.oldX = oldX; }
    public int getOldY() {return oldY; }
    public void setOldY(int oldY) {this.oldY = oldY; }
}
