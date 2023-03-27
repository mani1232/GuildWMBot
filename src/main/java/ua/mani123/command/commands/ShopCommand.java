package ua.mani123.command.commands;

import lombok.Getter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.DiscordLocale;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import ua.mani123.command.WMCommand;
import ua.mani123.database.DatabaseObjects.WMUser;

import java.util.Map;
import java.util.Objects;

@Getter
public class ShopCommand extends WMCommand {

    public ShopCommand(String name) {
        this.name = name;
        this.slashCommandData = Commands.slash("shop", "Command for interacting with the shop")
                .addSubcommands(
                        new SubcommandData("buy", "Sends a menu to select a product to buy")
                                .setDescriptionLocalizations(Map.of(
                                        DiscordLocale.ENGLISH_US, "Sends a menu to select a product to buy",
                                        DiscordLocale.ENGLISH_UK, "Sends a menu to select a product to buy",
                                        DiscordLocale.UKRAINIAN, "Відправляє меню вибору товару для покупки",
                                        DiscordLocale.RUSSIAN, "Отправляет меню выбора товара для покупки"))
                        //.addOption(OptionType.STRING, "count", "count", true)
                        , new SubcommandData("sell", "Sends a menu to select a product to sell"
                        ).setDescriptionLocalizations(Map.of(
                                DiscordLocale.ENGLISH_US, "Sends a menu to select a product to sell",
                                DiscordLocale.ENGLISH_UK, "Sends a menu to select a product to sell",
                                DiscordLocale.UKRAINIAN, "Відправляє меню вибору товару для продажу",
                                DiscordLocale.RUSSIAN, "Отправляет меню выбора товара для продажи"))
                        //.addOption(OptionType.STRING, "count", "count", true)
                );
    }

    @Override
    public void execute(SlashCommandInteractionEvent event, WMUser wmUser) {
        switch (Objects.requireNonNull(event.getSubcommandName())) {
            case "buy" -> event.replyEmbeds(new EmbedBuilder()
                    .setDescription(wmUser.getLang().getStrings().embed_description_buy_product_type)
                    .setTitle(wmUser.getLang().getStrings().embed_title_buy_product_type)
                    .build()).setEphemeral(true).addActionRow(
                    StringSelectMenu.create("buy_product_type")
                            .addOptions(SelectOption.of("Discord Nitro", "discord_nitro")
                                    .withEmoji(Emoji.fromFormatted("\uD83D\uDE80")))
                            .addOptions(SelectOption.of("Spotify premium", "spotify_premium")
                                    //.withDescription("")
                                    .withEmoji(Emoji.fromFormatted("\uD83C\uDFB6")))
                            .build()).queue();
            case "sell" ->
                    event.replyEmbeds(wmUser.getLang().getStrings().embed_not_working.build()).setEphemeral(true).queue();
            default ->
                    event.replyEmbeds(wmUser.getLang().getStrings().embed_default_error_title.build()).setEphemeral(true).queue();
        }
    }
}
