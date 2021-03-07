import listeners.John;
import listeners.QwantPhotoRetriever;
import listeners.ShuckleDatabaseRetriever;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.DatabaseUtil;

import javax.security.auth.login.LoginException;
import java.sql.SQLException;

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
        DatabaseUtil.setParameters(username, password);
        JDABuilder.createLight(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .setActivity(Activity.playing("Help : pls shuckle help"))
                .addEventListeners(new ShuckleDatabaseRetriever())
                .addEventListeners(new John())
                .build();
    }

}
