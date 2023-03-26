package ua.mani123.listeners;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class onGuildReadyEvent extends ListenerAdapter {

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


}
