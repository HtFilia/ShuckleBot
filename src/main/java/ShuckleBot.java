import actions.QwantPhotoRetriever;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

@Slf4j
public class ShuckleBot extends ListenerAdapter {

    public static void main(String[] args) throws LoginException {
        if (args.length != 1) {
            System.exit(1);
        }
        String token = args[0];
        JDABuilder.createLight(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .addEventListeners(new ShuckleBot())
                .setActivity(Activity.playing("Help : !s help"))
                .build();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        String toSend;
        MessageChannel channel = event.getChannel();
        if (msg.getContentRaw().equals("!s image")) {
            toSend = QwantPhotoRetriever.retrievePhoto("shuckle");
            channel.sendMessage(toSend)
                    .queue();
        } else if (msg.getContentRaw().equals("pls worst person")) {
            channel.sendMessage("WORST IS JOHN OFC")
                    .queue();
        }
    }

}
