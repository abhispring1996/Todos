package designpatterns.decorator;

public class Customer {

    public static void main(String[] args) {

        // Orange Cone with two Vanilla Scoops,one choco scoop and one vanilla Scop

        IceCreamCone iceCreamCone = new VanillaScoop(
                new ChocoScoop(
                        new VanillaScoop(
                                new VanillaScoop(
                new OrangeCone()))));

        System.out.println(iceCreamCone.getConstituents().contains(""));

        // TODO -> can make predefined bars like ChocoBar and KesarPista and can make Registry like we did in Prototype
        // TODO -> Pizza Ordering ,Coffee Ordering
    }
}
