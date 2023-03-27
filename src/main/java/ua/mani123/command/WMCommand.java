package ua.mani123.command;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import ua.mani123.database.DatabaseObjects.WMUser;

public abstract class WMCommand {

    public String name;

    public SlashCommandData slashCommandData;

    public String getName() {
        return name;
    }

    public SlashCommandData getSlashCommandData() {
        return slashCommandData;
    }

    public void execute(SlashCommandInteractionEvent event, WMUser wmUser) {}
}
