package events;

import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class pingCommand extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        User author = e.getAuthor();
        String name = author.getName();
        MessageChannel channel = e.getChannel();
        Message msg = e.getMessage();

        if (author.isBot()) { return; }
        else if(msg.getContentRaw().equalsIgnoreCase("!ping")) {
            channel.sendMessage(author.getAsMention() + " Pong!").queue();
        }

        }
    }
