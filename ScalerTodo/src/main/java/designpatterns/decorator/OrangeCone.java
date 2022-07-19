package designpatterns.decorator;

public class OrangeCone implements  IceCreamCone{
    @Override
    public long getCost() {
        return 10;
    }

    @Override
    public String getConstituents() {
        return "Orange Cone";
    }
}
