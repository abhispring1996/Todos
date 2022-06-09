package oops.inheritance;

public class User {

    private String name;
    private String password;
    private String email;

    // TODO -> private members of Parent class also contribute to memory in child class

    public User (){
        System.out.println("Here in the parent class");
    }

    public void updateEmail(String email){
        this.email=email;
    }

    public void updatePassword(String password){
        this.password=password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
