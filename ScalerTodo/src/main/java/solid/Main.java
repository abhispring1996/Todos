package solid;

public class Main {

    public static void main(String[] args) {

        CrowSparrowFly crowFly = new CrowSparrowFly();
        Flyable crow = new Crow(crowFly);
        crow.fly();
    }
}
