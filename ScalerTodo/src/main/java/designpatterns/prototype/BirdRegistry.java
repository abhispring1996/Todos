package designpatterns.prototype;

import java.util.HashMap;
import java.util.Map;

// TODO -> to store templates of objects corresponding to some type which can help us to make copy and then add additive things
public class BirdRegistry {

    public static Map<String,Bird> birdMap = new HashMap<>();

    public void registerBird(String name,Bird bird){
        birdMap.put(name,bird);
    }

    public Bird getCopy(String name){
        return birdMap.get(name).copy();
    }

}
