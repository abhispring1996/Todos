package designpatterns.decorator;

public class VanillaScoop implements IceCreamCone{

    private IceCreamCone iceCreamCone;

    public VanillaScoop(IceCreamCone iceCreamCone){
        if(null==iceCreamCone) throw new NullPointerException();
        this.iceCreamCone=iceCreamCone;
    }


    @Override
    public long getCost() {
        return iceCreamCone.getCost()+23;
    }

    @Override
    public String getConstituents() {
        return iceCreamCone.getConstituents()+" "+"Vanilla Scoop";
    }
}
