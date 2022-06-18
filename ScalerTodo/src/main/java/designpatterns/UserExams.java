package designpatterns;

// TODO -> use builder when there is ambiguity b/w diff attributes passed in constructor or class is immutable since there will be no setters
// TODO -> or we require validation before object creation writing everything in constructor is against SRP;

public class UserExams {

    private int englishMarks;
    private int mathsMarks;
    private int scienceMarks;
    private String name;

    private UserExams(){
    }

    public static UserExamsBuilder getBuilder(){
        return new UserExamsBuilder();
    }


    public static class UserExamsBuilder{

        private int englishMarks;
        private int mathsMarks;
        private int scienceMarks;
        private String name;

        public UserExamsBuilder setEnglishMarks(int englishMarks) {
            this.englishMarks = englishMarks;
            return this;
        }

        public UserExamsBuilder setMathsMarks(int mathsMarks) {
            this.mathsMarks = mathsMarks;
            return this;
        }

        public UserExamsBuilder setScienceMarks(int scienceMarks) {
            this.scienceMarks = scienceMarks;
            return this;
        }

        public UserExamsBuilder setName(String name) {
            this.name = name;
            return this;
        }


        public UserExams build(){

            // TODO -> can do validation also here

            UserExams userExams = new UserExams();
            userExams.englishMarks=englishMarks;
            userExams.mathsMarks=mathsMarks;
            userExams.scienceMarks=scienceMarks;
            userExams.name=name;
            return userExams;
        }
    }

    @Override
    public String toString() {
        return "UserExams{" +
                "englishMarks=" + englishMarks +
                ", mathsMarks=" + mathsMarks +
                ", scienceMarks=" + scienceMarks +
                ", name='" + name + '\'' +
                '}';
    }
}
