package ua.mani123.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.interactions.callbacks.IModalCallback;
import net.dv8tion.jda.api.interactions.callbacks.IReplyCallback;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;

public class EventUtils {

    public static void sendRegisterModal(IModalCallback event) {
        String modal_title;
        String userName_Label;
        String userName_Placeholder;
        switch (event.getUserLocale()) {
            case RUSSIAN -> {
                modal_title = "Регистрация аккаунта";
                userName_Label = "Имя аккаунта";
                userName_Placeholder = "Имя аккаунта (будет видно всем)";
            }
            case UKRAINIAN -> {
                modal_title = "Реєстрація облікового запису";
                userName_Label = "Ім'я";
                userName_Placeholder = "Ім'я облікового запису (буде видно всім)";
            }
            default -> {
                modal_title = "Account Registration";
                userName_Label = "Display name";
                userName_Placeholder = "Your display name (will be visible to everyone)";
            }
        }
        TextInput userName = TextInput.create("display_name", userName_Label, TextInputStyle.SHORT)
                .setPlaceholder(userName_Placeholder)
                .setMaxLength(12)
                .setRequired(false)
                .build();
        event.replyModal(Modal.create("register_account", modal_title).addComponents(
                ActionRow.of(userName)
                /* TODO Need release discord feature
                ,ActionRow.of(StringSelectMenu.create("lang-set")
                        .addOptions(SelectOption.of("Русский", "ru")
                                .withDescription("Изменяет язык аккаунта на русский")
                                .withEmoji(Emoji.fromFormatted("\uD83C\uDDF7\uD83C\uDDFA")))
                        .addOptions(SelectOption.of("Українська", "ua")
                                .withDescription("Змінює мову облікового запису на українську")
                                .withEmoji(Emoji.fromFormatted("\uD83C\uDDFA\uD83C\uDDE6")))
                        .addOptions(SelectOption.of("English", "en")
                                .withDescription("Changes account language to English")
                                .withEmoji(Emoji.fromFormatted("\uD83C\uDDEC\uD83C\uDDE7")))
                        .build())
                 */
        ).build()).queue();
    }


    public static boolean isBotOffline(IReplyCallback event) {
        if (event.getJDA().getPresence().getStatus() == OnlineStatus.DO_NOT_DISTURB) {
            switch (event.getUserLocale()) {
                case UKRAINIAN ->
                        event.replyEmbeds(new EmbedBuilder().setDescription("Цей бот наразі вимкнений, будь ласка, спробуйте ще раз, коли статус бота буде `online` або `idle`").build()).setEphemeral(true).queue();
                case RUSSIAN ->
                        event.replyEmbeds(new EmbedBuilder().setDescription("В настоящее время этот бот отключен, повторите попытку, когда статус бота будет `online` или `idle`").build()).setEphemeral(true).queue();
                default ->
                        event.replyEmbeds(new EmbedBuilder().setDescription("This bot is currently disabled, please try again when the bot status is `online` or `idle`").build()).setEphemeral(true).queue();
            }
            return true;
        }
        return false;
    }


}
