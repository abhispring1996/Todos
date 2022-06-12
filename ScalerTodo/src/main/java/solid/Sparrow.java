package solid;

public class Sparrow extends Bird implements Flyable{

    CrowSparrowFly fly;

    public Sparrow(CrowSparrowFly fly){
        this.fly=fly;
    }

    @Override
    public void eat() {

    }

    @Override
    public void makeSound() {

    }

    @Override
    public void fly() {
        fly.speedFly();
    }
}
