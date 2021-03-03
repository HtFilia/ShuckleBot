import listeners.John;
import listeners.QwantPhotoRetriever;
import lombok.extern.slf4j.Slf4j;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

@Slf4j
public class ShuckleBot {

    public static void main(String[] args) throws LoginException {
        if (args.length != 1) {
            System.exit(1);
        }
        String token = args[0];
        JDABuilder.createLight(token, GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES)
                .setActivity(Activity.playing("Help : pls shuckle help"))
                .addEventListeners(new QwantPhotoRetriever())
                .addEventListeners(new John())
                .build();
    }

}
