import events.commands.*;
import events.onJoinEvent;
import events.onReaction;
import events.settings;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;


public class WeGameBot {
    public static void main(String[] args) {
        try {
            JDA jda = new JDABuilder(AccountType.BOT)
                    .setToken(settings.token)
                    .addEventListener(new pingCommand())
                    .addEventListener(new onlineCommand())
                    .addEventListener(new muteCommand())
                    .addEventListener(new clearCommand())
                    .addEventListener(new announceCommand())
                    .addEventListener(new onReaction())
                    .addEventListener(new warnCommand())
                    .addEventListener(new onJoinEvent())

                    .setGame(Game.watching("Thomas play games on his discord!"))
                    .setStatus(OnlineStatus.DO_NOT_DISTURB)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
