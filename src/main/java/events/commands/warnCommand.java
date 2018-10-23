package events.commands;

import events.settings;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class warnCommand extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String args[] = e.getMessage().getContentRaw().split(" ");
        if (args[0].equalsIgnoreCase(settings.prefix + "warn")) {
            if (args.length >= 3) {
                Member target = e.getMessage().getMentionedMembers().get(0); //Getting an error here, will look into later?
                String reason = "";
                for (int i = 2; i < args.length; i++) {
                    reason += args[i] + " ";
                } log(target, e.getMember(), reason, e.getGuild().getTextChannelById("503937433856114709"));
                User u = target.getUser();
                String finalReason = reason;
                u.openPrivateChannel().queue(channel -> {
                    channel.sendMessage("You have been warned. The reason for this is: ").queue();
                    channel.sendMessage(finalReason).queue();
                    channel.sendMessage("You were warned by: ").queue();
                    channel.sendMessage(e.getAuthor().getName()).queue();
                });


            } else if(args.length < 3) {
                sendErrorMessage(e.getChannel(), e.getMember());
            }
        }
    }
    public void sendErrorMessage(TextChannel channel, Member member) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Invalid Usage!:");
        builder.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        builder.setColor(Color.decode("#e84118"));
        builder.setDescription("[] - Required, {} - Optional");
        builder.addField("Proper usage: !warn [@USER] [reason]", "", false);
        channel.sendMessage(builder.build()).complete().delete().queueAfter(30, TimeUnit.SECONDS);
    }
    public void log(Member warned, Member warner, String reason, TextChannel channel) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/d/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Warn report");
        builder.setColor(Color.decode("#e84118"));
        builder.addField("Warned User", warned.getAsMention(), false);
        builder.addField("Warner/Moderator", warner.getAsMention(), false);
        builder.addField("Reason", reason, false);
        builder.addField("Date", sdf.format(date), false);
        builder.addField("Time", stf.format(date), false);
        channel.sendMessage(builder.build()).queue();

    }
    public void DMlog(Member warned, Member warner, String reason, PrivateChannel channel) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/d/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Warn report");
        builder.setColor(Color.decode("#e84118"));
        builder.addField("Muted User", warned.getAsMention(), false);
        builder.addField("Muter/Moderator", warner.getAsMention(), false);
        builder.addField("Reason", reason, false);
        builder.addField("Date", sdf.format(date), false);
        builder.addField("Time", stf.format(date), false);
        channel.sendMessage(builder.build()).queue();
    }
}
