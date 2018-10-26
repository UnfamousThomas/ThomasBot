package commands.Administration;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import utils.settings;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class announceCommand extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        String[] args = e.getMessage().getContentRaw().split(" ");
        if (args[0].equalsIgnoreCase(settings.prefix + "announce")) {
            if (!e.getAuthor().isBot() && e.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                if (args.length < 2) {
                    sendErrorMessage(e.getTextChannel(), e.getMember());
                    e.getMessage().delete().queueAfter(15, TimeUnit.SECONDS);
                } else if (args.length >= 2) {
                    Member m = e.getMember();
                    String reason = "";
                    for (int i = 1; i < args.length; i++) {
                        reason += args[i] + " ";
                    }
                    e.getMessage().delete().queue();
                    sendAnnouncement(reason, e.getMember(), e.getGuild().getTextChannelById("503935759246557205"));
                }
            }
        }
    }

    public void sendErrorMessage(TextChannel channel, Member member) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Invalid Usage!:");
        builder.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        builder.setColor(Color.decode("#e84118"));
        builder.setDescription("[] - Required, {} - Optional");
        builder.addField("Proper usage: .announce [MESSAGE]", "", false);
        channel.sendMessage(builder.build()).complete().delete().queueAfter(30, TimeUnit.SECONDS);
    }

    public void sendAnnouncement(String msg, Member m, TextChannel c) {
        EmbedBuilder b = new EmbedBuilder();
        b.setTitle("ANNOUNCEMENT");
        b.setAuthor(m.getUser().getName(), m.getUser().getAvatarUrl(), m.getUser().getAvatarUrl());
        b.addField("Message:", msg, false);
        b.setColor(Color.decode("#f1c40f"));
        c.sendMessage(b.build()).complete().delete().queueAfter(10, TimeUnit.DAYS);
        c.sendMessage("@everyone").queue();

    }
}
