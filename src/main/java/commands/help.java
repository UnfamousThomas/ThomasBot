package commands;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class help extends ListenerAdapter {
    public void onMessage(MessageReceivedEvent e) {
        if(!(e.getAuthor().isBot()) && e.getMessage().getContentRaw().equalsIgnoreCase("!help")) {
            e.getChannel().sendMessage("helloooooooooo!").queue();
        }
    }}
