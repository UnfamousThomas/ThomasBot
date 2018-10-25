import commands.Administration.*;
import commands.WIP.suggest;
import commands.info.onlineCommand;
import commands.info.pingCommand;
import events.onJoinEvent;
import events.onMsgEdit;
import utils.settings;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.OnlineStatus;
import net.dv8tion.jda.core.entities.Game;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


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
                    .addEventListener(new warnCommand())
                    .addEventListener(new onJoinEvent())
                    .addEventListener(new banCommand())
                    .addEventListener(new unbanCommand())
                    .addEventListener(new kickCommand())
                    .addEventListener(new onMsgEdit())
                    .addEventListener(new suggest())

                    .setGame(Game.watching("Thomas play games on his discord!"))
                    .setStatus(OnlineStatus.DO_NOT_DISTURB)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
