package designpatterns.prototype;

public class Sparrow extends Bird{

    private String legSize;

    public void setLegSize(String legSize) {
        this.legSize = legSize;
    }

    public Sparrow(){

    }
    // copy Constructor
    public Sparrow(Sparrow old){
        super(old);
        this.legSize=old.legSize;
    }

    @Override
    public Sparrow copy(){
        return new Sparrow(this);
    }
}
