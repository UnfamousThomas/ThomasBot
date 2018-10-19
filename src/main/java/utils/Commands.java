package utils;

import commands.help;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {
    public void command(MessageReceivedEvent e) {
        Message message = e.getMessage();
        if(message.getContentRaw().startsWith("!")) {
            String[] args = message.getContentRaw().replaceFirst("!", " ").split(" ");
            switch (args[0]) {
                case "help":
                    help.run(e.getMessage());
                    break;
            }
        }
    }
}
