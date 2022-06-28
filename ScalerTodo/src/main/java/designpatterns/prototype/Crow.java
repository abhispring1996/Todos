package designpatterns.prototype;

public class Crow extends Bird {

    private String sound="Kaw";

    public void setSound(String sound) {
        this.sound = sound;
    }

    public Crow(){

    }

    public Crow(Crow old){
        super(old);
        this.sound=old.sound;
    }

    @Override
    public Crow copy() {
        return new Crow(this);
    }
}
