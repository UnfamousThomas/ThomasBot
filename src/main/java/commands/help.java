package commands;

import net.dv8tion.jda.core.entities.Message;

public class help {
    public static void run(Message msg) {
        msg.getChannel().sendMessage("Helllloooo!").queue();
    }
}
