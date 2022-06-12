package oops;

import oops.inheritance.Instructor;
import oops.inheritance.Student;
import oops.inheritance.User;

public class Main {
    public static void main(String[] args) {

      // TODO -> If we are making an object using ref of parent we are bounded to use only methods of parent -> Polymorphism
      User user = new Instructor();

      // TODO -> Method Overriding -> At runtime we get to know about the actual object whereas at compile time we are only
        // aware of the ref object

      // TODO -> Diamond problem -> Class extending two classes can't identify whose parent method to call incase of same method
      // TODO -> Interfaces(blueprint of behaviour) -> help in case where need to multiple reusability for ex Mammals -> Herbivore -> Animals

    }
}