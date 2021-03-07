package listeners;

import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public final class ShuckleDatabaseRetriever extends ListenerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(ShuckleDatabaseRetriever.class);

    private final String queryGetter = "SELECT url FROM Image " +
                   "ORDER BY RAND() " +
                   "LIMIT 1";

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message msg = event.getMessage();
        String toSendURL = null;
        MessageChannel channel = event.getChannel();
        if (msg.getContentRaw().equals("pls image shuckle")) {
            try (Connection conn = DatabaseUtil.connectToDB()) {
                logger.info("Connected to the database");
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(queryGetter);
                if (rs.next()) {
                    toSendURL = rs.getString("url");
                    logger.info(String.format("Received %s from database", toSendURL));
                }
                rs.close();
                stmt.close();
                channel.sendMessage(new EmbedBuilder().setImage(toSendURL)
                                            .setTitle("Don't fuckle with Shuckle.")
                                            .setFooter("This image was pre-approved by the community.")
                                            .build())
                        .queue();
                logger.info(String.format("Sent message with image to %s", channel));
            } catch (SQLException throwable) {
                logger.error("Exception occurred while trying to recover image from database: " + throwable.getMessage());
                toSendURL = "Couldn't load an image from the database.";
                channel.sendMessage(new MessageBuilder().append(toSendURL).build()).queue();
            }

        }
    }
}
