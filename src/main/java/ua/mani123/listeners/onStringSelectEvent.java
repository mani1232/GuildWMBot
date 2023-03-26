package ua.mani123.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;
import org.jetbrains.annotations.NotNull;
import ua.mani123.Main;
import ua.mani123.database.DatabaseObjects.WMUser;
import ua.mani123.localization.Lang;

public class onStringSelectEvent extends ListenerAdapter {

    @Override
    public void onStringSelectInteraction(@NotNull StringSelectInteractionEvent event) {
        if (EventUtils.botOnline(event)) return;

        WMUser account = Main.dataBase.getUserByDiscordId(event.getUser().getIdLong());
        if (account == null) {
            EventUtils.sendRegisterModal(event);
            return;
        }
        switch (event.getComponentId()) {
            case "lang-set" -> {
                switch (event.getSelectedOptions().get(0).getValue()) {
                    case "ru" -> account.setLang(Lang.RU);
                    case "ua" -> account.setLang(Lang.UA);
                    case "en" -> account.setLang(Lang.EN);
                }
                Main.dataBase.updateUser(account);
                event.replyEmbeds(account.getLang().getStrings().embed_default_success_title.build()).setEphemeral(true).queue();
            }
            case "buy_product_type" -> {
                switch (event.getSelectedOptions().get(0).getValue()) {
                    case "discord_nitro" ->
                            event.replyEmbeds(new EmbedBuilder().setDescription(String.format(account.getLang().getStrings().embed_buy_variation, "Discord nitro")).build()).setEphemeral(true)
                                    .addActionRow(
                                            StringSelectMenu.create("buy_product")
                                                    .addOptions(SelectOption.of("Discord Nitro Full 1 " + account.getLang().getStrings().month, "discord_nitro_full_1m"))
                                                    .addOptions(SelectOption.of("Discord Nitro Full 12 " + account.getLang().getStrings().months, "discord_nitro_full_12m"))
                                                    .addOptions(SelectOption.of("Discord Nitro Basic 1 " + account.getLang().getStrings().month, "discord_nitro_basic_1m"))
                                                    .addOptions(SelectOption.of("Discord Nitro Basic 12 " + account.getLang().getStrings().months, "discord_nitro_basic_12m"))
                                                    .build())
                                    .queue();
                    case "spotify_premium" ->
                            event.replyEmbeds(new EmbedBuilder().setDescription(String.format(account.getLang().getStrings().embed_buy_variation, "Spotify premium")).build()).setEphemeral(true).queue();
                }
            }
            case "buy_product" -> {
                int cost;
                String product;
                switch (event.getSelectedOptions().get(0).getValue()) {
                    case "discord_nitro_full_1m" -> {
                        cost = 100;
                        product = "Discord Nitro Full 1 " + account.getLang().getStrings().month;
                    }
                    case "discord_nitro_full_12m" -> {
                        cost = 700;
                        product = "Discord Nitro Full 12 " + account.getLang().getStrings().month;
                    }
                    case "discord_nitro_basic_1m" -> {
                        cost = 35;
                        product = "Discord Nitro Basic 1 " + account.getLang().getStrings().month;
                    }
                    case "discord_nitro_basic_12m" -> {
                        cost = 350;
                        product = "Discord Nitro Basic 12 " + account.getLang().getStrings().month;
                    }
                    default -> {
                        product = "Not found";
                        cost = -1;
                    }
                }
                if (cost > 0) {
                    if (account.getBalance() >= cost) {
                        event.getJDA().openPrivateChannelById(event.getUser().getIdLong()).flatMap(privateChannel -> privateChannel.sendMessageEmbeds(new EmbedBuilder().setDescription(String.format(account.getLang().getStrings().embed_buy, cost, account.getLang().getStrings().waiting_confirmation)).setTitle(product).build())
                                .addActionRow(Button.of(ButtonStyle.SUCCESS, "buy-" + account.getDiscordId() + "-" + cost, account.getLang().getStrings().confirm))).queue();
                        event.replyEmbeds(account.getLang().getStrings().embed_default_success_title.build()).setEphemeral(true).queue();
                    } else {
                        event.replyEmbeds(account.getLang().getStrings().embed_balance_not_enough.build()).setEphemeral(true).queue();
                    }
                } else {
                    event.replyEmbeds(account.getLang().getStrings().embed_default_error_title.build()).setEphemeral(true).queue();
                }
            }
            case "deposit_method" ->
                    event.replyEmbeds(account.getLang().getStrings().embed_not_working.build()).setEphemeral(true).queue();
            default ->
                    event.replyEmbeds(account.getLang().getStrings().embed_default_error_title.build()).setEphemeral(true).queue();
        }
    }
}
