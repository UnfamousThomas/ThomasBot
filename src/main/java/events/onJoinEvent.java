package events;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import commands.info.helpCommand;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class onJoinEvent extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent e) {
        e.getGuild().getController().addSingleRoleToMember(e.getMember(), e.getGuild().getRoleById(504239064426348555L)).queue();

        TextChannel general = e.getGuild().getTextChannelById(504239461706498079L);
        general.sendMessage(e.getMember().getAsMention() + " just joined our discord, we now have " + (e.getGuild().getMembers().size() - 1) + " members in our discord.").queue();
        int am =(e.getGuild().getMembers().size() -1);
        log(e.getMember(), e.getGuild().getTextChannelById("504413608399339546"), am);
        e.getUser().openPrivateChannel().queue(channel -> {
                    channel.sendMessage("Welcome to " + e.getGuild().getName() + ".We hope you enjoy your time here.");
                    helpCommand.DMlog(e.getMember(), channel);
                }
        );
    }
    public void log(Member joined, TextChannel channel, int amount) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/d/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Join report");
        builder.setColor(Color.decode("#e84118"));
        builder.addField("Joined User", joined.getAsMention(), false);
        builder.addField("Amount of users:", String.valueOf(amount), false);
        builder.addField("Date", sdf.format(date), false);
        builder.addField("Time", stf.format(date), false);
        channel.sendMessage(builder.build()).queue();

    }

}
