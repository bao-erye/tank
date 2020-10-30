package com.baoerye.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ResourceMgr {
    private static  ResourceMgr resourceMgr =null;
    public  BufferedImage goodTankL,goodTankR,goodTankU,goodTankD;
    public  BufferedImage badTankL,badTankR,badTankU,badTankD;
    public  BufferedImage badBulletL,badBulletR,badBulletU,badBulletD;
    public  BufferedImage goodBulletL,goodBulletR,goodBulletU,goodBulletD;
    public  BufferedImage[] explodes=new BufferedImage[11];
    public  BufferedImage wall;
    private ResourceMgr(){
        try {
            goodTankL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/goodTankL.png"));
            goodTankR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/goodTankR.png"));
            goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/goodTankU.png"));
            goodTankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/goodTankD.png"));

            badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankU.gif"));
            badTankD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankD.gif"));
            badTankL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankL.gif"));
            badTankR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/tankR.gif"));

            badBulletL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletL.gif"));
            badBulletR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletR.gif"));
            badBulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.gif"));
            badBulletD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletD.gif"));

            goodBulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodBulletU.png"));
            goodBulletD = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodBulletD.png"));
            goodBulletL = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodBulletL.png"));
            goodBulletR = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodBulletR.png"));

            for (int i=0;i<11;i++){
                explodes[i]=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/"+i+".gif"));
            }
            wall=ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/square2.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static ResourceMgr getResoureMgrInstance(){
        if (resourceMgr == null){
            resourceMgr = new ResourceMgr();
        }
        return resourceMgr;
    }
}
