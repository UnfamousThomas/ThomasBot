package events.commands;

import events.settings;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Category;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.requests.restaction.order.ChannelOrderAction;

import java.awt.*;
import java.util.concurrent.TimeUnit;

public class suggest extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
    String[] args = e.getMessage().getContentRaw().split(" ");

    if(args[0].equalsIgnoreCase(settings.prefix + "suggest")) {
        if(args.length != 1) {
           sendErrorMessage(e.getTextChannel(), e.getMember());
        } else {
            e.getChannel().sendMessage("hello...").queue();
            e.getGuild().getController().createTextChannel("suggest-" + e.getAuthor().getName()).queue();
            e.getChannel().sendMessage("Write your suggestion in the suggestion channel that was made for you!").queue();
        }
    }
}
    public void sendErrorMessage(TextChannel channel, Member member) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Invalid Usage!:");
        builder.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        builder.setColor(Color.decode("#e84118"));
        builder.setDescription("[] - Required, {} - Optional");
        builder.addField("Proper usage: .suggest [MESSAGE]", "", false);
        channel.sendMessage(builder.build()).complete().delete().queueAfter(30, TimeUnit.SECONDS);
    }


}
