package commands;

import model.ICommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import utils.BotHelper;

import java.util.List;

public class ErrorCommand implements ICommand {

    private static final String ERROR_COMMAND_MESSAGE = "This command doesn't exist";

    @Override
    public void run(MessageReceivedEvent event, List<String> args) {
        BotHelper.sendErrorEmbedded(event, ERROR_COMMAND_MESSAGE);
    }
}
