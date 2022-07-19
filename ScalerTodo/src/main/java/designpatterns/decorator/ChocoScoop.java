package designpatterns.decorator;

public class ChocoScoop implements IceCreamCone{


    private IceCreamCone iceCreamCone;

    public ChocoScoop(IceCreamCone iceCreamCone){
        this.iceCreamCone=iceCreamCone;
    }


    @Override
    public long getCost() {
        return iceCreamCone.getCost()+35;
    }

    @Override
    public String getConstituents() {
        return iceCreamCone.getConstituents()+" "+"ChocoScoop";
    }
}
