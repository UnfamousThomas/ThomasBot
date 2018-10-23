package events.commands;

import events.settings;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.sql.BatchUpdateException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class muteCommand extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] args = e.getMessage().getContentRaw().split(" ");
        if (args[0].equalsIgnoreCase(settings.prefix + "mute")) {
            User author = e.getAuthor();
            Message msg = e.getMessage();
            String name = author.getName();
            boolean isbot = author.isBot();
            TextChannel cha = e.getChannel();


            if (!isbot) {
                if (args.length <= 1) {
                sendErrorMessage(cha, e.getMember());
                } else {
                    Member target = msg.getMentionedMembers().get(0);
                    Role muted = e.getGuild().getRoleById("503936463319072780");

                    e.getGuild().getController().addSingleRoleToMember(target, muted).queue();
                    e.getGuild().getController().removeSingleRoleFromMember(target, muted).queueAfter(1, TimeUnit.DAYS);

                    if(args.length >= 3){
                        String reason = "";
                        for (int i = 2; i < args.length; i++) {
                            reason += args[i] + " ";
                        }  log(target,e.getMember(), reason, e.getGuild().getTextChannelById("503937433856114709"));
                        msg.delete().queue();
                    } else {
                        log(target, e.getMember(),"", e.getGuild().getTextChannelById("503937433856114709"));
                        msg.delete().queue();
                    }
                }
            } else {
                e.getChannel().sendMessage("Command came from bot, will not respond.").queue();
                return;
            }
        }
    }
    public void sendErrorMessage(TextChannel channel, Member member) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Invalid Usage!:");
        builder.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        builder.setColor(Color.decode("#e84118"));
        builder.setDescription("[] - Required, {} - Optional");
        builder.addField("Proper usage: !mute [@USER] {reason}", "", false);
        channel.sendMessage(builder.build()).complete().delete().queueAfter(30, TimeUnit.SECONDS);
    }
    public void log(Member muted, Member muter, String reason, TextChannel channel) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/d/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Mute report");
        builder.setColor(Color.decode("#e84118"));
        builder.addField("Muted User", muted.getAsMention(), false);
        builder.addField("Muter/Moderator", muter.getAsMention(), false);
        builder.addField("Reason", reason, false);
        builder.addField("Date", sdf.format(date), false);
        builder.addField("Time", stf.format(date), false);
        channel.sendMessage(builder.build()).queue();

    }
}
