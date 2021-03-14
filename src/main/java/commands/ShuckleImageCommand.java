package commands;

import model.ICommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.BotHelper;
import utils.DatabaseHelper;

import java.util.List;

public class ShuckleImageCommand implements ICommand {

    private static final Logger logger = LoggerFactory.getLogger(ShuckleImageCommand.class);

    private static final String RANDOM_IMAGE_QUERY = "SELECT url FROM Image ORDER BY RAND() LIMIT 1";

    @Override
    public void run(MessageReceivedEvent event, List<String> args) {
        String shuckleImageURL = DatabaseHelper.getImageFromDB(RANDOM_IMAGE_QUERY);
        BotHelper.sendImageEmbeded(event, shuckleImageURL);
    }
}
