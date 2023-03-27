package ua.mani123.command.commands;

import lombok.Getter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import ua.mani123.Main;
import ua.mani123.command.WMCommand;
import ua.mani123.database.DatabaseObjects.WMUser;

import java.awt.*;
import java.util.Objects;

@Getter
public class DevCommand extends WMCommand {

    public DevCommand(String name) {
        this.name = name;
        this.slashCommandData = Commands.slash(this.name, "Command to manage the real balance").setGuildOnly(true)
                .addSubcommands(
                        new SubcommandData("send", "Send message to user id")
                                .addOption(OptionType.STRING, "user-id", "to user id", true)
                                .addOption(OptionType.STRING, "text", "text", true),
                        new SubcommandData("sendfile", "Send file to user id")
                                .addOption(OptionType.STRING, "user-id", "to user id", true)
                                .addOption(OptionType.ATTACHMENT, "file", "file", true),
                        new SubcommandData("status", "Change bot status")
                                .addOption(OptionType.STRING, "type", "type", true, true)
                );
    }


    @Override
    public void execute(SlashCommandInteractionEvent event, WMUser wmUser) {
        if (event.isFromGuild() && Objects.requireNonNull(event.getMember()).getRoles().contains(Main.support_role)) {
            switch (Objects.requireNonNull(event.getSubcommandName())) {
                case "status" -> {
                    String type = Objects.requireNonNull(event.getOption("type")).getAsString();
                    Main.jda.setStatus(OnlineStatus.valueOf(type));
                    event.replyEmbeds(wmUser.getLang().getStrings().embed_default_success_title.build()).setEphemeral(true).queue();
                }
                case "send" -> {
                    long userId = Objects.requireNonNull(event.getOption("user-id")).getAsLong();
                    String text = Objects.requireNonNull(event.getOption("text")).getAsString();
                    event.getJDA().openPrivateChannelById(userId).flatMap(privateChannel -> privateChannel.sendMessageEmbeds(new EmbedBuilder().setDescription(text).setTitle("Support " + event.getUser().getAsTag()).setColor(Color.BLUE).build())).queue();
                    event.replyEmbeds(wmUser.getLang().getStrings().embed_default_success_title.build()).setEphemeral(true).queue();
                }
                case "sendfile" -> {
                    long userId = Objects.requireNonNull(event.getOption("user-id")).getAsLong();
                    Message.Attachment attachment = Objects.requireNonNull(event.getOption("file")).getAsAttachment();
                    event.getJDA().openPrivateChannelById(userId).flatMap(privateChannel -> privateChannel.sendMessageEmbeds(new EmbedBuilder().setImage(attachment.getUrl()).setTitle("Support " + event.getUser().getAsTag()).setColor(Color.BLUE).build())).queue();
                    event.replyEmbeds(wmUser.getLang().getStrings().embed_default_success_title.build()).setEphemeral(true).queue();
                }
                default ->
                        event.replyEmbeds(wmUser.getLang().getStrings().embed_default_error_title.build()).setEphemeral(true).queue();
            }
        } else {
            event.replyEmbeds(wmUser.getLang().getStrings().embed_default_error_title.build()).setEphemeral(true).queue();
        }
    }
}
