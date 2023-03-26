package ua.mani123.command.commands;

import lombok.Getter;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.DiscordLocale;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import ua.mani123.command.WMCommand;
import ua.mani123.database.DatabaseObjects.WMUser;

import java.util.Map;

@Getter
public class LanguageCommand extends WMCommand {

    String name;
    SlashCommandData slashCommandData;

    public LanguageCommand(String name) {
        this.name = name;
        this.slashCommandData = Commands.slash(this.name, "Sends a menu to change the account language")
                .setDescriptionLocalizations(Map.of(
                        DiscordLocale.ENGLISH_US, "Sends a menu to change the account language",
                        DiscordLocale.ENGLISH_UK, "Sends a menu to change the account language",
                        DiscordLocale.UKRAINIAN, "Відправляє меню для зміни мови облікового запису",
                        DiscordLocale.RUSSIAN, "Отправляет меню для изменения языка аккаунта"));
    }

    @Override
    public void execute(SlashCommandInteractionEvent event, WMUser wmUser) {
        event.replyEmbeds(wmUser.getLang().getStrings().embed_choose_lang.build()).setEphemeral(true).addActionRow(
                StringSelectMenu.create("lang-set")
                        .addOptions(SelectOption.of("Русский", "ru")
                                .withDescription("Изменяет язык аккаунта на русский")
                                .withEmoji(Emoji.fromFormatted("\uD83C\uDDF7\uD83C\uDDFA")))
                        .addOptions(SelectOption.of("Українська", "ua")
                                .withDescription("Змінює мову облікового запису на українську")
                                .withEmoji(Emoji.fromFormatted("\uD83C\uDDFA\uD83C\uDDE6")))
                        .addOptions(SelectOption.of("English", "en")
                                .withDescription("Changes account language to English")
                                .withEmoji(Emoji.fromFormatted("\uD83C\uDDEC\uD83C\uDDE7")))
                        .build()).queue();
    }
}
