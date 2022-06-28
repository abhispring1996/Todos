package designpatterns.prototype;

public class Bird implements Copyable{

    private String name;
    private String color;
    private int weight;

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Bird(){

    }

    public Bird(Bird old){
        this.name=old.name;
        this.color=old.color;
        this.weight=old.weight;
    }

    @Override
    public Bird copy() {
        return new Bird(this);
    }
}
