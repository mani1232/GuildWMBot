package ua.mani123.listeners;

import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import ua.mani123.Constants;
import ua.mani123.Main;
import ua.mani123.command.WMCommand;

import java.util.stream.Collectors;

public class onReadyEvent extends ListenerAdapter {

    @Override
    public void onReady(@NotNull ReadyEvent event) {

        event.getJDA().updateCommands().addCommands(Main.wmCommands.values().stream().map(WMCommand::getSlashCommandData).collect(Collectors.toList())).queue(commands -> Main.logger.info(String.format("%s commands updated", commands.size())));
        Main.main_guild = event.getJDA().getGuildById(Constants.MAIN_GUILD_ID);
        Main.support_role = Main.main_guild.getRoleById(981857783341260880L);
        //Main.manager_role = Main.main_guild.getRoleById(1080800182033190922L);
    }
}
