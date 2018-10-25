package commands.WIP;

import net.dv8tion.jda.core.entities.Category;
import net.dv8tion.jda.core.requests.restaction.ChannelAction;
import utils.settings;
import net.dv8tion.jda.core.EmbedBuilder;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import java.awt.*;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class suggest extends ListenerAdapter {
    String dbUrl="jdbc:mysql://localhost:3306/discordbot";
    String user = "bot";
    String pass = "koolkool123";
    @Override
    public void onMessageReceived(MessageReceivedEvent e) {
    String[] args = e.getMessage().getContentRaw().split(" ");
        String dcuser = e.getAuthor().getName();
        if(args.length == 1) {
            if (args[0].equalsIgnoreCase(settings.prefix + "suggest")) {
        try {
            Connection myConn = DriverManager.getConnection(dbUrl, user, pass);
            Statement stat = myConn.createStatement();
            String iduser = "SELECT * FROM suggestions WHERE UserID='" + e.getAuthor().getId() + "'";
            ResultSet existsID = stat.executeQuery(iduser);
            if(existsID.next()) {
                String hassu = "SELECT * FROM suggestions WHERE Suggestion='1'";
                ResultSet existhas = stat.executeQuery(hassu);
                System.out.println("YAY!");
                if (existhas.next()) {
                    System.out.println("yay2!");
                    e.getChannel().sendMessage("You already have a suggestion's channel. You can only have one!").queue();
                } } else {
                e.getGuild().getController().createTextChannel("suggestions3-" + e.getAuthor().getName()).queue();
                e.getChannel().sendMessage("Channel created. Look for a channel with your name.").queue();
                Connection mycon = DriverManager.getConnection(dbUrl, user, pass);

                Statement st =  mycon.createStatement();
                String rowsAffected = "INSERT INTO suggestions (UserID, Suggestion)" +
                        "VALUES (" + e.getAuthor().getId() +", 1)";
                PreparedStatement stt = mycon.prepareStatement(rowsAffected);
                stt.executeUpdate();
            }
        } catch (Exception er) {
            er.printStackTrace(); }

        }
        } if(args.length > 1 ||  args.length < 1) {
            sendErrorMessage(e.getTextChannel(), e.getMember());
        }
    }
    public void sendErrorMessage(TextChannel channel, Member member) {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("Invalid Usage!:");
        builder.setAuthor(member.getUser().getName(), member.getUser().getAvatarUrl(), member.getUser().getAvatarUrl());
        builder.setColor(Color.decode("#e84118"));
        builder.setDescription("[] - Required, {} - Optional");
        builder.addField("Proper usage: .suggest", "", false);
        channel.sendMessage(builder.build()).complete().delete().queueAfter(30, TimeUnit.SECONDS);
    }










    public void insert(String username, Integer discriminator, Integer ID, Integer time) {
        String dbUrl="jdbc:mysql://localhost:3306/discordbot";
        String user = "Administrator";
        String pass = "koolkool123";
        try {
            Connection myConn = DriverManager.getConnection(dbUrl, user, pass);
            Statement stat = myConn.createStatement();
            int rows = stat.executeUpdate("insert into suggestions " + "(Username, Discriminator, ID, Time) " + "values " + "username, discriminator, ID, time");
        } catch(Exception e) { e.printStackTrace(); }}


}
