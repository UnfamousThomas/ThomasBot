package events;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class SendMessageonMessage extends ListenerAdapter {
    public void onMessageReceived(GuildMessageReceivedEvent e) {
        Message mes = e.getMessage();
        User author = e.getAuthor();
        String us = author.getName();
        TextChannel channel = e.getChannel();

        if(!author.isBot()) {
            channel.sendMessage("Hello" + us + ". How are you doing? Your message was:" + mes.getContentRaw()).queue();
        }
    }
}