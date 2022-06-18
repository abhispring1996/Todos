package designpatterns;

public class MainClass {

    public static void main(String[] args) {
        UserExams userExams = UserExams.getBuilder()
                .setEnglishMarks(100)
                .setScienceMarks(90)
                .setName("Abhishek")
                .setMathsMarks(80).build();
        System.out.println(userExams.toString());
    }
}
