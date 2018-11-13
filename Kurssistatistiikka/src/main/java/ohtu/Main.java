package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {

        // määritellään tarkasteltavan opiskelijan opiskelijanumero
        String studentNr = "012345678";
        if (args.length > 0) {
            studentNr = args[0];
        }

        // haetaan opiskelijan tekemät palautukset
        Submission[] subs = haeSubmissionTiedot(studentNr);
        if (subs == null) {
            return; // tietojen haku epäonnistui
        }

        // haetaan kurssitiedot
        Course[] courses = haeKurssiTiedot();
        if (courses == null) {
            return; // tietojen haku epäonnistui
        }

        // tulostetaan hakujen tulokset käyttäjälle
        tulostaOpiskelijanKurssienTiedot(studentNr, subs, courses);
    }

    private static Submission[] haeSubmissionTiedot(String studentNr) {
        String url = "https://studies.cs.helsinki.fi/courses/students/" + studentNr + "/submissions";
        try {
            String bodyText = Request.Get(url).execute().returnContent().asString();

//            System.out.println("json-muotoinen data:");
//            System.out.println(bodyText);
//            System.out.println("");
            Gson mapper = new Gson();
            Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
            return subs;
        } catch (Exception e) {
            // ei onnistunut
        }
        return null;
    }

    private static Course[] haeKurssiTiedot() {
        String url = "https://studies.cs.helsinki.fi/courses/courseinfo";
        try {
            String bodyText = Request.Get(url).execute().returnContent().asString();

//            System.out.println("json-muotoinen data:");
//            System.out.println(bodyText);
//            System.out.println("");
            Gson mapper = new Gson();
            Course[] courses = mapper.fromJson(bodyText, Course[].class);
            return courses;
        } catch (Exception e) {
            // ei onnistunut
        }
        return null;
    }

    private static void tulostaOpiskelijanKurssienTiedot(String studentNr, Submission[] subs, Course[] courses) {
        System.out.println("opiskelijanumero " + studentNr);

        // poimitaan submissioneista opiskelijan kurssien nimet
        String[] coursenames = opiskelijanKurssienNimet(subs);

        // loopataan opiskelijan suorittamant kurssit läpi
        for (String cname : coursenames) {

            // haetaan kurssin tiedot
            Course course = etsiKurssiNimenPerusteella(cname, courses);
            if (course == null) {
                continue;
            }

            int totalHours = 0;
            int exercisesDone = 0;
            int totalExercises = course.numberOfExercises();

            System.out.println("\n" + course.toString() + " \n");

            // loopataan opiskelijan palautukset kurssiin liittyen läpi
            for (Submission submission : subs) {
                if (!submission.getCourse().equals(cname)) {
                    continue;   // ei kyseisen kurssin viikkopalautus
                }
                System.out.println("viikko " + submission.getWeek() + ":");
                System.out.print(" tehtyjä tehtäviä " + submission.getExercises().length);
                System.out.print("/" + course.getExercises()[submission.getWeek()] + " ");
                System.out.println(submission);
                totalHours += submission.getHours();
                exercisesDone += submission.getExercises().length;
            }
            System.out.println("\nyhteensä: " + exercisesDone + "/"
                    + totalExercises + " tehtävää " + totalHours + " tuntia");
        }

    }

    private static String[] opiskelijanKurssienNimet(Submission[] subs) {
        Set<String> names = new HashSet<>();
        for (Submission sub : subs) {
            names.add(sub.getCourse());
        }
        return names.toArray(new String[names.size()]);
    }

    private static Course etsiKurssiNimenPerusteella(String name, Course[] courses) {
        for (Course course : courses) {
            if (course.getName().equals(name)) {
                return course;
            }
        }
        return null;
    }

}
