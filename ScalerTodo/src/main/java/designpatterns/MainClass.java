package designpatterns;

import designpatterns.prototype.Bird;
import designpatterns.prototype.Crow;
import designpatterns.prototype.Sparrow;

import java.util.*;

public class MainClass {

    public static void main(String[] args) {


        // Builder Pattern
        UserExams userExams = UserExams.getBuilder()
                .setEnglishMarks(100)
                .setScienceMarks(90)
                .setName("Abhishek")
                .setMathsMarks(80).build();
        System.out.println(userExams.toString());


        // Prototype Pattern -> creating copy of the object
        Bird bird = new Bird();
        bird.setColor("brown");
        bird.setWeight(34);
        bird.setName("Abhi");

        Crow crow = new Crow();
        crow.setSound("Kooo");
        crow.setWeight(20);

        Sparrow sparrow = new Sparrow();
        sparrow.setLegSize("20");
        sparrow.setColor("Blue");


        List<Bird> birdsList = List.of(bird,crow,sparrow);
        List<Bird> childrenBirds = new ArrayList<>();

        for(Bird parent : birdsList){
            childrenBirds.add(parent.copy());
        }
    }
}
