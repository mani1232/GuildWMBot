package ua.mani123.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import ua.mani123.Main;

import java.awt.*;

public class onGuildEvents extends ListenerAdapter {

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        //int size = Main.dataBase.getAccounts().size();
        //event.getGuild().loadMembers().onSuccess(members -> {
        //            for (Member member : members) {
        //                boolean hasAccount = false;
        //                for (Account account : Main.dataBase.getAccounts()) {
        //                    if (account.getDiscordUserId() == member.getIdLong()) {
        //                        hasAccount = true;
        //                    }
        //                }
        //                if (!hasAccount) {
        //                    Main.dataBase.getAccounts().add(new Account(member.getIdLong()));
        //                }
        //            }
        //            int addedAccounts = Main.dataBase.getAccounts().size() - size;
        //            Main.logger.info("Loaded accounts: " + size + " and added new: " + addedAccounts);
        //            DataBase.save(Main.file, Main.dataBase);
        //        }
        //);
    }

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        Main.logger.info("User joined to guild: " + event.getUser().getAsTag());
        event.getUser().openPrivateChannel().queue(privateChannel -> privateChannel.sendMessageEmbeds(new EmbedBuilder().setColor(Color.RED).setDescription(String.format("""
                \s
                ```Markdown
                %s
                > To use this bot you need to use any command of this bot, for example `/account`
                %s
                > Для того що б користуватися ботом вам потрібно використовувати будь-яку команду цього бота, наприклад `/account`
                %s
                > Для того что бы пользоватся ботом вам нужно использовать любую команду этого бота, к примеру `/account`
                ```
                """, Emoji.fromFormatted("\uD83C\uDDEC\uD83C\uDDE7"), Emoji.fromFormatted("\uD83C\uDDFA\uD83C\uDDE6"), Emoji.fromFormatted("\uD83C\uDDF7\uD83C\uDDFA"))).setTitle("Welcome to WorldMandia discord server").build()).queue());
    }


}
