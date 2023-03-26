package ua.mani123;

import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.interaction.GenericInteractionCreateEvent;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Utils {

    public static Map<Long, List<VoiceChannel>> tempVoiceChatData = new HashMap<>();

    public static boolean isMainGuild(GenericInteractionCreateEvent event) {
        return Objects.equals(event.getGuild(), Main.main_guild);
    }
}
