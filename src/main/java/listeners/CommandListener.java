package listeners;

import commands.ErrorCommand;
import commands.HelpCommand;
import commands.JohnCommand;
import commands.ImageCommand;
import model.ICommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.BotHelper;

public class CommandListener extends ListenerAdapter {

    private static final Map<String, ICommand> commandMap = new HashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(CommandListener.class);

    static {
        commandMap.put("image", new ImageCommand());
        commandMap.put("help", new HelpCommand());
        commandMap.put("john", new JohnCommand());
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] argArray = event.getMessage().getContentRaw().split("\\s+");
        if (argArray.length == 0) return;
        if (!argArray[0].startsWith(BotHelper.BOT_PREFIX)) return;
        String command = argArray[1];
        logger.debug("Command retrieved is {}", command);
        List<String> args = new ArrayList<>(Arrays.asList(argArray));
        args.remove(0);
        commandMap.getOrDefault(command, new ErrorCommand()).run(event, args);
    }
}
