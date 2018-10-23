package events;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageHistory;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class onMsgEdit extends ListenerAdapter {
    @Override
    public void onGuildMessageUpdate(GuildMessageUpdateEvent e) {
        log(e.getMember(), e.getMessage().getContentRaw(), e.getGuild().getTextChannelById(504413608399339546L));
    }
        public void log(Member changer, String msg, TextChannel channel) {
            SimpleDateFormat sdf = new SimpleDateFormat("MM/d/yyyy");
            SimpleDateFormat stf = new SimpleDateFormat("HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("Message Edit report");
            builder.setColor(Color.decode("#e84118"));
            builder.addField("Editer", changer.getAsMention(), false);
            builder.addField("Channel", channel.getAsMention(),false);
            builder.addField("New Message", msg, false);
            builder.addField("Date", sdf.format(date), false);
            builder.addField("Time", stf.format(date), false);
            channel.sendMessage(builder.build()).queue();
        }

}

