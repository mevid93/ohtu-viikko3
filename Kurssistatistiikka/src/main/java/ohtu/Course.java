
package ohtu;


public class Course {
    
    private String name;
    private String fullName;
    private String term;
    private int year;
    private int[] exercises;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int[] getExercises() {
        return exercises;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    @Override
    public String toString() {
        return fullName + " " + term + " " + year;
    }
    
    public int numberOfExercises() {
        int sum = 0;
        for(Integer week : exercises) {
            sum += week;
        }
        return sum;
    }
    
    
}
