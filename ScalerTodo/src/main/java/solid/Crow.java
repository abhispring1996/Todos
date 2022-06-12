package solid;

public class Crow extends Bird implements Flyable{

    CrowSparrowFly fly;

    public Crow(CrowSparrowFly fly){
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
