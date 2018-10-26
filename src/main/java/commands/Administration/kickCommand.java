package commands.Administration;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import utils.settings;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class kickCommand extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] args = e.getMessage().getContentRaw().split(" ");

        if (args[0].equalsIgnoreCase(settings.prefix + "kick")) {
            if (e.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                if (args.length < 2) {
                    sendErrorMessage(e.getChannel(), e.getMember());
                } else if (args.length == 2) {
                    Member target = e.getMessage().getMentionedMembers().get(0);
                    e.getGuild().getController().kick(target).queue();
                    e.getMessage().delete().queue();
                    log(target, e.getMember(), e.getGuild().getTextChannelById(503937433856114709L));
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
        builder.addField("Proper usage: .kick [@USER]", "", false);
        channel.sendMessage(builder.build()).complete().delete().queueAfter(30, TimeUnit.SECONDS);
    }

    public void log(Member muted, Member muter, TextChannel channel) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/d/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Mute report");
        builder.setColor(Color.decode("#e84118"));
        builder.addField("Kicked User", muted.getAsMention(), false);
        builder.addField("Kicker/Moderator", muter.getAsMention(), false);
        builder.addField("Date", sdf.format(date), false);
        builder.addField("Time", stf.format(date), false);
        channel.sendMessage(builder.build()).queue();
    }
}
