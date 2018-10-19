import events.SendMessageonMessage;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import utils.Commands;

public class WeGameBot {
    public static void main(String[] args) throws Exception {
        JDA jda = new JDABuilder(AccountType.BOT)
                .setToken("NDk4NTc3ODM1ODA0MzI3OTcy.Dp_MSQ.gK-2LnlRja6CU0FF1jKp92Ku5Z0")
                .addEventListener(new Commands())
                .build();
    }
}
