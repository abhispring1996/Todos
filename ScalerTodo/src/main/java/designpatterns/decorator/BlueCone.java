package designpatterns.decorator;

public class BlueCone implements IceCreamCone{


    // TODO -> can make a constructor here which takes IceCreamCone incase we can two iceCreamCone

    @Override
    public long getCost() {
        return 5;
    }

    @Override
    public String getConstituents() {
        return "Blue Cone";
    }
}
