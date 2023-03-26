package ua.mani123.consoleCommands;

import ua.mani123.Main;
import ua.mani123.database.DatabaseObjects.WMUser;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class consoleUtils implements Runnable {

    static boolean isStopped = false;

    @Override
    public void run() {
        Scanner ins = new Scanner(System.in);
        do {
            List<String> parts = List.of(ins.nextLine().split(" "));
            switch (parts.get(0)) {
                case "send" -> {
                    try {
                        long id = Long.parseLong(parts.get(1));
                        Objects.requireNonNull(Main.jda.getPrivateChannelById(id)).sendMessage(parts.get(3)).queue();
                    } catch (NumberFormatException e) {
                        Main.logger.info("Id: " + parts.get(1) + " is not found or wrong");
                    }
                }
                case "ban" -> {
                    try {
                        long id = Long.parseLong(parts.get(1));
                        WMUser wmUser = Main.dataBase.getUserByDiscordId(id);
                        //wmUser.setBanned(true);
                        Main.logger.info("User " + Main.jda.getUserById(wmUser.getDiscordId()).getAsTag() + " banned");
                    } catch (NumberFormatException e) {
                        Main.logger.info("Id: " + parts.get(1) + " is not found or wrong");
                    }
                }
                case "unban" -> {
                    try {
                        long id = Long.parseLong(parts.get(1));
                        WMUser wmUser = Main.dataBase.getUserByDiscordId(id);
                        //account.setBanned(false);
                        Main.logger.info("User " + Main.jda.getUserById(wmUser.getDiscordId()).getAsTag() + " unbanned");
                    } catch (NumberFormatException e) {
                        Main.logger.info("Id: " + parts.get(1) + " is not found or wrong");
                    }
                }
                default -> Main.logger.info("Command not found");
            }
        } while (!isStopped);
    }

}
