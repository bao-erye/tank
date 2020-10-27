package com.baoerye.tank;

import com.sun.xml.internal.bind.v2.model.core.BuiltinLeafInfo;

import java.awt.*;
import java.util.Random;

public class Tank {
    public int x;
    public int y;
    private int oldX;
    private int oldY;
    public TankFrame tFrame;
    private boolean ismoving=false;
    private boolean isLiving=true;
    public Direction direction=Direction.UP;
    private Direction oldDirection;
    public Group group;
    public Rectangle rect=new Rectangle();
    private Random random=new Random();
    public static final int TANK_WIDTH=ResourceMgr.goodTankU.getWidth();
    public static final int TANK_HEIGHT=ResourceMgr.goodTankU.getHeight();
    private static final int TANK_SPEED=3;
    public Tank(int px,int py,Group gp,TankFrame tf){
        this.x=px;
        this.y=py;
        this.group=gp;
        this.tFrame=tf;
        rect.x=x;
        rect.y=y;
        rect.width=TANK_WIDTH;
        rect.height=TANK_HEIGHT;
    }

    public void paint(Graphics g){

        oldX=x;
        oldY=y;
        if (group==Group.GOOD){
            g.setColor(Color.blue);
            g.drawRect(x,y,TANK_WIDTH,TANK_HEIGHT);
        }

        move();
        //移动后变化矩阵位置
        rect.x=x;
        rect.y=y;

        switch (direction){
            case UP:
                g.drawImage(ResourceMgr.goodTankU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.goodTankD,x,y,null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.goodTankL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.goodTankR,x,y,null);
                break;
        }
    }
    public void move(){
        //敌人改变方向
        if (group==Group.BAD&&random.nextInt(100)>95){
            ismoving=true;
            direction=Direction.values()[random.nextInt(4)];
        }
        if (group==Group.BAD&&random.nextInt(100)>95){
            fire(new DefaultFireStrategy());
        }

        if(!ismoving) return;

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
    private void collideDetect() {
        if (x<0) x=0;
        if (x>(800-TANK_HEIGHT)) x=800-TANK_HEIGHT;
        if (y<40) y=40;
        if (y>(800-TANK_HEIGHT)) y=800-TANK_HEIGHT;
    }
    //坦克和墙碰撞检测
    public void collideWithWall(Wall wall){
        if (this.isLiving&&this.rect.intersects(wall.rect)){
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
