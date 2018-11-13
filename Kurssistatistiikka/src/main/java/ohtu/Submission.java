package ohtu;

public class Submission {

    private String course;
    private int week;
    private int hours;
    private int[] exercises;

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int[] getExercises() {
        return exercises;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("aikaa kului " + hours);
        builder.append(" tehdyt tehtävät: " + tehdytTehtavat());
        return builder.toString();
    }

    private String tehdytTehtavat() {
        String mj = "";
        boolean eka = true;
        for (Integer tehtava : exercises) {
            if (!eka) {
                mj += ", ";
            }
            mj += tehtava;
            eka = false;

        }
        return mj;
    }

}
