package model;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;

@FunctionalInterface
public interface ICommand {

    void run(MessageReceivedEvent event, List<String> args);
}
