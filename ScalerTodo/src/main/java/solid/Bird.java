package solid;

// TODO -> using abstract becoz it has physical existence
public abstract class Bird {

    String color;
    String type;
    long weight;
    long beakSize;

    // TODO -> Putting it in a interface owing to Open/closed principle
    //public abstract void fly();

    // TODO -> making it abstract so that required behaviour is implemented by the concerned class(Single Responsibility)
    public abstract void eat();

    public abstract void makeSound();


    // TODO -> Penguin and ostrich can't fly so no need of fly in them
    // TODO -> Crow and sparrow fly in same way so need to prevent duplication in fly methods -> Dependency inversion
}



