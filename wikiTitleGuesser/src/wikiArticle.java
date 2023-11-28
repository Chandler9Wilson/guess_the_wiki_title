import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class wikiArticle {
    public String summaryExtract;
    public String normalizedTitle;

    private static String targetURL = "https://en.wikipedia.org/api/rest_v1/page/random/summary";

    private String rawApiJson;
    //DO NOT COMMIT should be pulled from file
    private String authToken = "";

    wikiArticle() {
        if (getRandomArticle()) {
            System.out.println("No errors getting article");
        }
    }

    private boolean getRandomArticle() {
        try {
            HttpRequest articleRequest = HttpRequest.newBuilder()
            .uri(new URI(targetURL))
            .header("Authorization", "Bearer " + authToken)
            .header("accept", "application/problem+json")
            .GET()
            .build();

            // Synchronously send http GET request (and build to accept redirects)
            HttpResponse<String> response = HttpClient.newBuilder()
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build()
            .send(articleRequest, BodyHandlers.ofString());

            if(response.statusCode() == 200 || response.statusCode() == 303) {
                //TODO convert json
                System.out.println(response.body());
            }
            else {
                System.out.println("Bad response from server: " + response.statusCode());
                return false;
            }

            return true;
        } catch (Exception e) {
            System.out.println("Failed to retrieve article with error: " + e);
            return false;
        }
    }
}
