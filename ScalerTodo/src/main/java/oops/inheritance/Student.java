package oops.inheritance;


public class Student {

    private String batch;
    private double psp;
    private Mentor mentor;

    public Student(){

    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public double getPsp() {
        return psp;
    }

    public void setPsp(double psp) {
        this.psp = psp;
    }

    public Mentor getMentor() {
        return mentor;
    }

    public void setMentor(Mentor mentor) {
        this.mentor = mentor;
    }


    // Copy Constructor
    // this is used to remove ambiguity
    public Student(Student other) {
        this.batch= other.batch;
    }

}
