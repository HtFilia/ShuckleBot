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
    private static final String FOOTER_ERROR_MESSAGE = "I am sorry for failing you";
    private static final String ERROR_IMAGE_URL = "https://miro.medium.com/max/978/1*pUEZd8z__1p-7ICIO1NZFA.png";

    private BotHelper() { }

    public static void sendImageEmbedded(MessageReceivedEvent event, String imageURL) {
        MessageChannel channel = event.getChannel();
        channel.sendMessage(new EmbedBuilder().setImage(imageURL)
                                                .setTitle(TITLE_EMBED_MESSAGE)
                                                .setFooter(FOOTER_EMBED_MESSAGE)
                                                .build())
                .queue();
        logger.info("Sent image url {} on {} to {}", imageURL, channel.getName(), event.getAuthor().getName());
    }

    public static void sendErrorEmbedded(MessageReceivedEvent event, String errorMessage) {
        MessageChannel channel = event.getChannel();
        channel.sendMessage(new EmbedBuilder().setImage(ERROR_IMAGE_URL)
                                            .setTitle(errorMessage)
                                            .setFooter(FOOTER_ERROR_MESSAGE)
                                            .build())
                .queue();
        logger.info("Sent error message on {} to {}", channel.getName(), event.getAuthor().getName());
    }
}
