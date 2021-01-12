package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.util.Objects;

public class EmbedCreator extends ListenerAdapter {

    public static final int TITLE_MAX_LENGTH = 1000;
    public static final int VALUE_MAX_LENGTH = 100000;
    public static final int TEXT_MAX_LENGTH = 100000;
    public static final int EMBED_MAX_LENGTH_BOT = 100000;
    public static final int EMBED_MAX_LENGTH_CLIENT = 100000;

    public void onGuildMessageReceived(GuildMessageReceivedEvent event) {

        if(!event.getAuthor().isBot())
        {
            if(event.getMessage().getContentRaw().charAt(0)=='!') {
                String[] command = event.getMessage().getContentRaw().split("%");
                boolean hasPermission = false;
                for(Role r: Objects.requireNonNull(event.getMember()).getRoles())
                {
                    if(r.getId().equals("796909952865140767")||r.getId().equals("796909947588968448")||
                            r.getId().equals("796910085463998496")||r.getId().equals("796911701873917962"))
                    {
                        hasPermission=true;
                        break;
                    }
                }
                if (command[0].equals("!embed")) {
                    if(hasPermission) {
                        int err = 0;
                        String n = "";
                        String z = "";
                        EmbedBuilder eb = new EmbedBuilder();
                        if (command.length == 1) {
                            eb.setTitle("!embed%[c;%a;%t;%T=;%n;%z;%u;%U;%s;%o;]");
                            eb.addField("", "c-kolor(ang), np. c;red\n" +
                                    "a-autor, np. a;Jan\n" +
                                    "t-tytuł, np. t;Regulamin\n" +
                                    "T-tytul z url, np. T;Tytuł=adres url\n" +
                                    "n-nagłówek, np. n;Punkt pierwszy\n" +
                                    "z-zawartosc tekstowa\n" +
                                    "u-adres url miniatury\n" +
                                    "U-adres url obrazka\n" +
                                    "s-stopka, np. s;Made by Jan\n" +
                                    "o-opis", false);
                            eb.addField("Jeśli jakaś opcja Cię nie interesuje ,po prostu ją pomiń", "Dostępne kolory:\n" +
                                    "yellow\n" +
                                    "orange\n" +
                                    "red\n" +
                                    "blue\n" +
                                    "black\n" +
                                    "white\n" +
                                    "pink\n" +
                                    "green\n" +
                                    "magenta\n" +
                                    "cyan\n" +
                                    "gray\n" +
                                    "dark_gray\n", false);
                            eb.addField("Edycja czcionki:", "dwie_gwiazdki **tekst** dwie_gwiazdki - pogrubienie\n" +
                                    "gwiazdka *teskt* gwiazdka - kursywa\n" +
                                    "dwie_podłogi __tekst__ dwie_podłogi - podkreślenie\n" +
                                    "Powyższe można łączyć np.:\n" +
                                    "trzy_gwiazdki ***tekst*** trzy_gwiazdki - pogrubiona kursywa\n" +
                                    "dwie_podłogi_gwiazdka __*tekst*__ gwiazdka_dwie_podłogi - podkreślona kursywa", false);
                            eb.setFooter("Created by Devon");
                        } else {
                            for (int i = 1; i < command.length && err == 0; i++) {
                                String[] subCommand = command[i].split(";");
                                switch (subCommand[0]) {
                                    case "c":
                                        switch (subCommand[1]) {
                                            case "yellow":
                                                eb.setColor(Color.YELLOW);
                                                break;
                                            case "orange":
                                                eb.setColor(Color.ORANGE);
                                                break;
                                            case "red":
                                                eb.setColor(Color.RED);
                                                break;
                                            case "blue":
                                                eb.setColor(Color.BLUE);
                                                break;
                                            case "black":
                                                eb.setColor(Color.BLACK);
                                                break;
                                            case "white":
                                                eb.setColor(Color.WHITE);
                                                break;
                                            case "pink":
                                                eb.setColor(Color.PINK);
                                                break;
                                            case "green":
                                                eb.setColor(Color.GREEN);
                                                break;
                                            case "magenta":
                                                eb.setColor(Color.MAGENTA);
                                                break;
                                            case "cyan":
                                                eb.setColor(Color.CYAN);
                                                break;
                                            case "gray":
                                                eb.setColor(Color.GRAY);
                                                break;
                                            case "dark_gray":
                                                eb.setColor(Color.DARK_GRAY);
                                                break;
                                            default:
                                                event.getChannel().sendMessage("Error: Nieprawidłowy kolor!").queue();
                                                err++;
                                                break;
                                        }
                                        break;
                                    case "a":
                                        eb.setAuthor(subCommand[1]);
                                        break;
                                    case "t":
                                        eb.setTitle(subCommand[1]);
                                        break;
                                    case "u":
                                        eb.setThumbnail(subCommand[1]);
                                        break;
                                    case "U":
                                        eb.setImage(subCommand[1]);
                                        break;
                                    case "s":
                                        eb.setFooter(subCommand[1]);
                                        break;
                                    case "o":
                                        eb.setDescription(subCommand[1]);
                                        break;
                                    case "n":
                                        n = subCommand[1];
                                        break;
                                    case "z":
                                        z = subCommand[1];
                                        break;
                                    case "T":
                                        String[] subT = subCommand[1].split("=");
                                        eb.setTitle(subT[0], subT[1]);
                                        break;
                                }
                            }
                        }
                        if (z.length() > 0 || n.length() > 0) {
                            eb.addField(n, z, false);
                        }
                        if (err == 0) {
                            Objects.requireNonNull(event.getGuild().getTextChannelById(event.getChannel().getId())).sendMessage(eb.build()).queue();
                        }
                    }else {
                        event.getChannel().sendMessage("Nie masz uprawnień do tej akcji!").queue();
                    }
                }
            }
        }
    }
}

