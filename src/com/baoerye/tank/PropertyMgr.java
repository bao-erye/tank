package com.baoerye.tank;

import jdk.nashorn.internal.objects.annotations.Property;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
    private static final PropertyMgr propertyMgr =new PropertyMgr();
    private Properties props=new Properties();
    private PropertyMgr(){

        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static PropertyMgr getPropertyMgrInstance(){
        return propertyMgr;
    }

    public Object get(String key){
        if (props!=null){
            return props.get(key);
        }
        else return null;
    }

}
