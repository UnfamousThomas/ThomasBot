package events;

import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class onGuildMessageReceived extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        User author = e.getAuthor();
        String name = author.getName();
        TextChannel channel = e.getChannel();
        Message msg = e.getMessage();
        boolean bot = author.isBot();

        if (msg.isFromType(ChannelType.TEXT) && !bot) {
            channel.sendMessage("Hello!");
        }

        }
    }
