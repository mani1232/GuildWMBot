package ua.mani123.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.ThreadChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import ua.mani123.Main;
import ua.mani123.database.DatabaseObjects.WMUser;

import java.awt.*;
import java.util.Objects;
import java.util.Optional;

public class onMessageEvent extends ListenerAdapter {

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (!event.getAuthor().isBot() && event.getJDA().getPresence().getStatus() != OnlineStatus.DO_NOT_DISTURB) {
            WMUser wmUser = Main.dataBase.getUserByDiscordId(event.getAuthor().getIdLong());
            if (wmUser == null) {
                if (event.isFromType(ChannelType.PRIVATE)) {
                    event.getJDA().openPrivateChannelById(event.getAuthor().getIdLong()).flatMap(privateChannel -> privateChannel.sendMessageEmbeds(new EmbedBuilder().setTitle("You need register").setDescription("Use any bot command").build())).queue((success) -> {
                    }, (error) -> Main.logger.warn(event.getAuthor().getAsTag()));
                }
                return;
            } else if (wmUser.isBanned()) {
                return;
            }
            TextChannel textChannel = Main.main_guild.getTextChannelById(1089658022768418926L);
            if (event.isFromType(ChannelType.PRIVATE)) {
                Optional<ThreadChannel> threadChannel = Objects.requireNonNull(textChannel).getThreadChannels().stream().filter(threadChannelFilter -> threadChannelFilter.getName().equals(event.getAuthor().getAsTag() + "-" + event.getAuthor().getId())).findFirst();
                if (threadChannel.isPresent()) {
                    threadChannel.get().sendMessageEmbeds(new EmbedBuilder().setDescription(event.getMessage().getContentDisplay()).setTitle(wmUser.getLang().getNativeName() + " - " + event.getAuthor().getAsTag()).setColor(Color.BLUE).build()).queue();
                } else {
                    textChannel.sendMessage(Main.support_role.getAsMention()).queue();
                    textChannel.sendMessageEmbeds(new EmbedBuilder().setDescription(String.format("""
                            User: `%s`
                            User id: `%s`
                            Name: `%s`
                            """, event.getAuthor().getAsTag(), event.getAuthor().getId(), wmUser.getDisplayName()
                    )).setColor(Color.BLUE).build()).submit().whenComplete(((message, throwable) -> textChannel.createThreadChannel(event.getAuthor().getAsTag() + "-" + event.getAuthor().getId(), message.getId()).flatMap(threadChannel1 -> threadChannel1.sendMessageEmbeds(new EmbedBuilder().setDescription(event.getMessage().getContentDisplay()).setTitle(wmUser.getLang().getNativeName() + " - " + event.getAuthor().getAsTag()).setColor(Color.BLUE).build())).queue()));
                }
                Main.logger.info(String.format("[PM] %s -> %s", event.getAuthor().getAsTag(), event.getMessage()));
            } else if (event.isFromType(ChannelType.GUILD_PUBLIC_THREAD)) {
                if (event.getGuild().equals(Main.main_guild)) {
                    String[] parts = event.getChannel().asThreadChannel().getName().split("-");
                    event.getJDA().openPrivateChannelById(parts[1]).flatMap(privateChannel -> privateChannel.sendMessageEmbeds(new EmbedBuilder().setDescription(event.getMessage().getContentDisplay()).setTitle("Support " + event.getAuthor().getAsTag()).setColor(Color.BLUE).build())).queue();
                }
            }
        }
    }
}
