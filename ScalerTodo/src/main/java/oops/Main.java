package oops;

import oops.inheritance.Instructor;
import oops.inheritance.Student;
import oops.inheritance.User;

import java.util.*;

public class Main {
    public static void main(String[] args) {

      // TODO -> If we are making an object using ref of parent we are bounded to use only methods of parent -> Polymorphism
      User user = new Instructor();

      // TODO -> Method Overriding -> At runtime we get to know about the actual object whereas at compile time we are only
        // aware of the ref object

      // TODO -> Diamond problem -> Class extending two classes can't identify whose parent method to call incase of same method
      // TODO -> Interfaces(blueprint of behaviour) -> help in case where need to multiple reusability for ex Mammals -> Herbivore -> Animals

        char [] c = new char[26];
        char [] d = new char[26];

        String s = "abhi";

        for(char ch:s.toCharArray()){
            c[ch-'a']++;
        }

        String f ="hbia";

        for(char ch:f.toCharArray()){
            d[ch-'a']++;
        }

        String res = new String(c);
        String ret = new String(d);
        System.out.println(ret.equals(res)+"String is "+ret);

        //Map<String, ArrayList<String>> map = new HashMap<>();
        //map.putIfAbsent("key",new ArrayList<>()).add("e");

        int i=0;
        checkPostPre(i++);

        System.out.println("After execution "+i);
    }

    public static void checkPostPre(int i){

        //if(i==6) return ;

        //System.out.println(i);

        //checkPostPre(i++);

        //System.out.println(i);

            int j=0;

            while(j<6){
                System.out.println(++j);
            }
    }



}