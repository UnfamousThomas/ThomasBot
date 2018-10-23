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

public class unbanCommand extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (e.getMember().isOwner()) {
            String[] args = e.getMessage().getContentRaw().split(" ");
            if (args[0].equalsIgnoreCase(settings.prefix + "unban")) {
                if (args.length == 2) {
                    String target = args[1];
                    e.getGuild().getController().unban(target).queue();
                    log(e.getMember().getUser(), target, e.getGuild().getTextChannelById(503937433856114709L));



                } else if(args.length > 2 | args.length < 2 ) {
                    sendErrorMessage(e.getChannel(), e.getMember());
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
        builder.addField("Proper usage: .unban [@USER]", "", false);
        channel.sendMessage(builder.build()).complete().delete().queueAfter(30, TimeUnit.SECONDS);
    }
    public void log(User banner, String  banned, TextChannel channel) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/d/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Unban report");
        builder.setColor(Color.decode("#e84118"));
        builder.addField("Unbaned User (ID)", banned, false);
        builder.addField("Unbanner/Moderator", banner.getAsMention(), false);
        builder.addField("Date", sdf.format(date), false);
        builder.addField("Time", stf.format(date), false);
        channel.sendMessage(builder.build()).queue();
}
    public void DMlog(User banned, String unbanner, PrivateChannel channel) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/d/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("You have been unbanned. Here is some info about your unban:");
        builder.setColor(Color.decode("#e84118"));
        builder.addField("Unbanned User", banned.getAsMention(), false);
        builder.addField("Unbanner", unbanner, false);
        builder.addField("Date", sdf.format(date), false);
        builder.addField("Time", stf.format(date), false);
        channel.sendMessage(builder.build()).queue();
    }}
