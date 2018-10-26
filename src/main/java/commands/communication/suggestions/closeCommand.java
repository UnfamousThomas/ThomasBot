package commands.communication.suggestions;

import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.Permission;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.PrivateChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import utils.settings;

import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import static utils.settings.dbUrl;
import static utils.settings.pass;
import static utils.settings.user;

public class closeCommand extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] args = e.getMessage().getContentRaw().split(" ");
        TextChannel logs = e.getGuild().getTextChannelById("504413608399339546");

        if (args[0].equalsIgnoreCase(settings.prefix + "close")) {
            if (!e.getAuthor().isBot() && e.getMember().hasPermission(Permission.ADMINISTRATOR)){
                Member m = e.getGuild().getMemberById(e.getChannel().getTopic());
                if (args.length == 2) {
                    if (args[1].equalsIgnoreCase("denied")) {
                        log(m, e.getMember(), false, logs);
                        m.getUser().openPrivateChannel().queue(channel -> {
                                    logDM(m, e.getMember(), false, channel);
                                }

                        );
                        try {
                            Connection myConn = DriverManager.getConnection(dbUrl, user, pass);
                            Statement stat = myConn.createStatement();
                            String sethas = "UPDATE suggestions SET Suggestion=0 WHERE UserID=" + e.getChannel().getTopic();
                            PreparedStatement stt =  myConn.prepareStatement(sethas);
                            stt.executeUpdate();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        e.getChannel().delete().queue();
                    }


                    else if(args[1].equalsIgnoreCase("accepted")) {
                        log(m, e.getMember(), true, logs);
                        m.getUser().openPrivateChannel().queue(channel -> {
                                    logDM(m, e.getMember(), true, channel);
                                }

                        );
                        try {
                            Connection myConn = DriverManager.getConnection(dbUrl, user, pass);
                            Statement stat = myConn.createStatement();
                            String sethas = "UPDATE suggestions SET Suggestion=0 WHERE UserID=" + e.getChannel().getTopic();
                            PreparedStatement stt =  myConn.prepareStatement(sethas);
                            stt.executeUpdate();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        e.getChannel().delete().queue();
                    } else {
                        sendErrorMessage(e.getChannel(), e.getMember());
                    }
                } else {
                    sendErrorMessage(e.getChannel(), e.getMember());
                }
            } else {
                e.getChannel().sendMessage("You are a bot or you do not have enough permissions.");
            }

        }
    }

    public void log(Member suggestioner, Member staff, Boolean accepted, TextChannel channel) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Suggestion report!");
        builder.setColor(Color.decode("#e84118"));
        if (accepted) {
            builder.setDescription(suggestioner.getAsMention() + " suggestion has been **accepted**.");
            builder.addField("Staff member: ", staff.getAsMention(), false);
        } else if (!accepted) {
            builder.setDescription(suggestioner.getAsMention() + " suggestion has been **denied**.");
            builder.addField("Staff member: ", staff.getAsMention(), false);
        }
        channel.sendMessage(builder.build()).queue();
    }

    public void logDM(Member suggestioner, Member staff, Boolean accepted, PrivateChannel channel) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Suggestion report!");
        builder.setColor(Color.decode("#e84118"));
        if (accepted) {
            builder.setDescription(suggestioner.getAsMention() + " suggestion has been **accepted**.");
            builder.addField("Staff member: ", staff.getAsMention(), false);
        } else if (!accepted) {
            builder.setDescription(suggestioner.getAsMention() + " suggestion has been **denied**.");
            builder.addField("Staff member: ", staff.getAsMention(), false);
        }
        channel.sendMessage(builder.build()).queue();
    }
    public void sendErrorMessage(TextChannel channel, Member member) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Invalid Usage!:");
        builder.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        builder.setColor(Color.decode("#e84118"));
        builder.setDescription("[] - Required, {} - Optional");
        builder.addField("Proper usage: !close [ACCEPTED|DENIED]", "", false);
        channel.sendMessage(builder.build()).complete().delete().queueAfter(30, TimeUnit.SECONDS);
}}
