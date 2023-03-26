package ua.mani123.listeners;

import net.dv8tion.jda.api.events.interaction.component.EntitySelectInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import ua.mani123.Main;
import ua.mani123.database.DatabaseObjects.WMUser;

public class onEntitySelectInteractionEvent extends ListenerAdapter {

    @Override
    public void onEntitySelectInteraction(@NotNull EntitySelectInteractionEvent event) {
        if (EventUtils.isBotOffline(event)) return;

        WMUser account = Main.dataBase.getUserByDiscordId(event.getUser().getIdLong());
        if (account == null) {
            EventUtils.sendRegisterModal(event);
            return;
        }
        switch (event.getComponentId()) {
            default -> event.replyEmbeds(account.getLang().getStrings().embed_default_error_title.build()).setEphemeral(true).queue();
        }
    }
}
