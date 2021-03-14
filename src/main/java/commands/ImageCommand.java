package commands;

import model.ICommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.BotHelper;
import utils.DatabaseHelper;

import java.util.List;

public class ImageCommand implements ICommand {

    private static final Logger logger = LoggerFactory.getLogger(ImageCommand.class);

    private static final String RANDOM_IMAGE_QUERY = "SELECT url FROM Image ORDER BY RAND() LIMIT 1";
    private static final String IMAGE_RETRIEVING_FAILED_MESSAGE = "Couldn't retrieve an image from the database";

    @Override
    public void run(MessageReceivedEvent event, List<String> args) {
        String shuckleImageURL = DatabaseHelper.getImageFromDB(RANDOM_IMAGE_QUERY);
        if (shuckleImageURL != null) {
            logger.info("Successfully retrieved url {} for {} on {}", shuckleImageURL, event.getAuthor().getName(), event.getChannel().getName());
            BotHelper.sendImageEmbedded(event, shuckleImageURL);
        } else {
            logger.error("Couldn't retrieve Shuckle image for {} on {}", event.getAuthor().getName(), event.getChannel().getName());
            BotHelper.sendErrorEmbedded(event, IMAGE_RETRIEVING_FAILED_MESSAGE);
        }
    }
}
