package commands.info;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import utils.settings;

public class pingCommand extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        User author = e.getAuthor();
        String name = author.getName();
        MessageChannel channel = e.getChannel();
        Message msg = e.getMessage();

        if (author.isBot()) {
            return;
        } else if (msg.getContentRaw().equalsIgnoreCase(settings.prefix + "ping")) {
            channel.sendMessage(author.getAsMention() + " Pong!").queue();

        }
    }
}
