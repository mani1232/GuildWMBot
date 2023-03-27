package ua.mani123.listeners;

import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceUpdateEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class onVoiceChatJoin extends ListenerAdapter {

    @Override
    public void onGuildVoiceUpdate(@NotNull GuildVoiceUpdateEvent event) {
        if (event.getChannelJoined() instanceof VoiceChannel voiceChannel) {
            if (Objects.equals(voiceChannel.getParentCategoryId(), "1089647059948282006")) {
                if (voiceChannel.getId().equals("1089647233185611907")) Objects.requireNonNull(voiceChannel.getParentCategory()).createVoiceChannel("・" + event.getMember().getEffectiveName()).queue(newVoiceChannel -> event.getGuild().moveVoiceMember(event.getMember(), newVoiceChannel).queue());
            }
        } else if (event.getChannelLeft() instanceof VoiceChannel voiceChannel) {
            if (Objects.equals(voiceChannel.getParentCategoryId(), "1089647059948282006")) {
                if (!voiceChannel.getId().equals("1089647233185611907")) voiceChannel.delete().queue();
            }
        }
    }
}
