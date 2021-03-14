package app;

import listeners.CommandListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DatabaseHelper;

import javax.security.auth.login.LoginException;

public class ShuckleBot {

    private static final Logger logger = LoggerFactory.getLogger(ShuckleBot.class);

    public static void main(String[] args) throws LoginException {
        if (args.length != 3) {
            logger.error("This bot requires 3 arguments");
            System.exit(1);
        }
        logger.info("Loading parameters");
        String token = args[0];
        String username = args[1];
        String password = args[2];
        DatabaseHelper.setParameters(username, password);
        JDABuilder.createLight(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .setActivity(Activity.playing("Help : !shuckle help"))
                .addEventListeners(new CommandListener())
                .build();
    }

}
