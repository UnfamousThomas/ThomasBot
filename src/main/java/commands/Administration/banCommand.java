package commands.Administration;

import utils.settings;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class banCommand extends ListenerAdapter {
@Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
    String args[] = e.getMessage().getContentRaw().split(" ");

    if (e.getMember().hasPermission(Permission.ADMINISTRATOR)) {
        if (args[0].equalsIgnoreCase(settings.prefix + "ban")) {
            if (!e.getAuthor().isBot()) {
                if (args.length >=  3) {
                    String reason = "";
                    for (int i = 2; i < args.length; i++) {
                        reason += args[i] + " ";
                    } Member target = e.getMessage().getMentionedMembers().get(0);
                    e.getGuild().getController().ban(target.getUser(), 7, reason).queue();
                    e.getMessage().delete().queue();
                    log(target, e.getMember(), reason, e.getGuild().getTextChannelById("503937433856114709"));
                    String finalReason = reason;
                    target.getUser().openPrivateChannel().queue(channel -> {
                        DMlog(target, finalReason, channel);
                    });
                } else {
                    sendErrorMessage(e.getChannel(), e.getMember());
                }
            }
        }
    } else {
        e.getChannel().sendMessage("Invalid permissions.");
    }
}
    public void sendErrorMessage(TextChannel channel, Member member) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Invalid Usage!:");
        builder.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        builder.setColor(Color.decode("#e84118"));
        builder.setDescription("[] - Required, {} - Optional");
        builder.addField("Proper usage: .ban [@USER] [reason]", "", false);
        channel.sendMessage(builder.build()).complete().delete().queueAfter(30, TimeUnit.SECONDS);
    }
    public void log(Member banner, Member banned, String reason, TextChannel channel) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/d/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Ban report");
        builder.setColor(Color.decode("#e84118"));
        builder.addField("Ban User", banned.getAsMention(), false);
        builder.addField("Banner/Moderator", banner.getAsMention(), false);
        builder.addField("Reason", reason, false);
        builder.addField("Days of messages deleted:", "7", false);
        builder.addField("Date", sdf.format(date), false);
        builder.addField("Time", stf.format(date), false);
        channel.sendMessage(builder.build()).queue();

    }
    public void DMlog(Member banned, String reason, PrivateChannel channel) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/d/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("You have been Banned. Here is some info about your ban:");
        builder.setColor(Color.decode("#e84118"));
        builder.addField("Banned User", banned.getAsMention(), false);
        builder.addField("Reason", reason, false);
        builder.addField("Days of messages deleted:", "7", false);
        builder.addField("Date", sdf.format(date), false);
        builder.addField("Time", stf.format(date), false);
        channel.sendMessage(builder.build()).queue();
    }
}
