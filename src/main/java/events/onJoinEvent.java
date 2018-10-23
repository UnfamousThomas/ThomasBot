package events;

import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;


public class onJoinEvent extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(GuildMemberJoinEvent e) {
        e.getGuild().getController().addSingleRoleToMember(e.getMember(), e.getGuild().getRoleById(504239064426348555L)).queue();

        TextChannel general = e.getGuild().getTextChannelById(504239461706498079L);
        general.sendMessage("We now have" + (e.getGuild().getMembers().size() -1) + " members in our discord.");
    }
}
