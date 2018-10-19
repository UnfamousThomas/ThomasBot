package utils;

import commands.help;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {
    public void command(MessageReceivedEvent e) {
        Message message = e.getMessage();
        System.out.println("hihihi");
        if(message.getContentRaw().startsWith("!")) {
            e.getChannel().sendMessage("THIS WORKS!");
            System.out.println("hih2ihi");
            String[] args = message.getContentRaw().replaceFirst("!", "").split(" ");
            switch (args[0]) {
                case "help":
                    e.getChannel().sendMessage("THIS WORKS!");
                    System.out.println("hihihi3");
                    help.run(e.getMessage());
                    break;
            }
        }
    }
}
