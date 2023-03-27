package ua.mani123.command.commands;

import lombok.Getter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.DiscordLocale;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import ua.mani123.Main;
import ua.mani123.command.WMCommand;
import ua.mani123.database.DatabaseObjects.WMUser;

import java.util.Map;
import java.util.Objects;

@Getter
public class BalanceCommand extends WMCommand {

    public BalanceCommand(String name) {
        this.name = name;
        this.slashCommandData = Commands.slash(this.name, "Command to manage the real balance")
                .addSubcommands(
                        new SubcommandData("deposit", "Command for replenishing the real balance of the bot")
                                .setDescriptionLocalizations(Map.of(
                                        DiscordLocale.ENGLISH_US, "Command for replenishing the real balance of the bot",
                                        DiscordLocale.ENGLISH_UK, "Command for replenishing the real balance of the bot",
                                        DiscordLocale.UKRAINIAN, "Команда для поповнення реального балансу робота",
                                        DiscordLocale.RUSSIAN, "Команда для пополнения реального баланса бота"))
                                .addOption(OptionType.INTEGER, "count", "count", true)
                        ,new SubcommandData("send", "Sends coins from real balance")
                                .setDescriptionLocalizations(Map.of(
                                        DiscordLocale.ENGLISH_US, "Sends coins from real balance",
                                        DiscordLocale.ENGLISH_UK, "Sends coins from real balance",
                                        DiscordLocale.UKRAINIAN, "Відправляє монети з реального балансу",
                                        DiscordLocale.RUSSIAN, "Отправляет монеты из реального баланса"))
                                .addOption(OptionType.INTEGER, "count", "count", true)
                                .addOption(OptionType.STRING, "user_id", "to user id", true)
                        //,new SubcommandData("withdraw", "Command for withdrawing funds from the real balance of the bot"
                        //).setDescriptionLocalizations(Map.of(
                        //                DiscordLocale.ENGLISH_US, "Command for withdrawing funds from the real balance of the bot",
                        //                DiscordLocale.ENGLISH_UK, "Command for withdrawing funds from the real balance of the bot",
                        //                DiscordLocale.UKRAINIAN, "Команда для виведення коштів із реального балансу робота",
                        //                DiscordLocale.RUSSIAN, "Команда для вывода средств из реального баланса бота"))
                        //        .addOption(OptionType.INTEGER, "count", "count", true)
                );
    }

    @Override
    public void execute(SlashCommandInteractionEvent event, WMUser wmUser) {
        int count = Objects.requireNonNull(event.getOption("count")).getAsInt();
        switch (Objects.requireNonNull(event.getSubcommandName())) {
            case "send" -> {
                if (wmUser.getBalance() >= count) {
                    long userId = Objects.requireNonNull(event.getOption("user_id")).getAsLong();
                    WMUser sendAccount = Main.dataBase.getUserByDiscordId(userId);
                    if (sendAccount != null) {
                        wmUser.setBalance(wmUser.getBalance() - count);
                        sendAccount.setBalance(sendAccount.getBalance() + count);
                        event.getJDA().openPrivateChannelById(userId).flatMap(privateChannel -> privateChannel.sendMessageEmbeds(new EmbedBuilder().setDescription(String.format(sendAccount.getLang().getStrings().send_money, count, event.getUser().getAsTag())).build())).queue();
                        event.replyEmbeds(wmUser.getLang().getStrings().embed_default_success_title.build()).setEphemeral(true).queue();
                    }
                } else {
                    event.replyEmbeds(wmUser.getLang().getStrings().embed_balance_not_enough.build()).setEphemeral(true).queue();
                }
            }
            //case "withdraw" ->
            //        event.replyEmbeds(account.getLang().getStrings().embed_not_working.build()).setEphemeral(true).queue();
            case "deposit" ->
                    event.replyEmbeds(new EmbedBuilder().setDescription(String.format(wmUser.getLang().getStrings().embed_deposit, count, wmUser.getBalance(), wmUser.getBalance() + count)).build()).setEphemeral(true).addActionRow(
                            StringSelectMenu.create("deposit_method")
                                    .addOptions(SelectOption.of("MonoBank", "deposit_monobank")
                                                    .withDescription("Visa/MaterCard, Google pay, Apple pay, MonoPay app")
                                            //.withEmoji(Emoji.fromFormatted("\uD83D\uDCB0"))
                                    ).build()).queue();
            default ->
                    event.replyEmbeds(wmUser.getLang().getStrings().embed_default_error_title.build()).setEphemeral(true).queue();
        }
    }
}
