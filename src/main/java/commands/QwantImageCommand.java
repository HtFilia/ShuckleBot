package commands;

import model.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Random;
import java.util.StringJoiner;

@Deprecated
public class QwantImageCommand implements ICommand {

    private static final Logger logger = LoggerFactory.getLogger(QwantImageCommand.class);

    private static final String TEMPLATE_URI_QWANT = "https://api.qwant.com/api/search/images?t=images&uiv=4&count=200&q=";
    private static final String TITLE_EMBED_MESSAGE = "Don't fuckle with Shuckle";
    private static final String FOOTER_EMBED_MESSAGE = "This image was received using Qwant";
    private static final String ERROR_IMAGE_URI = "https://e7.pngegg.com/pngimages/557/438/png-clipart-error-message-icon-warning-icons-trademark-computer.png";
    private static final Random RAND = new Random();

    private HttpResponse<String> responseFromQwant(List<String> query) throws IOException, InterruptedException {
        String uriQwant = correspondingURI(query);
        HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uriQwant))
                .GET()
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:86.0) Gecko/20100101 Firefox/86.0")
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

    private String correspondingURI(List<String> query) {
        StringJoiner sj = new StringJoiner("%20");
        query.forEach(sj::add);
        return TEMPLATE_URI_QWANT.concat(sj.toString());
    }

    private String randomQwantImage(List<String> query) throws IOException, InterruptedException {
        HttpResponse<String> response = responseFromQwant(query);
        JSONObject jsonObject = new JSONObject(response.body());
        JSONArray items = jsonObject.getJSONObject("data")
                .getJSONObject("result")
                .getJSONArray("items");
        return items.getJSONObject(RAND.nextInt(items.length()))
                .get("media").toString();
    }

    @Override
    public void run(MessageReceivedEvent event, List<String> args) {
        boolean caughtException = false;
        MessageChannel channel = event.getChannel();
        String pictureURL = "";
        try {
            pictureURL = randomQwantImage(args);
        } catch (InterruptedException e) {
            logger.error("Caught an InterruptedException while trying to recover picture. {}", e.getMessage());
            caughtException = true;
        } catch (IOException e) {
            logger.error("Caught an IOException while trying to recover picture. {}", e.getMessage());
            caughtException = true;
        } finally {
            if (caughtException) {

                sendErrorMessage(channel);
            } else {
                sendCorrectMessage(pictureURL, channel);
            }
        }

    }

    private void sendCorrectMessage(String pictureURL, MessageChannel channel) {
        MessageEmbed embed = new EmbedBuilder()
                .setImage(pictureURL)
                .setTitle(TITLE_EMBED_MESSAGE)
                .setFooter(FOOTER_EMBED_MESSAGE)
                .build();
        channel.sendMessage(embed)
                .queue();
    }

    private void sendErrorMessage(MessageChannel channel) {
        MessageEmbed embed = new EmbedBuilder()
                .setImage(ERROR_IMAGE_URI)
                .setTitle(TITLE_EMBED_MESSAGE)
                .setFooter(FOOTER_EMBED_MESSAGE)
                .build();
        channel.sendMessage(embed)
                .queue();
    }
}
