package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Objects;

public class MessageSender extends ListenerAdapter {

    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        new Thread( () -> {
            if(event.getMessage().getContentRaw().contains("!strangers sendmsg"))
            {
                String[] command = event.getMessage().getContentRaw().split(" ");
                if(command.length==4) {
                    try {
                        String[] msg = event.getMessage().getContentRaw().split(";");
                        Objects.requireNonNull(event.getGuild().getTextChannelById(event.getMessage().getMentionedChannels().get(0).getId())).sendMessage(msg[1]).queue();
                    }catch (Exception e)
                    {
                        event.getChannel().sendMessage("SendMSG : Coś poszło nie tak! Sprawdź czy prawidłowo oznaczyłeś kanał i nie zapomniałeś o ';' .").queue();
                    }
                } else if(command.length==2) {
                    EmbedBuilder eb = new EmbedBuilder();
                    eb.setTitle("SendMSG");
                    eb.setColor(Color.RED);
                    eb.addField("!strangers sendmsg @Kanał ;Wiadomość","",false);
                    eb.setFooter("Created by Devon");
                    event.getChannel().sendMessage(eb.build()).queue();
                } else {
                    event.getChannel().sendMessage("SendMSG : Coś za krótkie ...").queue();
                }
            }
        }).start();
    }
}
