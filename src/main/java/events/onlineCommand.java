package events;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class onlineCommand extends ListenerAdapter {
    public void onlineComamnd(MessageReceivedEvent e) {
        Message meg = e.getMessage();
        User author = e.getAuthor();
        String name = author.getName();
        MessageChannel channel = e.getChannel();

        if(!author.isBot()) {
            if (meg.getContentRaw().contains("!online")) {
                channel.sendMessage(e.getGuild().getMembers())
        }
    }}}
