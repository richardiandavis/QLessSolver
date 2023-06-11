import java.io.IOException;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;
import java.net.URI;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
//logic for checking existence of words implemented
public class qLessSolver {
    public static boolean isWord(String word) {
        try {
            String encodedWord = URLEncoder.encode(word, StandardCharsets.UTF_8);
            HttpRequest getRequest = HttpRequest.newBuilder()
                    .uri(new URI("https://api.dictionaryapi.dev/api/v2/entries/en/" + encodedWord))
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newHttpClient().send(getRequest, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                return true;
            } else {
                return false;
            }
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return true;
    }
    public static void main(String[] args) {
        if (isWord("test")) {
            System.out.println("working");
        }
    }
}
