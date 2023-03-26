package ua.mani123.listeners;

import net.dv8tion.jda.api.events.interaction.command.CommandAutoCompleteInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class onCommandAutoCompleteInteractionEvent extends ListenerAdapter {
    private final String[] words = new String[]{"DO_NOT_DISTURB", "ONLINE", "IDLE"};

    @Override
    public void onCommandAutoCompleteInteraction(@NotNull CommandAutoCompleteInteractionEvent event) {
        if (Objects.requireNonNull(event.getSubcommandName()).equals("status") && event.getFocusedOption().getName().equals("type")) {
            List<Command.Choice> options = Stream.of(words)
                    .filter(word -> word.startsWith(event.getFocusedOption().getValue()))
                    .map(word -> new Command.Choice(word, word))
                    .collect(Collectors.toList());
            event.replyChoices(options).queue();
        }
    }
}
