package utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class BotHelper {

    private static final Logger logger = LoggerFactory.getLogger(BotHelper.class);

    public static final String BOT_PREFIX = "!shuckle";
    private static final String TITLE_EMBED_MESSAGE = "Don't fuckle with Shuckle";
    private static final String FOOTER_EMBED_MESSAGE = "This image is stored in the local database";

    private BotHelper() { }

    public static void sendImageEmbeded(MessageReceivedEvent event, String imageURL) {
        MessageChannel channel = event.getChannel();
        channel.sendMessage(new EmbedBuilder().setImage(imageURL)
                                                .setTitle(TITLE_EMBED_MESSAGE)
                                                .setFooter(FOOTER_EMBED_MESSAGE)
                                                .build())
                .queue();
        logger.info("Sent message with image on {}", channel.getName());
    }

    public static void sendErrorEmbeded(MessageReceivedEvent event) {
        //TODO
    }
}
