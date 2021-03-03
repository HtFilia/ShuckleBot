package actions;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Random;

public final class QwantPhotoRetriever {

    private QwantPhotoRetriever() {}

    private static final String templateURIQwant = "https://api.qwant.com/api/search/images?t=images&uiv=4&count=200&q=";

    public static String retrievePhoto(String query) {
        String uriQwant = correspondingURI(query);
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uriQwant))
                .GET()
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:86.0) Gecko/20100101 Firefox/86.0")
                .build();
        HttpResponse<String> response;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject jsonObject = new JSONObject(response.body());
            Random rand = new Random();
            JSONArray items = jsonObject.getJSONObject("data")
                    .getJSONObject("result")
                    .getJSONArray("items");
            return items.getJSONObject(rand.nextInt(items.length()))
                    .get("media").toString();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
            return "Don't fuckle with Shuckle.";
        }
    }

    private static String correspondingURI(String query) {
        return templateURIQwant.concat(query.replaceAll(" ", "%20"));
    }

}
