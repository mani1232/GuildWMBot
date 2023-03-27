package ua.mani123.command.commands;

import lombok.Getter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.DiscordLocale;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import ua.mani123.command.WMCommand;
import ua.mani123.database.DatabaseObjects.WMUser;

import java.time.Instant;
import java.util.Map;

@Getter
public class RulesCommand extends WMCommand {

    public RulesCommand(String name) {
        this.name = name;
        this.slashCommandData = Commands.slash(this.name, "The command to show the rules").setDescriptionLocalizations(Map.of(
                DiscordLocale.RUSSIAN, "Команда для отображения правил",
                DiscordLocale.UKRAINIAN, "Команда для показу правил"
        ));
    }

    @Override
    public void execute(SlashCommandInteractionEvent event, WMUser wmUser) {
        event.replyEmbeds(new EmbedBuilder().setDescription(wmUser.getLang().getStrings().rules).setTitle(wmUser.getLang().getStrings().rules_title).setTimestamp(Instant.now()).setFooter(wmUser.getLang().getStrings().rules_footer).build()).setEphemeral(true).queue();
    }
}
