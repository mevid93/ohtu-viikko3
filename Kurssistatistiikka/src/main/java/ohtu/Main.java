package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/courses/students/" + studentNr + "/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();
        
        System.out.println("json-muotoinen data:");
        System.out.println(bodyText);
        System.out.println("");

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);

        System.out.println("opiskelijanumero " + studentNr + "\n");
        
        int totalHours = 0;
        int exercisesDone = 0;
        for (Submission submission : subs) {
            System.out.println(submission);
            totalHours += submission.getHours();
            exercisesDone += submission.getExercises().length;
        }
        
        System.out.println("\nyhteensä: " + exercisesDone + " tehtävää "
                + totalHours + " tuntia");
    }

}