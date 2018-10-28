package commands.info;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import utils.settings;

import java.awt.*;

public class helpCommand extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (!e.getAuthor().isBot()) {
            String[] args = e.getMessage().getContentRaw().split(" ");
            if (args[0].equalsIgnoreCase(settings.prefix + "help")) {
                e.getChannel().sendMessage(e.getAuthor().getAsMention() + ", sent the list of commands to you!").queue();
                e.getAuthor().openPrivateChannel().queue(channel -> {
                    DMlog(e.getMember(), channel);
                });
            }
        }
    }
    public static void DMlog(Member sent, PrivateChannel channel) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Commands");
        String informationMsg = (
            "**———————————————————**\n" +
            "  **" + settings.prefix + "online**\n" + "*Displays online info, not including the bot.*\n" +
            "  **" + settings.prefix + "ping**\n" + "*Displays the bot's ping to Discord.*\n" +
            "  **" + settings.prefix + "help**\n" + "*Displays this message*\n" +
            "\n**———————————————————**"
        );
        builder.addField("Information",informationMsg, true);
        builder.setColor(Color.decode("#e84118"));
        String communicationMsg = (
            "**———————————————————**\n" +
            "  **" + settings.prefix + "suggest**\n" + "*Opens a channel for suggestions*\n" +
            "  **" + settings.prefix + "close**\n" + "*Closes suggestion channel (Staff)*\n" +
            "\n**———————————————————**"
        );
        builder.addField("Communication",communicationMsg, true);
        String moderationMsg = (
            "**———————————————————**\n" +
            "  **" + settings.prefix + "announce**\n" + "*Sends a message in announcements (Staff)*\n" +
            "  **" + settings.prefix + "ban**\n" + "*Ban's someone (Staff)*\n" +
            "  **" + settings.prefix + "kick**\n" + "*Kick's someone (Staff)*\n" +
            "  **" + settings.prefix + "mute**\n" + "*Mute's someone (Staff)*\n" +
            "  **" + settings.prefix + "warn**\n" + "*Warn's someone (Staff)*\n" +
            "  **" + settings.prefix + "unban**\n" + "*Unban's someone (Staff)*\n" +
            "  **" + settings.prefix + "clear**\n" + "*Clear's a chat for a certain amount of messages (Staff)*\n" +
            "\n**———————————————————**"
        );
        builder.addField("Moderation:", moderationMsg, false);


        channel.sendMessage(builder.build()).queue();
    }
}
