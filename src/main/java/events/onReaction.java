package events;

import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class onReaction extends ListenerAdapter {
    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent e) {
        if(e.getReactionEmote().getName().equals("\u1f600")) {
            e.getChannel().sendMessage("GRINNING!!!!").queue();
        }
    }
}
