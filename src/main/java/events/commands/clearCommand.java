package events.commands;

import events.settings;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class clearCommand extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] args = e.getMessage().getContentRaw().split(" ");

        if(args[0].equalsIgnoreCase(settings.prefix + "clear")) {
            Message msg = e.getMessage();
            User author = e.getAuthor();
            String name = author.getName();
            TextChannel channel = e.getChannel();
            Member m = e.getMember();
            boolean isbot = author.isBot();
            if (args.length <= 2) {
                sendErrorMessage(channel, m);
            } else {
                msg.delete().queue();
                TextChannel target = e.getMessage().getMentionedChannels().get(0);
                purgeMessages(target, Integer.parseInt(args[2]));
                if(args.length < 3) {
                    String reason = "";
                    for (int i = 3; i < args.length; i++) {
                        reason += args[i] + " ";
                    }
                    log(m, args[2], reason, e.getGuild().getTextChannelById("503160801880702989"), target);
                } else {
                    log(m, args[2], "", e.getGuild().getTextChannelById("503160801880702989"), target);
                }

        }
    }}
    public void sendErrorMessage(TextChannel channel, Member member) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Invalid Usage!:");
        builder.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        builder.setColor(Color.decode("#e84118"));
        builder.setDescription("[] - Required, {} - Optional");
        builder.addField("Proper usage: !clear [channel] [Message amount] [reason]", "", false);
        channel.sendMessage(builder.build()).complete().delete().queueAfter(30, TimeUnit.SECONDS);
    }
    public void log(Member moderator, String num, String reason, TextChannel incident, TextChannel cleared) {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/d/yyyy");
        SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Mute report");
        builder.setColor(Color.decode("#e84118"));
        builder.addField("Cleared Channel", cleared.getAsMention(), false);
        builder.addField("Number of messages cleared", num, false);
        builder.addField("Moderator", moderator.getAsMention(), false);
        builder.addField("Reason", reason, false);
        builder.addField("Date", sdf.format(date), false);
        builder.addField("Time", stf.format(date), false);
        incident .sendMessage(builder.build()).queue();

    }
    private void purgeMessages(TextChannel channel, int num) {
        MessageHistory history = new MessageHistory(channel);
        List<Message> msgs;

        msgs = history.retrievePast(num).complete();
        channel.deleteMessages(msgs).queue();
    }
}
