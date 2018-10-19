package events;

import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class onMessageReceived extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        User author = e.getAuthor();
        String name = author.getName();
        MessageChannel channel = e.getChannel();
        Message msg = e.getMessage();

        if (author.isBot()) { return; }
        else if(msg.equals("!helo")) {
            channel.sendMessage("HEY THERE, " + name);
        }

        }
    }
