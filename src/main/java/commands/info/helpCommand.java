package commands.info;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import utils.settings;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class helpCommand extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (!e.getAuthor().isBot()) {
            String[] args = e.getMessage().getContentRaw().split(" ");
            if (args[0].equalsIgnoreCase(settings.prefix + "help")) {
                e.getAuthor().openPrivateChannel().queue(channel -> {
                    DMlog(e.getMember(), channel);
                });
            }
        }
    }
    public static void DMlog(Member sent, PrivateChannel channel) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Commands:");
        builder.addField("Information:","", false);
        builder.setColor(Color.decode("#e84118"));
        builder.addField(settings.prefix + "online","Displays online info, discluding the bot.", false);
        builder.addField(settings.prefix + "ping","Pong!" , false);
        builder.addField(settings.prefix + "help", "Sends this.", false);
        builder.addField("Communication:","", false);
        builder.addField(settings.prefix + "suggest", "Opens a channel for suggestions.", false);
        builder.addField("Administration/Moderation:","", false);
        builder.addField(settings.prefix + "announce","Sends a message in announcements.", false);
        builder.addField(settings.prefix + "ban","Ban's someone.", false);
        builder.addField(settings.prefix + "kick","Kick's someone", false);
        builder.addField(settings.prefix + "mute","Mute's someone", false);
        builder.addField(settings.prefix + "warn","Warn's someone.", false);
        builder.addField(settings.prefix + "unban","Unban's someone", false);
        builder.addField(settings.prefix + "clear","Clear's a chat for a certain amount of messages.", false);


        channel.sendMessage(builder.build()).queue();
    }
}
