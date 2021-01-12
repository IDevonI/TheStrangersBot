import commands.EmbedCreator;
import commands.MessageSender;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) {
        JDA jda;
        String token=System.getenv("TOKEN");
        try {
            jda = JDABuilder.createDefault(token).enableIntents(GatewayIntent.GUILD_PRESENCES).build();
            jda.addEventListener(new EmbedCreator());
            jda.addEventListener(new MessageSender());
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
