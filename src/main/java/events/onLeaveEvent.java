package events;

import net.dv8tion.jda.core.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class onLeaveEvent extends ListenerAdapter {
    @Override
    public void onGuildLeave(GuildLeaveEvent e) {
        e.getGuild().getTextChannelById("504239461706498079").sendMessage("Some idiot left, I don't know who. But we now have: " + (e.getGuild().getMembers().size()-1) + " members.").queue();
    }
}
