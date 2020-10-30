package com.baoerye.tank;

import com.baoerye.abstractFactory.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

public class TankFrame extends Frame {

    private static final int GAME_WIDTH=800,GAME_HEIGHT=800;
    // 获取坦克工厂
    AbstratFactory tankFactory = FactoryProducer.getFactory("tank");
    Tank myTank = tankFactory.getTank("goodTank",400,700,this,1);
    public ArrayList<Bullet> arrayBullets=new ArrayList<Bullet>();
    public ArrayList<Tank> arrayBadTanks=new ArrayList<Tank>();
    public ArrayList<Explode> arrayExplodes=new ArrayList<Explode>();
    public ArrayList<Wall> arrayWalls=new ArrayList<Wall>();

    public TankFrame(){
        setSize(GAME_WIDTH,GAME_HEIGHT);
        setVisible(true);
        setTitle("坦克大战");
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //添加监听
        addKeyListener(new MyListener());
        //添加敌方坦克
        initBadTanks();
        //添加墙
        initWalls();
        //添加背景音乐
        new Thread(new Runnable() {
            @Override
            public void run() {
                new Audio("audio/war1.wav").loop();
            }
        }).start();
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g){
        g.setColor(Color.WHITE);
        g.drawString("子弹数量:"+arrayBullets.size(), 10, 60);
        g.drawString("敌方坦克数量:"+arrayBadTanks.size(), 10, 80);
        g.drawString("爆炸数量:"+arrayExplodes.size(), 10, 100);

        //画我方坦克
        myTank.paint(g);
        //画墙
        for (int i=0;i<arrayWalls.size();i++){
            arrayWalls.get(i).paint(g);
        }
        //画子弹
        for (int i=0;i<arrayBullets.size();i++){
            arrayBullets.get(i).paint(g);
        }
        //画敌人
        for (int i=0;i<arrayBadTanks.size();i++){
            arrayBadTanks.get(i).paint(g);
        }
        //主坦克与墙碰撞检测
        for (Wall wall:arrayWalls){
            myTank.collideWithWall(wall);
        }
        //每颗子弹与敌人、墙碰撞检测
        for (int i=0;i<arrayBullets.size();i++){
            // 每颗子弹与墙碰撞检测
            for (Wall wall:arrayWalls) {
                arrayBullets.get(i).collideWithWall(wall);
            }
            // 每颗子弹与坦克碰撞检测
            if (arrayBullets.get(i).getGroup()==Group.GOOD){
                for (Tank tank:arrayBadTanks) {
                    arrayBullets.get(i).collideWithTank(tank);
                }
            }else {
                arrayBullets.get(i).collideWithTank(myTank);
            }
        }
        //每辆坦克和墙、其他坦克碰撞检测
        for(int i=0;i<arrayBadTanks.size();i++){
            for (Wall wall:arrayWalls){
                arrayBadTanks.get(i).collideWithWall(wall);
            }
            for (int j=i+1;j<arrayBadTanks.size();j++){
                arrayBadTanks.get(i).collideWithTank(arrayBadTanks.get(j));
            }
        }

        //画爆炸
        for (int i=0;i<arrayExplodes.size();i++){
            try {
                arrayExplodes.get(i).paint(g);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class MyListener extends KeyAdapter {
        boolean du=false;
        boolean dd=false;
        boolean dl=false;
        boolean dr=false;

        @Override
        public void keyPressed(KeyEvent e){
            int key=e.getKeyCode();
            switch (key){
                case KeyEvent.VK_UP:
                    du=true;
                    setTankDir();
                    break;
                case KeyEvent.VK_DOWN:
                    dd=true;
                    setTankDir();
                    break;
                case KeyEvent.VK_LEFT:
                    dl=true;
                    setTankDir();
                    break;
                case KeyEvent.VK_RIGHT:
                    dr=true;
                    setTankDir();
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire(new DefaultFireStrategy());
                    break;
                case KeyEvent.VK_SHIFT:
                    myTank.fire(new FourDirFireStrategy());
                    break;
            }
        }

        @Override
        public void keyReleased(KeyEvent e){
            int key=e.getKeyCode();
            switch (key){
                case KeyEvent.VK_UP:
                    du=false;
                    setTankDir();
                    break;
                case KeyEvent.VK_DOWN:
                    dd=false;
                    setTankDir();
                    break;
                case KeyEvent.VK_LEFT:
                    dl=false;
                    setTankDir();
                    break;
                case KeyEvent.VK_RIGHT:
                    dr=false;
                    setTankDir();
                    break;
            }
        }

        private void setTankDir() {
            if(!du && !dd && !dl && !dr) {
                myTank.setIsmoving(false);
            }else {
                myTank.setIsmoving(true);
                if (du)  myTank.setDirection(Direction.UP);
                if (dd)  myTank.setDirection(Direction.DOWN);
                if (dl)  myTank.setDirection(Direction.LEFT);
                if (dr)  myTank.setDirection(Direction.RIGHT);
            }

        }
    }
    private void initBadTanks() {
        int initBadTank=Integer.valueOf((String)PropertyMgr.getPropertyMgrInstance().get("badTankCount"));
        for (int i=0;i<initBadTank;i++){
            Tank badTank=tankFactory.getTank("badTank",150,150,this,i);
            arrayBadTanks.add(badTank);
        }
    }
    //初始化墙
    private void initWalls() {
        for (int i=0;i<15;i++){
            arrayWalls.add(new Wall(100+16*i,100,this));
            arrayWalls.add(new Wall(100+16*i,116,this));
            arrayWalls.add(new Wall(400+16*i,100,this));
            arrayWalls.add(new Wall(400+16*i,116,this));
            arrayWalls.add(new Wall(100,350+16*i,this));
            arrayWalls.add(new Wall(116,350+16*i,this));
            arrayWalls.add(new Wall(550,200+16*i,this));
            arrayWalls.add(new Wall(566,200+16*i,this));
        }
        for (int i=0;i<9;i++){
            arrayWalls.add(new Wall(100+16*i,250,this));
            arrayWalls.add(new Wall(100+16*i,266,this));
            arrayWalls.add(new Wall(300+16*i,250,this));
            arrayWalls.add(new Wall(300+16*i,266,this));
            arrayWalls.add(new Wall(400,275+16*i,this));
            arrayWalls.add(new Wall(416,275+16*i,this));
            arrayWalls.add(new Wall(400,450+16*i,this));
            arrayWalls.add(new Wall(416,450+16*i,this));
            arrayWalls.add(new Wall(420+16*i,520,this));
            arrayWalls.add(new Wall(420+16*i,536,this));
            arrayWalls.add(new Wall(620+16*i,520,this));
            arrayWalls.add(new Wall(620+16*i,536,this));
        }

    }

}
