import events.commands.muteCommand;
import events.commands.onlineCommand;
import events.commands.pingCommand;
import events.settings;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;


public class WeGameBot {
    public static void main(String[] args) {
        try {
            JDA jda = new JDABuilder(AccountType.BOT)
                    .setToken(settings.token)
                    .addEventListener(new pingCommand())
                    .addEventListener(new onlineCommand())
                    .addEventListener(new muteCommand())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
