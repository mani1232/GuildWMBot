package ua.mani123.listeners;

import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import ua.mani123.Main;
import ua.mani123.database.DatabaseObjects.WMUser;
import ua.mani123.localization.Lang;

public class onModalInteractionEvent extends ListenerAdapter {

    @Override
    public void onModalInteraction(@NotNull ModalInteractionEvent event) {
        if (EventUtils.botOnline(event)) return;

        WMUser wmUser = Main.dataBase.getUserByDiscordId(event.getUser().getIdLong());
        switch (event.getModalId()) {
            case "register_account" -> {
                if (wmUser == null) {
                    wmUser = new WMUser();
                    wmUser.setDiscordId(event.getUser().getIdLong());
                    String realName = event.getValue("display_name").getAsString();
                    if (!realName.equals("")) {
                       wmUser.setDisplayName(realName);
                    }
                    switch (event.getUserLocale()) {
                        case RUSSIAN -> {
                            wmUser.setLang(Lang.RU);
                            Main.dataBase.insertUser(wmUser);
                        }
                        case UKRAINIAN -> {
                            wmUser.setLang(Lang.UA);
                            Main.dataBase.insertUser(wmUser);
                        }
                        default -> {
                            wmUser.setLang(Lang.EN);
                            Main.dataBase.insertUser(wmUser);
                        }
                    }
                    Main.logger.info(String.format("""
                            Announce
                            New account registered:
                            user id: %s
                            user tag: %s
                            user uuid: %s
                            """, wmUser.getDiscordId(), event.getUser().getAsTag(), wmUser.getId()
                    ));
                    event.replyEmbeds(wmUser.getLang().getStrings().embed_default_success_title.build()).setEphemeral(true).queue();
                }
               // else if (wmUser.isBanned()) {
               //     event.replyEmbeds(wmUser.getLang().getStrings().embed_banned.build()).setEphemeral(true).queue();
               // }
            }
            default -> {
                if (event.getModalId().startsWith("buy_info")) {
                    String[] parts = event.getModalId().split("-");
                    TextChannel textChannel = Main.main_guild.getChannelById(TextChannel.class, 1L);

                } else {
                    event.replyEmbeds(wmUser.getLang().getStrings().embed_default_error_title.build()).setEphemeral(true).queue();
                }
            }
        }
    }

}
