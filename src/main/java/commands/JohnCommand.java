package commands;

import model.ICommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import utils.BotHelper;

import java.util.List;

public final class JohnCommand implements ICommand {

    private static final String UNKO_IMAGE_URL = "https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/160/apple/271/pile-of-poo_1f4a9.png";

    @Override
    public void run(MessageReceivedEvent event, List<String> args) {
        BotHelper.sendImageEmbedded(event, UNKO_IMAGE_URL);
    }
}
