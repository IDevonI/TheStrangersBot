import events.Creator;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) {
        JDA jda;
        //String token=System.getenv("TOKEN");
        try {
            jda = JDABuilder.createDefault("Nzk3MTc5MTAxNTYyNTM1OTQ2.X_is5A.lt2AMPrWGZWGPXEyenbMqMHaVfQ").enableIntents(GatewayIntent.GUILD_PRESENCES).build();
            jda.addEventListener(new Creator());
        } catch (LoginException e) {
            e.printStackTrace();
        }
    }
}
