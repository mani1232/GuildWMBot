package ua.mani123.listeners;

import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class onContextInteractionEvent extends ListenerAdapter {

    @Override
    public void onUserContextInteraction(@NotNull UserContextInteractionEvent event) {
        super.onUserContextInteraction(event);
    }

    @Override
    public void onMessageContextInteraction(@NotNull MessageContextInteractionEvent event) {
        super.onMessageContextInteraction(event);
    }
}
