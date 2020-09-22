package com.baoerye.tank;

import java.awt.*;

public class Bullet {
    private int x;
    private int y;
    private Direction direction;
    private TankFrame tf;
    public Rectangle rect=new Rectangle();
    private boolean isLiving=true;
    private Group group;
    private static final int BULLET_WIDTH=ResourceMgr.bulletD.getWidth();
    private static final int BULLET_HEIGHT=ResourceMgr.bulletD.getHeight();
    private static final int BULLET_SPEED=5;

    public Bullet(int px,int py,Direction dir,Group gp,TankFrame tankFrame){
        this.x=px;
        this.y=py;
        this.direction=dir;
        this.tf=tankFrame;
        this.group=gp;
        rect.x=x;
        rect.y=y;
        rect.width=BULLET_WIDTH;
        rect.height=BULLET_HEIGHT;
    }


    public void paint(Graphics g){
        //更新rect
        rect.x=x;
        rect.y=y;

        switch (direction){
            case UP:
                g.drawImage(ResourceMgr.bulletU,x,y,null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD,x,y,null);
                break;
            case LEFT:
                g.drawImage(ResourceMgr.bulletL,x,y,null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR,x,y,null);
                break;
        }
        collideWithBridge();//子弹与边界碰撞检测
        move();
        if (!isLiving) tf.arrayBullets.remove(this);
    }

    public void move(){

        switch (direction){
            case UP:
                y -=BULLET_SPEED;
                break;
            case DOWN:
                y +=BULLET_SPEED;
                break;
            case LEFT:
                x -=BULLET_SPEED;
                break;
            case RIGHT:
                x +=BULLET_SPEED;
                break;
        }
    }
    //子弹与敌人碰撞检测
    public void collideWithTank(Tank tank) {
        if (this.isLiving&&tank.getIsmoving()&&this.rect.intersects(tank.rect)){
//            tf.arrayBullets.remove(this);//不能直接移除，调用该方法的人还在遍历list
//            tf.arrayBadTanks.remove(tank);
            this.die();
            tank.die();
            tf.arrayExplodes.add(new Explode(x,y,tf));
            new Thread(new Runnable() {
                @Override
                public void run() {
                    new Audio("audio/explode.wav").play();
                }
            }).start();
        }

    }
    //子弹边界碰撞检测
    public void collideWithBridge(){
        if (x<BULLET_HEIGHT/2) this.die();
        if (x>(800-BULLET_WIDTH/2)) this.die();
        if (y<BULLET_HEIGHT/2) this.die();
        if (y>(800-BULLET_WIDTH/2)) this.die();
    }
    //子弹与墙碰撞检测
    public void collideWithWall(Wall wall) {
        if (this.isLiving&&this.rect.intersects(wall.rect)){
            this.die();
        }
    }

    private void die() {
        isLiving=false;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public Direction getDirection() {
        return direction;
    }
    public Group getGroup() {return group;}
    public void setGroup(Group group) {this.group = group;}


}
