package ua.mani123.listeners;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import ua.mani123.Main;
import ua.mani123.database.DatabaseObjects.WMUser;

public class onSlashCommandEvent extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (EventUtils.isBotOffline(event)) return;

        WMUser wmUser = Main.dataBase.getUserByDiscordId(event.getUser().getIdLong());
        if (wmUser == null) {
            EventUtils.sendRegisterModal(event);
        } else if (wmUser.isPrivateUser()) {
            event.replyEmbeds(wmUser.getLang().getStrings().embed_banned.build()).setEphemeral(true).queue();
        } else {
            if (Main.wmCommands.containsKey(event.getName())) {
                Main.wmCommands.get(event.getName()).execute(event, wmUser);
            } else {
                event.replyEmbeds(wmUser.getLang().getStrings().embed_default_error_title.build()).setEphemeral(true).queue();
            }
        }

    }
}
