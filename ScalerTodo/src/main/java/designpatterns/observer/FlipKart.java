package designpatterns.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FlipKart {

    public static Map<Events, List<Subscriber>> subscriberMap = new HashMap<>();

    public static void registerSubscriber(Subscriber subscriber,Events events){

        if(!subscriberMap.containsKey(events)){
            subscriberMap.put(events,new ArrayList<>());
        }

        subscriberMap.get(events).add(subscriber);
    }

    public void notify(Events events,Order order){

        for(Subscriber subscriber:subscriberMap.get(events)) {
            subscriber.listen(order);
        }
    }

    public void placeOrder(Order order){
            notify(Events.ORDERS_PLACED,order);
    }

}
