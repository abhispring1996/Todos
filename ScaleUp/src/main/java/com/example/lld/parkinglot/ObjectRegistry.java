package com.example.lld.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ObjectRegistry {

    private static Map<String,Object> objectRegistry = new HashMap<>();

    public static void put(String name,Object object){
       objectRegistry.put(name,object);
    }

    public static Object get(String name){
        return objectRegistry.get(name);
    }
}
