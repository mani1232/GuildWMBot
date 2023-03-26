package ua.mani123;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.mani123.command.WMCommand;
import ua.mani123.command.commands.*;
import ua.mani123.consoleCommands.consoleUtils;
import ua.mani123.database.Database;
import ua.mani123.listeners.*;

import java.util.HashMap;
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
        jda = DefaultShardManagerBuilder.createDefault(PrivateData.discordBotToken).enableIntents(GatewayIntent.MESSAGE_CONTENT).addEventListeners(
                new onGuildReadyEvent(),
                new onSlashCommandEvent(),
                new onButtonInteractionEvent(),
                new onModalInteractionEvent(),
                new onContextInteractionEvent(),
                new onMessageEvent(),
                new onCommandAutoCompleteInteractionEvent(),
                new onReadyEvent(),
                new onEntitySelectInteractionEvent(),
                new onStringSelectEvent()
        ).build();
        wmCommands.putAll(Map.of(
                "account", new AccountCommand("account"),
                "balance", new BalanceCommand("balance"),
                "lang", new LanguageCommand("lang"),
                "shop", new ShopCommand("shop"),
                "dev", new DevCommand("dev")
                )
        );
        jda.setPresence(OnlineStatus.ONLINE, Activity.watching("test"));
        new Thread(new consoleUtils()).start();
    }
}