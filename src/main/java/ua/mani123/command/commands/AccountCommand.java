package ua.mani123.command.commands;

import lombok.Getter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.DiscordLocale;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import ua.mani123.Constants;
import ua.mani123.Main;
import ua.mani123.command.WMCommand;
import ua.mani123.database.DatabaseObjects.WMUser;

import java.awt.*;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Getter
public class AccountCommand extends WMCommand {

    public AccountCommand(String name) {
        this.name = name;
        this.slashCommandData = Commands.slash(this.name, "Shows account information and settings")
                .addSubcommands(
                        new SubcommandData("set_real_name", "Changes the real account name")
                                .setDescriptionLocalizations(Map.of(
                                        DiscordLocale.UKRAINIAN, "Змінює справжнє ім'я облікового запису",
                                        DiscordLocale.RUSSIAN, "Изменяет реальное имя аккаунта"))
                                .addOption(OptionType.STRING, "real_name", "Real Name", true),
                        new SubcommandData("set_private", "Changes account privacy")
                                .setDescriptionLocalizations(Map.of(
                                        DiscordLocale.UKRAINIAN, "Змінює приватність облікового запису",
                                        DiscordLocale.RUSSIAN, "Изменяет приватность аккаунта"))
                                .addOption(OptionType.BOOLEAN, "private", "Is private", true),
                        new SubcommandData("info", "Shows account information and settings"
                        ).setDescriptionLocalizations(Map.of(
                                        DiscordLocale.UKRAINIAN, "Показує інформацію та налаштування облікового запису",
                                        DiscordLocale.RUSSIAN, "Показывает информацию об учетной записи и настройки"))
                                .addOption(OptionType.STRING, "account_id", "account ID", false)
                );
    }

    @Override
    public void execute(SlashCommandInteractionEvent event, WMUser wmUser) {
        switch (Objects.requireNonNull(event.getSubcommandName())) {
            case "info" -> {
                EmbedBuilder embed = new EmbedBuilder();
                if (event.getOption("account_id") != null) {
                    if (!wmUser.isPrivateUser()) {
                        WMUser otherAccount = Main.dataBase.getUserByDiscordId(Long.valueOf(Objects.requireNonNull(event.getOption("account_id")).getAsString()));
                        if (otherAccount != null) {
                            embed.setTimestamp(Instant.now())
                                    .setColor(Color.decode(Constants.DARK_GREEN))
                                    .setFooter(otherAccount.getLang().getStrings().embed_default_footer)
                                    .setDescription(String.format(otherAccount.getLang().getStrings().embed_description_account_info, otherAccount.getDisplayName(), otherAccount.getBalance(), otherAccount.getGameBalance(), otherAccount.getExperience(), otherAccount.getLang().getNativeName(), "********-****-****-****-************"));
                        } else {
                            event.replyEmbeds(wmUser.getLang().getStrings().embed_default_error_title.build()).setEphemeral(true).queue();
                        }
                    } else {
                        event.replyEmbeds(new EmbedBuilder().setTitle(wmUser.getLang().getStrings().private_account).build()).setEphemeral(true).queue();
                        return;
                    }
                } else {
                    embed.setTimestamp(Instant.now())
                            .setColor(Color.decode(Constants.DARK_GREEN))
                            .setFooter(wmUser.getLang().getStrings().embed_default_footer)
                            .setDescription(String.format(wmUser.getLang().getStrings().embed_description_account_info, wmUser.getDisplayName(), wmUser.getBalance(), wmUser.getGameBalance(), wmUser.getExperience(), wmUser.getLang().getNativeName(), wmUser.getId()));
                }
                User user = Main.jda.getUserById(wmUser.getDiscordId());
                if (user != null) {
                    embed.setThumbnail(Objects.requireNonNullElse(user.getAvatarUrl(), ""))
                            .setTitle(user.getAsTag());
                }
                event.deferReply(true).queue(
                        hook -> hook.editOriginalEmbeds(embed.build()).queueAfter(3, TimeUnit.SECONDS));
            }
            case "set_real_name" -> {
                wmUser.setDisplayName(Objects.requireNonNull(event.getOption("real_name")).getAsString());
                event.replyEmbeds(wmUser.getLang().getStrings().embed_default_success_title.build()).setEphemeral(true).queue();
            }
            case "set_private" -> {
                wmUser.setPrivateUser(Objects.requireNonNull(event.getOption("private")).getAsBoolean());
                event.replyEmbeds(wmUser.getLang().getStrings().embed_default_success_title.build()).setEphemeral(true).queue();
            }
            default ->
                    event.replyEmbeds(wmUser.getLang().getStrings().embed_default_error_title.build()).setEphemeral(true).queue();
        }
    }
}
