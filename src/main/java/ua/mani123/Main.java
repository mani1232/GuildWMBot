package ua.mani123;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.mani123.command.WMCommand;
import ua.mani123.command.commands.*;
import ua.mani123.consoleCommands.consoleUtils;
import ua.mani123.database.Database;
import ua.mani123.listeners.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static Database dataBase;
    public static Guild main_guild;
    public static Role support_role;
    public static Role manager_role;

    public static ShardManager jda = null;

    public static Map<String, WMCommand> wmCommands = new HashMap<>();

    public static void main(String[] args) {
        dataBase = new Database("localhost", "27017", "worldmandia_test");
        jda = DefaultShardManagerBuilder.createDefault(PrivateData.discordBotToken).setMemberCachePolicy(MemberCachePolicy.ALL).enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.GUILD_MEMBERS).addEventListeners(
                new onGuildEvents(),
                new onSlashCommandEvent(),
                new onButtonInteractionEvent(),
                new onModalInteractionEvent(),
                new onContextInteractionEvent(),
                new onMessageEvent(),
                new onCommandAutoCompleteInteractionEvent(),
                new onReadyEvent(),
                new onEntitySelectInteractionEvent(),
                new onVoiceChatJoin(),
                new onStringSelectEvent()
        ).build();
        addCommand(List.of(
                new AccountCommand("account"),
                new BalanceCommand("balance"),
                new LanguageCommand("lang"),
                new ShopCommand("shop"),
                new CategoryCommand("category"),
                new RulesCommand("rules"),
                new DevCommand("dev")
        ));

        jda.setPresence(OnlineStatus.ONLINE, Activity.watching("test"));
        new Thread(new consoleUtils()).start();
    }

    public static void addCommand(List<WMCommand> command) {
        for (WMCommand wm : command) {
            wmCommands.put(wm.getName(), wm);
        }
    }

}