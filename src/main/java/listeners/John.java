package listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public final class John extends ListenerAdapter {

    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        MessageChannel channel = event.getChannel();
        if (msg.getContentRaw().equals("pls worst person")) {
            String unkoImageURL = "https://emojipedia-us.s3.dualstack.us-west-1.amazonaws.com/thumbs/160/apple/271/pile-of-poo_1f4a9.png";
            MessageEmbed embed = new EmbedBuilder().setImage(unkoImageURL)
                    .setTitle("Award goes to John")
                    .setFooter("This guy sucks wow")
                    .build();
            channel.sendMessage(embed)
                    .queue();
        }
    }
}
