package ua.mani123.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import ua.mani123.database.DatabaseObjects.WMUser;

public abstract class WMCommand {

    public String getName() {
        return null;
    }

    public SlashCommandData getSlashCommandData() {
        return null;
    }

    public void execute(SlashCommandInteractionEvent event, WMUser wmUser) {}
}
