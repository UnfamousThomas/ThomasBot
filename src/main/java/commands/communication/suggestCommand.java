package commands.communication;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Category;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import utils.settings;

import java.awt.*;
import java.sql.*;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;

import static utils.settings.*;


public class suggestCommand extends ListenerAdapter {


    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
        String[] args = e.getMessage().getContentRaw().split(" ");
        String dcuser = e.getAuthor().getName();
            if (args[0].equalsIgnoreCase(settings.prefix + "suggest")) {
                if (args.length == 1) {
                    try {
                    Connection myConn = DriverManager.getConnection(dbUrl, user, pass);
                    Statement stat = myConn.createStatement();
                    String iduser = "SELECT * FROM suggestions WHERE UserID='" + e.getAuthor().getId() + "'";
                    ResultSet existsID = stat.executeQuery(iduser);
                    if (existsID.next()) {
                        String hassu = "SELECT * FROM suggestions WHERE Suggestion='1'";
                        ResultSet existhas = stat.executeQuery(hassu);
                        if (existhas.next()) {
                            e.getChannel().sendMessage("You already have a suggestion's channel. You can only have one!").queue();
                        }
                    } else {
                        e.getGuild().getController().createTextChannel("suggestions-" + e.getAuthor().getName()).queue(channel -> {
                             TextChannel c = (TextChannel) channel;
                            Category sugs = e.getGuild().getCategoryById("505140448520830998");
                             channel.getManager().setParent(sugs).queue();
                            e.getChannel().sendMessage("Channel created: " + ((TextChannel) channel).getAsMention()).queue();
                            channel.getManager().setTopic("Suggestion channel for: " + e.getAuthor().getName()).queue();

                            channel.getManager().putPermissionOverride(e.getMember(), EnumSet.of(Permission.MESSAGE_READ),null).queue();
                            channel.getManager().putPermissionOverride(e.getGuild().getPublicRole(), null , EnumSet.of(Permission.MESSAGE_READ)).queueAfter(3, TimeUnit.SECONDS);


                        });
                        Connection mycon = DriverManager.getConnection(dbUrl, user, pass);
                        Statement st = mycon.createStatement();
                        String rowsAffected = "INSERT INTO suggestions (UserID, Suggestion)" +
                                "VALUES (" + e.getAuthor().getId() + ", 1)";
                        PreparedStatement stt = mycon.prepareStatement(rowsAffected);
                        stt.executeUpdate();
                    }
                } catch (Exception er) {
                    er.printStackTrace();
                }

            } else if (args.length >= 2) {
                    sendErrorMessage(e.getTextChannel(), e.getMember());
        }
        }
    }

    public void sendErrorMessage(TextChannel channel, Member member) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Invalid Usage!:");
        builder.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        builder.setColor(Color.decode("#e84118"));
        builder.setDescription("[] - Required, {} - Optional");
        builder.addField("Proper usage: " + settings.prefix + "suggest", "", false);
        channel.sendMessage(builder.build()).complete().delete().queueAfter(30, TimeUnit.SECONDS);
    }
}
