package ua.mani123.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import org.jetbrains.annotations.NotNull;
import ua.mani123.Main;
import ua.mani123.database.DatabaseObjects.WMUser;

public class onButtonInteractionEvent extends ListenerAdapter {
    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        if (EventUtils.botOnline(event)) return;

        WMUser wmUser = Main.dataBase.getUserByDiscordId(event.getUser().getIdLong());
        if (wmUser == null) {
            EventUtils.sendRegisterModal(event);
            return;
        }
        //else if (wmUser.isBanned()) {
        //    event.replyEmbeds(account.getLang().getStrings().embed_banned.build()).setEphemeral(true).queue();
        //    return;
        //}
        switch (event.getComponentId()) {
            default -> {
                if (event.getComponentId().startsWith("buy-")) {
                    String[] parts = event.getComponentId().split("-");
                    event.getMessage().editMessageEmbeds(new EmbedBuilder().setTitle(event.getMessage().getEmbeds().get(0).getTitle()).setDescription(String.format(wmUser.getLang().getStrings().embed_buy, parts[2], wmUser.getLang().getStrings().accepted_wait)).build()).queue();
                    event.getMessage().editMessageComponents(ActionRow.of(event.getButton().asDisabled(), Button.of(ButtonStyle.DANGER, "cancel-" + event.getUser().getId() + "-" + parts[2], wmUser.getLang().getStrings().cancel))).queue();
                    wmUser.setBalance(wmUser.getBalance() - Integer.parseInt(parts[2]));
                    event.replyModal(Modal.create("buy_info-" + event.getUser().getId() + "-" + event.getMessage().getId() + "-" + parts[2], wmUser.getLang().getStrings().modal_buy_info_title)
                            .addActionRow(TextInput.create("mail", "Mail", TextInputStyle.SHORT)
                                    .setPlaceholder("Example: yourmail@gmail.com")
                                    .setRequiredRange(3, 100)
                                    .setRequired(true)
                                    .build())
                            .addActionRow(TextInput.create("password", "Password", TextInputStyle.SHORT)
                                    .setPlaceholder("Example: YourBestPassword123")
                                    .setRequiredRange(3, 100)
                                    .setRequired(true)
                                    .build())
                            .build()).queue();
                } else if (event.getComponentId().startsWith("cancel-")) {
                    String[] parts = event.getComponentId().split("-");
                    event.getMessage().editMessageEmbeds(new EmbedBuilder().setTitle(event.getMessage().getEmbeds().get(0).getTitle()).setDescription(String.format(wmUser.getLang().getStrings().embed_buy, parts[2], wmUser.getLang().getStrings().canceled)).build()).queue();
                    event.getMessage().editMessageComponents(ActionRow.of(event.getButton().asDisabled())).queue();
                    wmUser.setBalance(wmUser.getBalance() + Integer.parseInt(parts[2]));
                    event.replyEmbeds(wmUser.getLang().getStrings().embed_default_success_title.build()).setEphemeral(true).queue();
                } else {
                    event.replyEmbeds(wmUser.getLang().getStrings().embed_default_error_title.build()).setEphemeral(true).queue();
                }
            }
        }
    }


}
