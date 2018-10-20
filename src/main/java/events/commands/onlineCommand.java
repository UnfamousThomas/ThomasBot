package events.commands;

import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import events.settings;
public class onlineCommand extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        User author = e.getAuthor();
        String name = author.getName();
        boolean bot = author.isBot();
        MessageChannel channel = e.getChannel();

        String[] args = e.getMessage().getContentRaw().split(" ");
        if(args[0].equalsIgnoreCase(settings.prefix + "online")) {
            int online = 0;
            if (!bot) {
                for (int i = 0; i < e.getGuild().getMembers().size(); i++){
                    if(e.getGuild().getMembers().get(i).getOnlineStatus() == OnlineStatus.ONLINE || e.getGuild().getMembers().get(i).getOnlineStatus() == OnlineStatus.DO_NOT_DISTURB) {
                        online++;
                    }
                }
                channel.sendMessage("There are: " + online + " members online. There are " + e.getGuild().getMembers().size() + " members in this discord server.").queue();
                } else channel.sendMessage(name + " is a bot! Will not respond.").queue();
            }

        }
    }
