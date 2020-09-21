package com.baoerye.tank;

import jdk.nashorn.internal.objects.annotations.Property;

import java.io.IOException;
import java.util.Properties;

public class PropertyMgr {
    static Properties props=new Properties();
    static {
        try {
            props.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Object get(String key){
        if (props!=null){
            return props.get(key);
        }
        else return null;
    }

}
