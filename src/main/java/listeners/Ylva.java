package listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public final class Ylva extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        MessageChannel channel = event.getChannel();
        if (msg.getContentRaw().equals("WHO IS THE BEST MOM")) {
            String bestGirl = "https://cdn.discordapp.com/attachments/793826005717352459/819872159622365204/20210301_154700.jpg";
            MessageEmbed embed = new EmbedBuilder().setImage(bestGirl)
                    .setTitle("Ever heard the story of Ylva the Great?")
                    .setFooter("Mario is a lucky man")
                    .build();
            channel.sendMessage(embed)
                    .queue();
        }
    }
}
