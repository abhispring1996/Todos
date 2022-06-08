package oops.inheritance;

public class Instructor extends User{

    private String assignedBatch;
    private String description;

    public Instructor(){

    }

    public Instructor(String description){
        this.description=description;
    }

    public String getAssignedBatch() {
        return assignedBatch;
    }

    public void setAssignedBatch(String assignedBatch) {
        this.assignedBatch = assignedBatch;
    }
}
